package com.example.demo.controller;

import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.DoctorRequestDto;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/onBoardNewDoctor")
    public ResponseEntity<DoctorDto> onBoardNewDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        return doctorService.onBoardNewDoctor(doctorRequestDto);
    }
}
