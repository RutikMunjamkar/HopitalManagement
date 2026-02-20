package com.example.demo.dto;

import com.example.demo.entity.Insurance;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class PatientDto {
    private String id;
    private String name;
    private LocalDate dataOfBirth;
    private String email;
    private String gender;
    private LocalDate createdDate;
    private String bloodGroup;
    private InsuranceDto insuranceDto;
    private List<AppointmentDto> appointmentDtoList;
}
