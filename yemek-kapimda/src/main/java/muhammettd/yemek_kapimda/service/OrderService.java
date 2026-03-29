package muhammettd.yemek_kapimda.service;

import muhammettd.yemek_kapimda.dto.OrderCreateRequest;
import muhammettd.yemek_kapimda.dto.OrderItemRequest;
import muhammettd.yemek_kapimda.dto.OrderMapper;
import muhammettd.yemek_kapimda.dto.OrderResponse;
import muhammettd.yemek_kapimda.model.*;
import muhammettd.yemek_kapimda.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository,
                        RestaurantRepository restaurantRepository, ProductRepository productRepository,
                        AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public OrderResponse createOrder(UUID userId, OrderCreateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restoran bulunamadı"));

        if (!restaurant.isOpen()) {
            throw new IllegalStateException("Restoran şu anda sipariş kabul etmiyor.");
        }

        Address address = addressRepository.findByIdAndUser_Id(request.getDeliveryAddressId(), userId)
                .orElseThrow(() -> new IllegalArgumentException("Teslimat adresi bulunamadı veya size ait değil."));

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository
                    .findByIdAndRestaurant_Id(itemRequest.getProductId(), restaurant.getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Ürün bulunamadı veya bu restorana ait değil: " + itemRequest.getProductId()));

            if (!product.isAvailable()) {
                throw new IllegalStateException("Şu an satışta olmayan ürün: " + product.getName());
            }

            double unit = product.getPrice() != null ? product.getPrice() : 0.0;
            double lineTotal = unit * itemRequest.getQuantity();

            OrderItem item = new OrderItem();
            item.setProductId(product.getId());
            item.setQuantity(itemRequest.getQuantity());
            item.setUnitPrice(unit);
            item.setTotalPrice(lineTotal);

            orderItems.add(item);
            totalAmount += lineTotal;
        }

        if (totalAmount < restaurant.getMinAmountOrder()) {
            throw new IllegalStateException("Minimum paket tutarını karşılamıyorsunuz.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setDeliveryAddress(address);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(totalAmount);
        order.setCreatedAt(LocalDateTime.now());

        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }
        order.setOrderItems(orderItems);

        Order saved = orderRepository.save(order);

        return OrderMapper.toResponse(saved);


    }

    @Transactional(readOnly = true)
    public List<OrderResponse> listMyOrders(UUID userId) {
        return orderRepository.findByUser_IdOrderByCreatedAtDesc(userId).stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(UUID userId, Long orderId) {
        Order order = orderRepository.findByIdAndUser_Id(orderId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Sipariş bulunamadı."));
        return OrderMapper.toResponse(order);
    }


}
