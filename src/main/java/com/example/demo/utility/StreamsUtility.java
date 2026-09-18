package com.example.demo.utility;


import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.Employee;
import lombok.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsUtility {
    public static void main(String[] args) {
        Comparator<Integer>comparator=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        Comparator<Integer>comparator1= Integer::compareTo;
        List<DoctorDto> doctors = new ArrayList<>(List.of(
                new DoctorDto(1L, "Dr. John Smith", "Cardiologist", "john.smith@example.com", LocalDateTime.now().minusDays(10)),
                new DoctorDto(2L, "Dr. Alice Johnson", "Dermatologist", "alice.johnson@example.com", LocalDateTime.now().minusDays(8)),
                new DoctorDto(3L, "Dr. Robert Brown", "Neurologist", "robert.brown@example.com", LocalDateTime.now().minusDays(6)),
                new DoctorDto(4L, "Dr. Emily Davis", "Orthopedic", "emily.davis@example.com", LocalDateTime.now().minusDays(4)),
                new DoctorDto(5L, "Dr. Michael Wilson", "Cardiologist", "michael.wilson@example.com", LocalDateTime.now().minusDays(2))
        ));
        find1(doctors);
        find2(doctors);
        find3(doctors);
        find4(doctors);
        find5(doctors);
        find6(doctors);
        find7();
        find8();
        find9();
        find10();
    }
    public static void find1(List<DoctorDto> doctors){
        Map<String,List<String>>map=doctors.stream().collect(Collectors.groupingBy(dto->dto.getSpecialization(),Collectors.mapping(
                dto->dto.getName(),Collectors.toList()
        )));
        System.out.println(map);
    }

    public static void find2(List<DoctorDto> doctors){
        Comparator<DoctorDto> comparator=new Comparator<DoctorDto>() {
            @Override
            public int compare(DoctorDto o1, DoctorDto o2) {
                return o2.getCreatedAt().compareTo(o1.getCreatedAt());
            }
        };
        Optional<DoctorDto> doctorDto =doctors.stream()
                .max(Comparator.comparing(DoctorDto::getCreatedAt));
        System.out.println(doctorDto.orElse(null));
    }

    public static void find3(List<DoctorDto> doctors){
        Map<String,Long>map=doctors.stream().collect(Collectors.groupingBy(doctorDto -> doctorDto.getSpecialization(),
                Collectors.counting()));
        System.out.println(map);
    }

    public static void find4(List<DoctorDto> doctors){
        Map<String,Long>map=doctors.stream().collect(Collectors.groupingBy(doctorDto -> doctorDto.getSpecialization(),
                Collectors.counting()));
        System.out.println(map);
    }
    public static void find5(List<DoctorDto> doctors){
        Optional<DoctorDto>doctorDto=doctors.stream().sorted((a,b)->b.getCreatedAt().compareTo(a.getCreatedAt()))
                .skip(1)
                .limit(1).findFirst();
        System.out.println(doctorDto.orElse(null));
    }

    public static void find6(List<DoctorDto> doctors){
        Optional<Map.Entry<String, Long>> map=doctors.stream().collect(Collectors.groupingBy(doctorDto -> doctorDto.getSpecialization(),
                Collectors.counting()))
                        .entrySet().stream()
                .max(( e1,e2)->e1.getValue().compareTo(e2.getValue()));
        System.out.println(map.orElse(null));
        Map<Long,String> map2=doctors.stream().collect(Collectors.toMap(doctorDto -> doctorDto.getId(),doctorDto->doctorDto.getName()));
        System.out.println(map2);
    }
    public static void find7(){
        List<String> names = Arrays.asList(
                "Rutik", "Amit", "Rohit", "Amit", "Rutik", "Rohit", "Rohit"
        );
        String result=names.stream().collect(Collectors.groupingBy(s->s,Collectors.counting()))
                .entrySet().stream().collect(Collectors.collectingAndThen(Collectors.maxBy((a,b)->a.getValue().compareTo(b.getValue())),
                        x->x.get().getKey()));
        System.out.println(result);
    }
    public static void find8(){
        //highest salary per department
        List<Employee> employees = Arrays.asList(
                new Employee("A", "IT", 80000),
                new Employee("B", "HR", 60000),
                new Employee("C", "IT", 100000),
                new Employee("D", "HR", 90000),
                new Employee("E", "Finance", 120000)
        );
        Map<String, String> map=employees.stream().collect(Collectors.groupingBy(e->e.getDepartment(),
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                        res->res.get().getName())));
        System.out.println(map);
    }
    public static void find9(){
        //second highest salary per department
        List<Employee> employees = Arrays.asList(
                new Employee("A", "IT", 80000),
                new Employee("B", "HR", 60000),
                new Employee("C", "IT", 100000),
                new Employee("D", "HR", 90000),
                new Employee("E", "Finance", 120000),
                new Employee("J", "Finance", 120000),
                new Employee("F", "IT", 100000)
        );
        Map<String,String>map=employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                Collectors.collectingAndThen(Collectors.toList(),
                        list -> {
                            return list.stream()
                                        .collect(Collectors.groupingBy(Employee::getSalary, Collectors.toList()))
                                        .entrySet().stream()
                                        .sorted((a, b) -> b.getKey().compareTo(a.getKey()))
                                        .skip(1)
                                        .findFirst()
                                        .flatMap(a -> a.getValue().stream().findFirst())
                                        .map(Employee::getName)
                                        .orElse("nothing");
                        })));
        System.out.println(map);
    }

    public static void find10(){
        //Find the department having the highest average salary.
        List<Employee> employees = Arrays.asList(
                new Employee("A", "IT", 80000),
                new Employee("B", "HR", 60000),
                new Employee("C", "IT", 100000),
                new Employee("D", "HR", 90000),
                new Employee("E", "Finance", 120000),
                new Employee("F", "IT", 70000)
        );
        String department=employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().stream().max((a,b)->a.getValue().compareTo(b.getValue()))
                .get().getKey();
        System.out.println(department);

        List<Integer>list= Arrays.asList(1,2,3,56,78);
        int x=Collections.binarySearch(list,56);
        System.out.println(x);
    }
}
