package com.franktran.userservice.user;

import com.franktran.userservice.department.Department;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserResource(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/user-department/{id}")
    public UserDepartment getUserAndDepartmentByUserId(@PathVariable long id) {
        User user = getUserById(id);
        Department department = restTemplate.getForObject("http://department-service/departments/" + user.getDepartmentId(), Department.class);
        UserDepartment ud = new UserDepartment();
        ud.setUser(user);
        ud.setDepartment(department);
        return ud;
    }
}
