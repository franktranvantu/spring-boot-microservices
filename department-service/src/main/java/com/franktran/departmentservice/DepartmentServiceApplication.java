package com.franktran.departmentservice;

import com.franktran.departmentservice.department.Department;
import com.franktran.departmentservice.department.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DepartmentServiceApplication {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceApplication(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DepartmentServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            List<Department> departments = Arrays.asList(
                    new Department("Member"),
                    new Department("BO"),
                    new Department("API"),
                    new Department("Agent")
            );
            departmentRepository.saveAll(departments);
        };
    }

}
