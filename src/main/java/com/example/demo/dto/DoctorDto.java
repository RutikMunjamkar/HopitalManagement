package com.example.demo.dto;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class DoctorDto {
    private Long id;
    private String name;
    private String specialization;
    private String email;
    private LocalDateTime createdAt;
}
