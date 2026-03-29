package muhammettd.yemek_kapimda.controller;

import jakarta.validation.Valid;
import muhammettd.yemek_kapimda.dto.AuthResponse;
import muhammettd.yemek_kapimda.dto.UserCreateRequest;
import muhammettd.yemek_kapimda.dto.UserLoginRequest;
import muhammettd.yemek_kapimda.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserLoginRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(request));
    }


}
