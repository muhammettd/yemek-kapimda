package muhammettd.yemek_kapimda.repository;

import muhammettd.yemek_kapimda.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUser_IdAndIsDeletedFalseOrderByIdAsc(UUID userId);

    Optional<Address> findByIdAndUser_Id(Long deliveryAddressId, UUID userId);
}
