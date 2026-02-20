package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "patient_appointment_id",nullable = false) // patient is required and not nullable
    private Patient patient;// owning side

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name="appointment_dcotor_id",nullable = false)
    private Doctor doctor; //owning side
}