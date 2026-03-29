package muhammettd.yemek_kapimda.service;

import muhammettd.yemek_kapimda.dto.ProductResponse;
import muhammettd.yemek_kapimda.dto.RestaurantResponse;
import muhammettd.yemek_kapimda.model.Product;
import muhammettd.yemek_kapimda.model.Restaurant;
import muhammettd.yemek_kapimda.repository.ProductRepository;
import muhammettd.yemek_kapimda.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantCatalogService {

    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;

    public RestaurantCatalogService(RestaurantRepository restaurantRepository, ProductRepository productRepository) {
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
    }

    public List<RestaurantResponse> listOpenRestaurants() {
        return restaurantRepository.findByOrderByNameAsc().stream()
                .map(this::toRestaurantResponse)
                .toList();
    }

    public List<ProductResponse> listMenu(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restoran bulunamadı."));

        return productRepository.findByRestaurant_IdAndAvailableTrueOrderByNameAsc(restaurantId).stream()
                .map(p -> toProductResponse(p, restaurantId))
                .toList();
    }

    private RestaurantResponse toRestaurantResponse(Restaurant r) {
        RestaurantResponse dto = new RestaurantResponse();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setDescription(r.getDescription());
        dto.setLogoUrl(r.getLogoUrl());
        dto.setMinOrderAmount(r.getMinAmountOrder());
        dto.setEstimatedDeliveryTime(r.getEstimatedDeliveryTime());
        dto.setOpen(r.isOpen());
        dto.setCuisine(r.getCuisine());
        dto.setRatingStars(r.getRatingStars());
        return dto;
    }

    private ProductResponse toProductResponse(Product p, Long restaurantId) {
        return new ProductResponse(
                p.getId(),
                restaurantId,
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getImageUrl(),
                p.isAvailable()
        );
    }

}
