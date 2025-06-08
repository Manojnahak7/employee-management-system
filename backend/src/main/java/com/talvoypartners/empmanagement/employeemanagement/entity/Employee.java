package com.talvoypartners.empmanagement.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mobile;

    @OneToOne(cascade = CascadeType.ALL)
    private EmployeeAddress address;

    @ManyToOne(cascade = CascadeType.ALL)
    private Office office;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

