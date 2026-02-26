package com.example.demo.controller;


import com.example.demo.dto.PatientDto;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/findall")
    public ResponseEntity<List<PatientDto>> findAllPatients(){
        return ResponseEntity.ok(patientService.findAllPatients());
    }
}

