package muhammettd.yemek_kapimda.repository;

import muhammettd.yemek_kapimda.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByRestaurant_IdAndAvailableTrueOrderByNameAsc(Long restaurantId);

    Optional<Product> findByIdAndRestaurant_Id(Long productId, Long restaurantId);

}
