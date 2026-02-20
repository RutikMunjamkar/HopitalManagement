package com.example.demo.dto;

import com.example.demo.entity.Insurance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class InsuranceDto {
    private Long id;
    private String policyNumber;
    private String provide;
    private LocalDate validUntil;
    private LocalDateTime createdAt;
    private PatientDto patientDto;
}
