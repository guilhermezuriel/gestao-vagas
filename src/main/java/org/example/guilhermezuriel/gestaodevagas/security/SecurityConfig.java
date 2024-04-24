package org.example.guilhermezuriel.gestaodevagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> {
            try {
                csrf.disable()
                        .authorizeHttpRequests(auth -> {
                            auth.requestMatchers("/candidate/").permitAll()
                                    .requestMatchers("/company/**").permitAll();
                            auth.anyRequest().authenticated();
                        });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();
    }
}
