package com.example.demo.security;

import com.example.demo.type.PermissionType;
import com.example.demo.type.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@Slf4j
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Autowired
    OAuth2SuccessHandler oAuth2SuccessHandler;


    @Autowired
    HandlerExceptionResolver handlerExceptionResolver;

//    @Autowired
//    CustomAuthenticationException customAuthenticationException;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/public/**").permitAll().
                        requestMatchers(HttpMethod.DELETE,"/auth/**").hasAnyAuthority(PermissionType.APPOINTMENT_DELETE.getPermission(),PermissionType.USER_MANAGE.getPermission())
                        .requestMatchers("/patients/**").hasRole(RoleType.PATIENT.name())
                        .requestMatchers("/doctors/**").hasAnyRole(RoleType.DOCTOR.name(), RoleType.ADMIN.name())
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole(RoleType.ADMIN.name(),RoleType.DOCTOR.name())
                        .requestMatchers("/login/**").permitAll() // there is denyall as well.
                        .anyRequest().authenticated() // should contain the authentication object in the security context
                )
                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(jwtAuthFilter, ExceptionTranslationFilter.class)
                .oauth2Login(oAuth2->oAuth2.failureHandler(
                        (request, response, exception) -> {
                            log.error("this is error: {} ", exception.getMessage());
                            handlerExceptionResolver.resolveException(request,response,null,exception);
                        })
                        .successHandler(oAuth2SuccessHandler)
                );
//                .exceptionHandling(exceptionHandling->
//                        exceptionHandling.accessDeniedHandler((request, response, accessDeniedException) ->
//                                handlerExceptionResolver.resolveException(request,response,null,accessDeniedException)
//                ).authenticationEntryPoint(customAuthenticationException));
                //.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}