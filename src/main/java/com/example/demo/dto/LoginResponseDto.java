package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String jwt;
    private Long userId;
}
