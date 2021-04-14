package com.franktran.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodResource {

    @GetMapping("/user-service-fallback")
    public String userServiceFallback() {
        return "User service is taking longer than expected. Please try again later";
    }

    @GetMapping("/department-service-fallback")
    public String departmentServiceFallback() {
        return "Department service is taking longer than expected. Please try again later";
    }
}
