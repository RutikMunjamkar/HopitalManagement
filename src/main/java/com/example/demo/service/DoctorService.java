package com.example.demo.service;

import com.example.demo.dto.DoctorDto;
import com.example.demo.entity.Doctor;
import com.example.demo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ObjectMapper objectMapper;

    public List<DoctorDto> findAllDoctors(){
        List<Doctor>doctors= doctorRepository.findAll();
        return objectMapper.convertValue(doctors, new TypeReference<List < DoctorDto>>() {});
    }
}
