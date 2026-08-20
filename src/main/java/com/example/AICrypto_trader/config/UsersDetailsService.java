package com.example.AICrypto_trader.config;

import com.example.AICrypto_trader.users.UsersModel;
import com.example.AICrypto_trader.users.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UsersDetailsService implements UserDetailsService {

    private final UsersRepository repo;

    public UsersDetailsService(UsersRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersModel users = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found: " + email));

        // Create a UserDetails. No roles shown here; add if needed.
        return User.builder()
                .username(users.getEmail())
                .password(users.getPassword()) // password must be already BCrypt-hashed in DB
                .authorities("STUDENT")
                .build();
    }
}
