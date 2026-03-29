package muhammettd.yemek_kapimda.search;

import muhammettd.yemek_kapimda.dto.RestaurantResponse;
import muhammettd.yemek_kapimda.model.Product;
import muhammettd.yemek_kapimda.model.Restaurant;
import muhammettd.yemek_kapimda.repository.ProductRepository;
import muhammettd.yemek_kapimda.repository.RestaurantRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class RestaurantSearchService {

    private final ElasticsearchOperations operations;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;

    public RestaurantSearchService(ElasticsearchOperations operations,
                                   RestaurantRepository restaurantRepository,
                                   ProductRepository productRepository) {
        this.operations = operations;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
    }

    public List<RestaurantResponse> search(String query, String cuisine) {
        String q = query != null ? query.trim() : "";
        String c = cuisine != null ? cuisine.trim() : "";

        boolean isAnyCuisine = !c.isBlank() && !"ALL".equalsIgnoreCase(c);

        // Query boşsa Elasticsearch ile uğraşmadan JPA'den puana göre sırala.
        if (q.isBlank()) {
            if (isAnyCuisine) {
                return restaurantRepository
                        .findByAndCuisineIgnoreCaseOrderByRatingStarsDesc(c)
                        .stream()
                        .map(RestaurantSearchService::toResponseFromJpa)
                        .toList();
            }
            return restaurantRepository.findByOrderByRatingStarsDesc().stream()
                    .map(RestaurantSearchService::toResponseFromJpa)
                    .toList();
        }

        // Elasticsearch: restoran adı veya menüdeki ürün adında eşleşenleri getir.
        List<RestaurantSearchDocument> nameHits = searchByField("name", q);
        List<RestaurantSearchDocument> productHits = searchByField("productNames", q);

        Map<Long, RestaurantSearchDocument> merged = Stream.concat(nameHits.stream(), productHits.stream())
                .filter(d -> !isAnyCuisine || (d.getCuisine() != null && d.getCuisine().equalsIgnoreCase(c)))
                .collect(java.util.stream.Collectors.toMap(RestaurantSearchDocument::getId, d -> d, (a, b) -> a));

        return merged.values().stream()
                .sorted(Comparator.comparing(RestaurantSearchDocument::getRatingStars,
                        Comparator.nullsLast(Comparator.reverseOrder())))
                .map(RestaurantSearchService::toRestaurantResponse)
                .toList();
    }

    private List<RestaurantSearchDocument> searchByField(String field, String query) {
        Criteria criteria = new Criteria(field).matches(query);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        criteriaQuery.setPageable(PageRequest.of(0, 50));

        try {
            SearchHits<RestaurantSearchDocument> hits = operations.search(criteriaQuery, RestaurantSearchDocument.class);
            return hits.get().toList().stream().map(h -> h.getContent()).toList();
        } catch (Exception e) {
            // Elasticsearch çalışmıyorsa arama sonuç döndürmek yerine sessizce boş dön.
            return List.of();
        }
    }

    public List<String> listCuisines() {
        return restaurantRepository.findDistinctCuisine();
    }

    private static RestaurantResponse toRestaurantResponse(RestaurantSearchDocument doc) {
        RestaurantResponse dto = new RestaurantResponse();
        dto.setId(doc.getId());
        dto.setName(doc.getName());
        dto.setCuisine(doc.getCuisine());
        dto.setRatingStars(doc.getRatingStars());
        dto.setDescription(doc.getDescription());
        dto.setLogoUrl(doc.getLogoUrl());
        dto.setMinOrderAmount(doc.getMinOrderAmount());
        dto.setEstimatedDeliveryTime(doc.getEstimatedDeliveryTime());
        dto.setOpen(doc.isOpen());
        return dto;
    }

    private static RestaurantResponse toResponseFromJpa(Restaurant r) {
        RestaurantResponse dto = new RestaurantResponse();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setCuisine(r.getCuisine());
        dto.setRatingStars(r.getRatingStars());
        dto.setDescription(r.getDescription());
        dto.setLogoUrl(r.getLogoUrl());
        dto.setMinOrderAmount(r.getMinAmountOrder());
        dto.setEstimatedDeliveryTime(r.getEstimatedDeliveryTime());
        dto.setOpen(r.isOpen());
        return dto;
    }

    // Indexing için helper
    public RestaurantSearchDocument toDocument(Restaurant restaurant, List<Product> products) {
        RestaurantSearchDocument doc = new RestaurantSearchDocument();
        doc.setId(restaurant.getId());
        doc.setName(restaurant.getName());
        doc.setCuisine(restaurant.getCuisine());
        doc.setRatingStars(restaurant.getRatingStars());
        doc.setDescription(restaurant.getDescription());
        doc.setLogoUrl(restaurant.getLogoUrl());
        doc.setMinOrderAmount(restaurant.getMinAmountOrder());
        doc.setEstimatedDeliveryTime(restaurant.getEstimatedDeliveryTime());
        doc.setOpen(restaurant.isOpen());

        List<String> productNames = products.stream()
                .map(Product::getName)
                .filter(n -> n != null && !n.isBlank())
                .toList();
        doc.setProductNames(productNames);

        return doc;
    }

    public List<Product> listAvailableProductsForRestaurant(Long restaurantId) {
        return productRepository.findByRestaurant_IdAndAvailableTrueOrderByNameAsc(restaurantId);
    }


}
