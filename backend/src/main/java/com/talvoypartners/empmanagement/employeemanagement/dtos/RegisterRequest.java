package com.talvoypartners.empmanagement.employeemanagement.dtos;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String mobile;
    private String permanentAddress;
    private String correspondenceAddress;
    private String officeName;
    private String officeAddress;
}
