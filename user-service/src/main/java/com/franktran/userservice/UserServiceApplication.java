package com.franktran.userservice;

import com.franktran.userservice.user.User;
import com.franktran.userservice.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class UserServiceApplication {

    private final UserRepository userRepository;

    public UserServiceApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            List<User> users = Arrays.asList(
                    new User("Frank", "frank@gmail.com", 1L),
                    new User("Henry", "henry@gmail.com", 2L),
                    new User("Bean", "bean@gmail.com", 3L),
                    new User("Alan", "alan@gmail.com", 3L)
            );
            userRepository.saveAll(users);
        };
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
