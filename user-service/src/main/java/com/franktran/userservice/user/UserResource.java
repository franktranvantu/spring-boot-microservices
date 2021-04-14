package com.franktran.userservice.user;

import com.franktran.userservice.department.Department;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

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

    @PostMapping
    public void createUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @RequestBody User user) {
        User existUser = getUserById(id);
        if (Objects.nonNull(existUser)) {
            existUser.setName(user.getName());
            existUser.setEmail(user.getEmail());
            existUser.setDepartmentId(user.getDepartmentId());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        User existUser = getUserById(id);
        if (Objects.nonNull(existUser)) {
            userRepository.deleteById(id);
        }
    }
}
