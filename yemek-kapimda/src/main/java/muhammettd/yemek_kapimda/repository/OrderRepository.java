package muhammettd.yemek_kapimda.repository;

import muhammettd.yemek_kapimda.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_IdOrderByCreatedAtDesc(UUID userId);

    Optional<Order> findByIdAndUser_Id(Long orderId, UUID userId);

}
