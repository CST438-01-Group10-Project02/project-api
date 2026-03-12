package com.Group10.Project02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;



@Configuration
public class SecurityConfig {

    /**
     *
     * The current SecurityFilterChain makes all routes public for developing purposes.
     * This will change once project-ui is set up and a token is returned successfully.
     * Replace .anyRequest() to something like .requestMatchers("/users","/actuator/health","/events")
     * and add .anyRequest() after .permitAll()
     *
     **/

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
