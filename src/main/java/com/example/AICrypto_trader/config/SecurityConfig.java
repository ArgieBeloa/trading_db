package com.example.AICrypto_trader.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final UsersDetailsService usersDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter,  UsersDetailsService usersDetailsService) {
        this.jwtFilter = jwtFilter;
        this.usersDetailsService = usersDetailsService;
    }

    // ✅ 1. Global CORS configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8081")
                        .allowedMethods(
                                "GET",
                                "POST",
                                "PUT",
                                "DELETE",
                                "OPTIONS",
                                "PATCH"
                        )
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    // ✅ 2. Security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth

                        // Public registration/login
                        .requestMatchers(
                                "/api/users",
                                "/api/auth/**"
                        ).permitAll()

                        // WebSocket endpoint
                        .requestMatchers("/ws/**").permitAll()

                        // Everything else requires JWT
                        .anyRequest().authenticated()
                )

                .sessionManagement(sess ->
                        sess.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .userDetailsService(usersDetailsService);

        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    // ✅ 3. Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    // ✅ 4. Authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
