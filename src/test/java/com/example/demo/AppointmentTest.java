package com.example.demo;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Patient;
import com.example.demo.service.AppointmentService;
import com.example.demo.type.BloodGroupType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    AppointmentService appointmentService;

   @Test
    public void testAppointment(){
        LocalDateTime dateTime = LocalDateTime.of(2026, 2, 14, 10, 30);
        Appointment appointment=Appointment.builder().appointmentTime(dateTime).
                reason("family leave").build();
        appointmentService.createAppointment(appointment, 1L,1l);
    }

    @Test
    public void testReassignAppointmentToAnotherDoctor(){
       appointmentService.reAssignAppointmentToAnotherDoctor(1l,1l);
    }

    @Test
    public void disAssociateInsuranceFromPatient(){
       appointmentService.disAssociateInsuranceFromPatient(1l);
    }

    @Test
    public void addDeletePatient(){
       appointmentService.addDeletetPatient(1l);
    }
}
