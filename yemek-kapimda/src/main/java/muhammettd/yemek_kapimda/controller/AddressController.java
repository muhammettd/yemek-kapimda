package muhammettd.yemek_kapimda.controller;

import jakarta.validation.Valid;
import muhammettd.yemek_kapimda.dto.AddressCreateRequest;
import muhammettd.yemek_kapimda.dto.AddressResponse;
import muhammettd.yemek_kapimda.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressResponse> list(Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        return addressService.listForUser(userId);
    }

    @PostMapping
    public ResponseEntity<AddressResponse> create(Authentication authentication,
                                                  @Valid @RequestBody AddressCreateRequest request) {
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(userId, request));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId, Principal principal) {

        UUID userId = UUID.fromString(principal.getName());

        addressService.deleteAddress(addressId, userId);
        return ResponseEntity.ok().build();
    }

}
