package com.example.demo.controller;


import com.example.demo.dto.PatientDto;
import com.example.demo.entity.User;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
@Scope("prototype")
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    //@PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    @GetMapping("/findall")
    public ResponseEntity<List<PatientDto>> findAllPatients(){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(patientService.findAllPatients());
    }
}