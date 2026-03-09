package com.example.demo.dto;

import com.example.demo.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String password;
    private String name;
    private Set<RoleType> roles=new HashSet<>();
}
