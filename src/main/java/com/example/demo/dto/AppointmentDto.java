package com.example.demo.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AppointmentDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private PatientDto patientDto;
    private DoctorDto doctorDto;
}
