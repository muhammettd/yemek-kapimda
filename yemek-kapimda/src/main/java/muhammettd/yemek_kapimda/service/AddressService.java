package muhammettd.yemek_kapimda.service;

import muhammettd.yemek_kapimda.dto.AddressCreateRequest;
import muhammettd.yemek_kapimda.dto.AddressResponse;
import muhammettd.yemek_kapimda.model.Address;
import muhammettd.yemek_kapimda.model.User;
import muhammettd.yemek_kapimda.repository.AddressRepository;
import muhammettd.yemek_kapimda.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<AddressResponse> listForUser(UUID userId) {
        return addressRepository.findByUser_IdAndIsDeletedFalseOrderByIdAsc(userId).stream()
                .map(this::toResponse)
                .toList();
    }


    public AddressResponse create(UUID userId, AddressCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı."));

        Address address = new Address();
        address.setUser(user);
        address.setTitle(request.getTitle());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setFullAddress(request.getFullAddress());

        Address savedAddress = addressRepository.save(address);

        return toResponse(savedAddress);

    }

    public void deleteAddress(Long addressId, UUID userId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Adres bulunamadı."));

        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("Bu adresi silmeye yetkiniz yok!");
        }

        address.setDeleted(true);
        addressRepository.save(address);
    }

    private AddressResponse toResponse(Address a) {
        return new AddressResponse(
                a.getId(),
                a.getTitle(),
                a.getCity(),
                a.getDistrict(),
                a.getFullAddress()
        );

    }

}
