package com.example.demo.repository;

import com.example.demo.dto.BloodGroupCount;
import com.example.demo.entity.Patient;
import com.example.demo.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByName(String name);
    Patient findByDateOfBirthOrEmail(LocalDate localDate, String email);

    List<Patient> findByDateOfBirthBetween(LocalDate parse, LocalDate parse1);

    List<Patient> findByDateOfBirthAfter(LocalDate parse);

    List<Patient> findByIdGreaterThan(int i);

    List<Patient> findByIdGreaterThanEqual(int i);

    List<Patient> findByIdLessThan(int i);

    List<Patient> findByIdLessThanEqual(int i);

    Page<Patient> findByDateOfBirthBefore(LocalDate parse,Pageable pageable);

    List<Patient> findByCreatedDateIsNull();

    List<Patient> findByIdIsNotNull();

    List<Patient> findByNameLike(String s);

    List<Patient> findByNameNotLike(String s);

    List<Patient> findByNameStartingWith(String s);

    List<Patient> findByNameEndingWith(String s);

    List<Patient> findAllByOrderByNameDesc();

    Page<Patient> findAllByOrderByName(Pageable pageable);

    List<Patient> findByNameIn(List<String> strings);

    List<Patient> findByNameNotIn(List<String> strings);

    List<Patient> findByNameIgnoreCase(String rohitPatil);

    //JPQL Query
    @Query("SELECT p FROM Patient p WHERE p.bloodGroup=:blood")
    List<Patient>findByBloodGroup(@Param("blood") BloodGroupType bloodGroup);

    @Query("SELECT p FROM Patient p WHERE p.dateOfBirth>:dob")
    List<Patient>findByDateOfBirth(@Param("dob") LocalDate dob);
    //Have blood group A_POSITIVE
    //We born before 1st January 2000
    //Sort the results by name in ascending order
    @Query("SELECT p FROM Patient p WHERE p.bloodGroup=:blood AND p.dateOfBirth>:dob Order By p.name")
    List<Patient>findByBloodGroupDateOfBirthAsc(@Param("blood") BloodGroupType bloodGroup , @Param("dob") LocalDate dob);

    @Query("SELECT COUNT(*), p.bloodGroup FROM Patient p GROUP BY p.bloodGroup ")
    List<BloodGroupCount>findByGroupBy();

    @Query(value = "SELECT * FROM patient ORDER BY Date_Of_Birth DESC",nativeQuery = true)
    List<Patient> findAllBySortedByDateOfBirth();

    @Transactional
    @Modifying
    @Query("UPDATE  Patient p SET p.name=:name where p.id=:id")
    int updatePatientNameById(@Param("name")String name, @Param("id")Long id);

    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointmentList a LEfT JOIN FETCH a.doctor")
    List<Patient> findAllPatientWithAppointments();
}