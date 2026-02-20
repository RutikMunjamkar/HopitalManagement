package com.example.demo;
import com.example.demo.dto.BloodGroupCount;
import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.PatientService;
import com.example.demo.type.BloodGroupType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

   // @Test
    public void testFindDerivedQueries(){
        //DerivedQueries
        // Query Methods in JPA
        Patient pt2=patientRepository.findByDateOfBirthOrEmail(LocalDate.parse("1997-01-03"), "rohit.patil@gmail.com");
        List<Patient> pt3list =patientRepository.findByDateOfBirthBetween(LocalDate.parse("1998-01-01"), LocalDate.parse("1998-12-31"));
        List<Patient> pt4list=patientRepository.findByDateOfBirthAfter(LocalDate.parse("1998-12-31"));
        List<Patient>pt5list=patientRepository.findByIdGreaterThan(2);
        List<Patient>pt6list=patientRepository.findByIdGreaterThanEqual(3);
        List<Patient>pt7list=patientRepository.findByIdLessThan(2);
        List<Patient>pt8list=patientRepository.findByIdLessThanEqual(2);
        List<Patient>pt10list=patientRepository.findByCreatedDateIsNull();
        List<Patient>pt11list=patientRepository.findByIdIsNotNull();
        List<Patient>pt12list=patientRepository.findByNameLike("%ti_");
        List<Patient>pt13list=patientRepository.findByNameNotLike("%ti_");
        List<Patient>ptl14list=patientRepository.findByNameStartingWith("Amit");
        List<Patient>pt15list=patientRepository.findByNameEndingWith("mukh");
        List<Patient>pt16list=patientRepository.findAllByOrderByNameDesc();
        //pagination on the data
        List<Patient>pt18list=patientRepository.findByNameIn(List.of("Rohit Patil", "Sneha Kulkarni"));
        List<Patient>pt19list=patientRepository.findByNameNotIn(List.of("Rohit Patil", "Sneha Kulkarni"));
        List<Patient>pt20list=patientRepository.findByNameIgnoreCase("rohit patil");
        List<Patient>pt21list=patientRepository.findByBloodGroup(BloodGroupType.O_POSITIVE);
//        System.out.println(pt21list);
//        System.out.println(pt21list.size());
    }
   // @Test
    public void testFindJPQLQueries(){
        List<Patient>list1=patientRepository.findByBloodGroup(BloodGroupType.O_POSITIVE);
        List<Patient>list2=patientRepository.findByDateOfBirth(LocalDate.parse("1997-01-03"));
        List<Patient>list3=patientRepository.findByBloodGroupDateOfBirthAsc(BloodGroupType.A_POSITIVE,LocalDate.of(2000, 8, 15));
        List<BloodGroupCount>list4=patientRepository.findByGroupBy(); // this is called the projection
        int val1=patientRepository.updatePatientNameById("rutik munjamkar", 7l);
        System.out.println(list4);
    }
    //@Test
    public void testNativeQuery(){
        List<Patient>list1=patientRepository.findAllBySortedByDateOfBirth();
        System.out.println(list1);
    }
    //@Test
    public void testPagination(){
        Page<Patient> pt17list=patientRepository.findAllByOrderByName(PageRequest.of(0,2));
        Page<Patient>pt9list=patientRepository.findByDateOfBirthBefore(LocalDate.parse("1998-05-13"),PageRequest.of(2
                ,3, Sort.by("name", "id")));
        for(Patient p:pt9list){
            System.out.println(p);
        }
    }

    @Test
    public void patientTest(){
        List<Patient>listOfPatient=patientRepository.findAllPatientWithAppointments();
    }
}
