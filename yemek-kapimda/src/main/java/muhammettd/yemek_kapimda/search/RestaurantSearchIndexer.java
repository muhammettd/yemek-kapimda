package muhammettd.yemek_kapimda.search;

import muhammettd.yemek_kapimda.model.Product;
import muhammettd.yemek_kapimda.model.Restaurant;
import muhammettd.yemek_kapimda.repository.ProductRepository;
import muhammettd.yemek_kapimda.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantSearchIndexer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RestaurantSearchIndexer.class);

    private final ElasticsearchOperations operations;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;
    private final RestaurantSearchService searchService;

    public RestaurantSearchIndexer(ElasticsearchOperations operations,
                                   RestaurantRepository restaurantRepository,
                                   ProductRepository productRepository,
                                   RestaurantSearchService searchService) {
        this.operations = operations;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
        this.searchService = searchService;
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            IndexOperations indexOps = operations.indexOps(RestaurantSearchDocument.class);
            if (!indexOps.exists()) {
                indexOps.create();
            }

            Iterable<Restaurant> restaurants = restaurantRepository.findAll();
            for (Restaurant r : restaurants) {
                List<Product> products = productRepository.findByRestaurant_IdAndAvailableTrueOrderByNameAsc(r.getId());
                RestaurantSearchDocument doc = searchService.toDocument(r, products);
                operations.save(doc);
            }
            log.info("Elasticsearch restaurants indexlandi.");
        } catch (Exception e) {
            log.warn("Elasticsearch indexleme atlandı (ES çalışmıyor olabilir). Sebep: {}", e.getMessage());
        }

    }
}
