package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 50)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    private Doctor headDoctor;

    @ManyToMany
    @JoinTable(name = "department_dcotors_table",joinColumns = @JoinColumn(name = "dpt_id"),
               inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Doctor> doctors=new HashSet<>();
}