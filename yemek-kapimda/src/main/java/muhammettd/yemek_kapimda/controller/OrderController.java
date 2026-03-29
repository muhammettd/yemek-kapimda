package muhammettd.yemek_kapimda.controller;

import jakarta.validation.Valid;
import muhammettd.yemek_kapimda.dto.OrderCreateRequest;
import muhammettd.yemek_kapimda.dto.OrderResponse;
import muhammettd.yemek_kapimda.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(Authentication authentication,
                                                @Valid @RequestBody OrderCreateRequest request) {
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(userId, request));
    }

    @GetMapping
    public List<OrderResponse> myOrders(Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        return orderService.listMyOrders(userId);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(Authentication authentication, @PathVariable Long orderId) {
        UUID userId = (UUID) authentication.getPrincipal();
        return orderService.getOrder(userId, orderId);
    }


}
