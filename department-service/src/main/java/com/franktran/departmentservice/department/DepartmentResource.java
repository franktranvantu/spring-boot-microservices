package com.franktran.departmentservice.department;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/departments")
public class DepartmentResource {

    private final DepartmentRepository departmentRepository;

    public DepartmentResource(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public void createDepartment(@RequestBody Department department) {
        departmentRepository.save(department);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable long id, @RequestBody Department department) {
        Department existDepartment = getDepartmentById(id);
        if (Objects.nonNull(existDepartment)) {
            existDepartment.setName(department.getName());
            departmentRepository.save(existDepartment);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable long id) {
        departmentRepository.deleteById(id);
    }
}
