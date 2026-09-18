package com.example.demo.controller;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.entity.User;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;


    @GetMapping("/allAppointments")
    public ResponseEntity<List<AppointmentDto>> findAllAppointmentByDoctorId(){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(doctorService.findAllAppointmentByDoctorId(user.getId()));
    }
}
