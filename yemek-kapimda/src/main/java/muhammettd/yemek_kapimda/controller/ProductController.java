package muhammettd.yemek_kapimda.controller;

import muhammettd.yemek_kapimda.dto.ProductResponse;
import muhammettd.yemek_kapimda.service.RestaurantCatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/products")
public class ProductController {

    private final RestaurantCatalogService restaurantCatalogService;

    public ProductController(RestaurantCatalogService restaurantCatalogService) {
        this.restaurantCatalogService = restaurantCatalogService;
    }

    @GetMapping
    public List<ProductResponse> listMenu(@PathVariable Long restaurantId) {
        return restaurantCatalogService.listMenu(restaurantId);
    }


}
