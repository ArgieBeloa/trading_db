package com.example.AICrypto_trader.users;

import com.example.AICrypto_trader.common.UserCreateRequest;
import com.example.AICrypto_trader.common.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody UserCreateRequest request
    ) {

        UsersModel user = usersService.createUser(
                request.getEmail(),
                request.getPassword()
        );

        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.isTwoFactorEnabled()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                usersService.getUserById(id)
        );
    }
}