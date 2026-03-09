package com.example.demo.service;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.DoctorRequestDto;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.User;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Set;

import static com.example.demo.type.RoleType.DOCTOR;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    public List<DoctorDto> findAllDoctors(){
        List<Doctor>doctors= doctorRepository.findAll();
        return objectMapper.convertValue(doctors, new TypeReference<List < DoctorDto>>() {});
    }

    public List<AppointmentDto> findAllAppointmentByDoctorId(Long id) {
        return doctorRepository.findAppointmentListByDoctorId(id);
    }

    @Transactional
    @PreAuthorize("(hasRole(DOCTOR) OR hasRole(ADMIN)) AND #doctorId=authentication.pricipal.id")
    public ResponseEntity<DoctorDto> onBoardNewDoctor(DoctorRequestDto doctorRequestDto) throws CustomException{
        //assuming that the username of user to be email type
        if(userRepository.existsByUsername(doctorRequestDto.getEmail())){
            throw new CustomException("This User is already present ad Doctor, Patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user=User.builder().username(doctorRequestDto.getEmail()).roles(Set.of(DOCTOR)).build();
        Doctor doctor=Doctor.builder().name(doctorRequestDto.getName()).email(doctorRequestDto.getEmail())
                .specialization(doctorRequestDto.getSpecialization()).user(user).build();
        doctorRepository.save(doctor);
        return ResponseEntity.ok(objectMapper.convertValue(doctor,DoctorDto.class));
    }
}