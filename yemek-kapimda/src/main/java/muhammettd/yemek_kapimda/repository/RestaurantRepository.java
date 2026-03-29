package muhammettd.yemek_kapimda.repository;

import muhammettd.yemek_kapimda.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByOrderByNameAsc();

    List<Restaurant> findByOrderByRatingStarsDesc();

    List<Restaurant> findByAndCuisineIgnoreCaseOrderByRatingStarsDesc(String cuisine);

    Optional<Restaurant> findByOwner_Id(UUID ownerId);

    List<Restaurant> findByNameContainingIgnoreCase(String name);

    @Query("select distinct r.cuisine from Restaurant r where r.cuisine is not null and r.cuisine <> '' order by r.cuisine asc")
    List<String> findDistinctCuisine();


}
