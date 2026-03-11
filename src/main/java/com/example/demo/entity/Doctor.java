package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String name;


    private String specialization;

    @Column(nullable = false,unique = true)
    private String email;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "doctor")
    @JsonBackReference
    private List<Appointment>appointmentList=new ArrayList<>();//inverse side

    @ManyToMany(mappedBy = "doctors")
    @JsonBackReference
    private Set<Department> departments=new HashSet<>();

    @OneToOne
    //@MapsId
    private User user;
}
