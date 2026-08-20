package com.example.AICrypto_trader.users;

import com.example.AICrypto_trader.common.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsersModel createUser(
            String email,
            String password
    ) {

        if (usersRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        UsersModel user = new UsersModel();

        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setTwoFactorEnabled(false);

        return usersRepository.save(user);
    }

    public UserResponseDTO getUserById(Long id) {

        UsersModel user = usersRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.isTwoFactorEnabled()
        );
    }
}