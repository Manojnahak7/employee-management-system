package com.talvoypartners.empmanagement.employeemanagement.repositories;

import com.talvoypartners.empmanagement.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserEmail(String email);
}