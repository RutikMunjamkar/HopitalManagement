package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.SignUpRequestDto;
import com.example.demo.dto.SignUpResponseDto;
import com.example.demo.security.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    @SneakyThrows
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, String bearerToken){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> singUp(@RequestBody SignUpRequestDto singUpRequestDto) {
        return ResponseEntity.ok(authService.signUp(singUpRequestDto));
    }
}