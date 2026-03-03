package com.example.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@Slf4j
public class WebSecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Autowired
    OAuth2SuccessHandler oAuth2SuccessHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/patients/**").hasRole("PATIENT")
                        .requestMatchers("/doctors/**").hasAnyRole("DOCTOR", "ADMIN")
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oAuth2->oAuth2.failureHandler(
                        (request, response, exception) ->
                                log.error("this is error: {} ", exception.getMessage())
                        )
                        .successHandler(oAuth2SuccessHandler)
                );
//                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}