package com.example.demo.service;

import com.example.demo.controller.PatientController;
import com.example.demo.dto.PatientDto;
import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    ApplicationContext context;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Transactional
    public Patient getPatientById(Long id){
        Patient p2=patientRepository.findById(id).orElseThrow();
        // the query won't be executed because already present in the persistence context
        Patient p1=patientRepository.findById(id).orElseThrow();
//        p2.setName("rutik");
        return p2;
    }
    public void parent(){
        for(int i=0;i<5;i++){
           PatientController controller= context.getBean(PatientController.class);
        }
    }
    public List<PatientDto> findAllPatients(){
        List<Patient> patientList=patientRepository.findAll();
        List<PatientDto>patientDtoList=objectMapper.convertValue(patientList, new TypeReference<List<PatientDto>>() {});
        return patientDtoList;
    }
}
