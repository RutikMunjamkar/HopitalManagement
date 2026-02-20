package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public void createAppointment(Appointment appointment, Long doctorId, Long patientId){
        Doctor doctor=doctorRepository.findById(doctorId).orElseThrow(()->new EntityNotFoundException("no doctor found"));
        Patient patient=patientRepository.findById(patientId).orElseThrow(()->new EntityNotFoundException("no patient found"));
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.getAppointmentList().add(appointment);
        doctor.getAppointmentList().add(appointment);
        if(appointment.getId()!=null) throw new IllegalArgumentException("id should be not be present");
        appointmentRepository.save(appointment);
    }

    @Transactional
    public void reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow(()->new EntityNotFoundException("not appointment Found"));
        Doctor doctor=doctorRepository.findById(doctorId).orElseThrow(()->new EntityNotFoundException("not doctor found"));
        Doctor oldDoctor=appointment.getDoctor();
        oldDoctor.getAppointmentList().remove(appointment);
        doctor.getAppointmentList().add(appointment);
        appointment.setDoctor(doctor);
    }

    @Transactional
    public void disAssociateInsuranceFromPatient(Long patientId){
        Patient patient=patientRepository.findById(patientId).orElseThrow(()->new EntityNotFoundException("no patient found"));
        patient.setInsurance(null);
    }


    public void addDeletetPatient(Long patientId){
        Patient patient= patientRepository.findById(patientId).orElseThrow(()->new EntityNotFoundException("not patient fount"));
        List<Appointment> appointmentList=appointmentRepository.findAll();
    }
}
