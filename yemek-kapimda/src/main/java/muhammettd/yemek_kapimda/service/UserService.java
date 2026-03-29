package muhammettd.yemek_kapimda.service;

import muhammettd.yemek_kapimda.dto.UserCreateRequest;
import muhammettd.yemek_kapimda.dto.UserResponse;
import muhammettd.yemek_kapimda.model.Role;
import muhammettd.yemek_kapimda.model.User;
import muhammettd.yemek_kapimda.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Bu e-posta adresi kullanımda");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(Role.CUSTOMER);

        User savedUser = userRepository.save(user);

        return toResponse(savedUser);

    }

    public UserResponse getUserById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));

        return toResponse(user);
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole() != null ? user.getRole().name() : null
        );
    }


}
