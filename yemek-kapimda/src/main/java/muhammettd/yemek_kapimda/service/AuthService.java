package muhammettd.yemek_kapimda.service;

import muhammettd.yemek_kapimda.dto.AuthResponse;
import muhammettd.yemek_kapimda.dto.UserCreateRequest;
import muhammettd.yemek_kapimda.dto.UserLoginRequest;
import muhammettd.yemek_kapimda.dto.UserResponse;
import muhammettd.yemek_kapimda.model.User;
import muhammettd.yemek_kapimda.repository.UserRepository;
import muhammettd.yemek_kapimda.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
                       JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public AuthResponse register(UserCreateRequest request) {
        UserResponse user = userService.registerUser(request);
        String token = jwtService.generateToken(user.getId(), user.getRole());
        return new AuthResponse(token, "Bearer", user);
    }


    public AuthResponse login(UserLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Kullanıcı bulunamadı"));

        String token = jwtService.generateToken(user.getId(), user.getRole().name());
        return new AuthResponse(token, "Bearer", userService.toResponse(user));
    }
}
