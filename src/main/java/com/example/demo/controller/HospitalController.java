package com.example.demo.controller;

import com.example.demo.dto.DoctorDto;
import com.example.demo.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class HospitalController {
    private final DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDto>> getAllDoctors(){
        return ResponseEntity.of(Optional.ofNullable(doctorService.findAllDoctors()));
    }
}
