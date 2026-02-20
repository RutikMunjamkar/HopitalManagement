package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.
                authorizeHttpRequests(auth-> auth
                        .requestMatchers("/public/**").hasRole("ADMIN")
                        .requestMatchers("/patients/**").hasRole("PATIENT")
                        .requestMatchers("/doctors/**").hasAnyRole("DOCTOR", "ADMIN")
                ).formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}