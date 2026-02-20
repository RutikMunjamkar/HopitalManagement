package com.example.demo;

import com.example.demo.entity.Insurance;
import com.example.demo.entity.Patient;
import com.example.demo.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {


    @Autowired
    InsuranceService insuranceService;

    @Test
    public void testInsurance(){
        Insurance insurance=Insurance.builder()
                .policyNumber("SBI 0001")
                .provide("SBI")
                .validUntil(LocalDate.of(2027,12,14))
                .build();

        Patient patient=insuranceService.assignInsuranceToPatient(insurance,1l);
        System.out.println(patient);
    }
}
