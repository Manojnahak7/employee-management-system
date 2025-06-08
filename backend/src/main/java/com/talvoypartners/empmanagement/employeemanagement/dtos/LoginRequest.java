package com.talvoypartners.empmanagement.employeemanagement.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
