package com.example.demo.dto;


import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String specialization;
    private String email;
    private LocalDateTime createdAt;
}
