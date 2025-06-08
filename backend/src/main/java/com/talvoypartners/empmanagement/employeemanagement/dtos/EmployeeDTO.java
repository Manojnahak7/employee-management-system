package com.talvoypartners.empmanagement.employeemanagement.dtos;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String name;
    private String email;
    private String mobile;
    private String permanentAddress;
    private String correspondenceAddress;
    private String officeName;
    private String officeAddress;
}
