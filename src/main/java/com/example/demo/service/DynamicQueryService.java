package com.example.demo.service;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.PatientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DynamicQueryService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger logger= LoggerFactory.getLogger(DynamicQueryService.class);

    public List<AppointmentDto> executeSelectQueryToDB(){
        // jdbcTemplate.query(sql, rowMapper, v1, v2, v3, v4, v5, v6);
        // Values are bound to placeholders in the same order they appear.
        String sql="select * from appointment where id=? AND reason=?";
        List<AppointmentDto> appointmentDtoList=jdbcTemplate.query(sql,(res, rowNum)->{
            AppointmentDto appointmentDto=new AppointmentDto();
            logger.info("Thre row numer is {}", rowNum);
            appointmentDto.setId(res.getLong("id"));
            appointmentDto.setReason(res.getString("reason"));
            return appointmentDto;
        },2, "Follow-up");
        return appointmentDtoList;
    }

    public List<AppointmentDto> executeSelectQueryToDBBeanMapper(){
        // jdbcTemplate.query(sql, rowMapper, v1, v2, v3, v4, v5, v6);
        // Values are bound to placeholders in the same order they appear.
        String sql="select * from appointment where id=? AND reason=?";
        List<AppointmentDto> appointmentDtoList=jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AppointmentDto.class),2, "Follow-up");
        return appointmentDtoList;
    }

    public PatientDto executeSelectQueryObject(){
        String sql="SELECT * FROM PATIENT WHERE id=?";
        PatientDto patientDto=jdbcTemplate.queryForObject(sql,(res, rowNum) ->
             PatientDto.builder().id(res.getString("id")).name(res.getString("name"))
                     .bloodGroup(res.getString("blood_group")).build()
        ,1);
        return patientDto;
    }

    public int executeInsertQueryObject(){
        String sql = "INSERT INTO patient (name, date_of_birth, email, gender, blood_group) VALUES (?, ?, ?, ?, ?)";
        int res=jdbcTemplate.update(sql,"Rutik",
                LocalDate.of(2000, 5, 10),
                "rutik@gmail.com",
                "Male",
                "O_POSITIVE"
        );
        return res;
    }
}
