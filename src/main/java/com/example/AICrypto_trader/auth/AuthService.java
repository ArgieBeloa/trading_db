package com.example.AICrypto_trader.auth;



import com.example.AICrypto_trader.users.UsersModel;
import com.example.AICrypto_trader.users.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import  com.example.AICrypto_trader.config.JWTService;

@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthService(JWTService jwtService, UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String email, String password) {

        UsersModel user = usersRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password")
                );

        if (!passwordEncoder.matches(
                password,
                user.getPassword()
        )) {
            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        return jwtService.generateToken(user.getEmail(), user.getRole());
    }
}