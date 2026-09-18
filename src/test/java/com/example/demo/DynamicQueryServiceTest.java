package com.example.demo;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.PatientDto;
import com.example.demo.service.DynamicQueryService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@SpringBootTest
public class DynamicQueryServiceTest {

    @Autowired
    DynamicQueryService dynamicQueryService;

    private final Logger logger= LoggerFactory.getLogger(DynamicQueryServiceTest.class);

    @Test
    public void method1(){
        List<AppointmentDto>appointmentDtoList=dynamicQueryService.executeSelectQueryToDB();
        appointmentDtoList.forEach(appointmentDto ->{
            logger.info("this is the returned data {}", appointmentDto);
        });
    }

    @Test
    public void method2(){
        PatientDto patientDto =dynamicQueryService.executeSelectQueryObject();
        logger.info("this is the returned object for the object {}", patientDto);
    }


    @Test
    public void method3(){
        int res =dynamicQueryService.executeInsertQueryObject();
        logger.info("this is the returned response for the insert query {}", res);
    }

    @Test
    public void method4(){
        List<AppointmentDto>appointmentDtoList=dynamicQueryService.executeSelectQueryToDBBeanMapper();
        appointmentDtoList.forEach(appointmentDto ->{
            logger.info("this is the returned data for baenmapper{}", appointmentDto);
        });
    }

}
