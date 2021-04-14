package com.franktran.userservice.user;

import com.franktran.userservice.department.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDepartment {

    private User user;
    private Department department;
}
