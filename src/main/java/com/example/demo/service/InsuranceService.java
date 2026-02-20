package com.example.demo.service;

import com.example.demo.entity.Insurance;
import com.example.demo.entity.Patient;
import com.example.demo.repository.InsuranceRepository;
import com.example.demo.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient=patientRepository.findById(patientId).orElseThrow(()->  new EntityNotFoundException("the patient not found"));
        patient.setInsurance(insurance);
        return patient;
    }
}
