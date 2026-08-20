package com.example.AICrypto_trader.common;



public class UserResponseDTO {

    private final Long id;
    private final String email;
    private final String role;
    private final boolean twoFactorEnabled;

    public UserResponseDTO(
            Long id,
            String email,
            String role,
            boolean twoFactorEnabled
    ) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public boolean isTwoFactorEnabled() {
        return twoFactorEnabled;
    }
}