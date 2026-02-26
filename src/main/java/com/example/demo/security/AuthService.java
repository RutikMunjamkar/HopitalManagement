package com.example.demo.security;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.SignUpResponseDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final AuthUtil authUtil;

    private final UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())) ;
        User user= (User) authentication.getPrincipal();
        String token=authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }

    @Transactional
    public SignUpResponseDto signUp(LoginRequestDto signUpRequestDto){
        User user= (User) userRepository.findByUsername(signUpRequestDto.getUsername());
        if(user!=null) throw new IllegalArgumentException("this user is already present");
        User cratedUser=User.builder().username(signUpRequestDto.getUsername()).password(passwordEncoder.encode(signUpRequestDto.getPassword())).build();
        userRepository.save(cratedUser);
        return objectMapper.convertValue(cratedUser,SignUpResponseDto.class);
    }
}
