package muhammettd.yemek_kapimda.controller;

import muhammettd.yemek_kapimda.dto.RestaurantResponse;
import muhammettd.yemek_kapimda.search.RestaurantSearchService;
import muhammettd.yemek_kapimda.service.RestaurantCatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantCatalogService restaurantCatalogService;
    private final RestaurantSearchService restaurantSearchService;

    public RestaurantController(RestaurantCatalogService restaurantCatalogService, RestaurantSearchService restaurantSearchService) {
        this.restaurantCatalogService = restaurantCatalogService;
        this.restaurantSearchService = restaurantSearchService;
    }

    @GetMapping
    public List<RestaurantResponse> listOpen() {
        return restaurantCatalogService.listOpenRestaurants();
    }

    @GetMapping("/search")
    public List<RestaurantResponse> search(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "cuisine", required = false) String cuisine) {
        return restaurantSearchService.search(query, cuisine);
    }

    @GetMapping("/cuisines")
    public List<String> cuisines() {
        return restaurantSearchService.listCuisines();
    }


}
