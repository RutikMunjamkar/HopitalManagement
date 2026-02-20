package com.example.demo.dto;

import com.example.demo.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DepartmentDto {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private DoctorDto headDoctor;
    private Set<DoctorDto> doctors;
}
