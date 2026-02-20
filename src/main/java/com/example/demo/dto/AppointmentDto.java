package com.example.demo.dto;

import com.example.demo.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDto {
    private Long id;
    private LocalDateTime appointmentTime;
    private String reason;
    private PatientDto patientDto;
    private DoctorDto doctorDto;
}
