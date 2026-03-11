package com.example.demo.entity;

import com.example.demo.type.BloodGroupType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = {@UniqueConstraint(name="unique_names_dateofbirth", columnNames ={"name", "dateOfBirth"})},
       indexes = {@Index(name="idx_patient_dateOfBirth",columnList = "dateOfBirth")})
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private LocalDate dateOfBirth;

    @Column(unique = true,nullable = false)
    private String email;

    //if want to exclude from the toString method
    @ToString.Exclude
    @Column(check=@CheckConstraint(name = "chk_age", constraint = "gender IN ('Male', 'Female')"))
    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @OneToOne(cascade =CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id")
    @JsonBackReference
    private Insurance insurance; //patient is owning side

    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER, cascade ={CascadeType.REMOVE}, orphanRemoval = true) //inverse side
    @JsonBackReference
    private List<Appointment> appointmentList=new ArrayList<>();

    @OneToOne
    //@MapsId
    private User user;
}