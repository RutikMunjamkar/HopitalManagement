package com.example.demo.repository;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query(value = "select  from Doctor d.",nativeQuery = true)
    List<AppointmentDto> getAppointmentsById(@Param("doctor") Long doctorId);
}
