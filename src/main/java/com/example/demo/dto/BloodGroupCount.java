package com.example.demo.dto;

import com.example.demo.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class BloodGroupCount {
    private Long count;
    private BloodGroupType bloodGroup;
}