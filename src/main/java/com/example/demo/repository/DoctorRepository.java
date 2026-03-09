package com.example.demo.repository;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    @Query(value = "select d.appointmentList from Doctor d where d.id=:doctorId")
    List<AppointmentDto> findAppointmentListByDoctorId(@Param("doctorId") Long id);
}
