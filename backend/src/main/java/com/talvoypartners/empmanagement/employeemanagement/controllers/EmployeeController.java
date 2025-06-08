package com.talvoypartners.empmanagement.employeemanagement.controllers;

import com.talvoypartners.empmanagement.employeemanagement.dtos.EmployeeDTO;
import com.talvoypartners.empmanagement.employeemanagement.dtos.RegisterRequest;
import com.talvoypartners.empmanagement.employeemanagement.entity.Employee;
import com.talvoypartners.empmanagement.employeemanagement.entity.User;
import com.talvoypartners.empmanagement.employeemanagement.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> all() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/me")
    public ResponseEntity<Employee> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(service.getMyDetails(user.getEmail()));
    }

    @PutMapping("/me")
    public ResponseEntity<String> update(@AuthenticationPrincipal User user, @RequestBody RegisterRequest req) {
        service.updateDetails(user.getEmail(), req);
        return ResponseEntity.ok("Updated");
    }
}


