package com.talvoypartners.empmanagement.employeemanagement.controllers;

import com.talvoypartners.empmanagement.employeemanagement.config.JwtUtil;
import com.talvoypartners.empmanagement.employeemanagement.entity.Employee;
import com.talvoypartners.empmanagement.employeemanagement.entity.User;
import com.talvoypartners.empmanagement.employeemanagement.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")

@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // Get profile of logged-in user (return Employee details including User info if needed)
    @GetMapping("/profile")
    public ResponseEntity<Employee> getProfile(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        String email = jwtUtil.extractUsername(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Employee employee = user.getEmployee();
        if (employee == null) {
            throw new RuntimeException("Employee details not found for user");
        }

        // Optional: To avoid sending sensitive info like password
        if (employee.getUser() != null) {
            employee.getUser().setPassword(null);
        }

        return ResponseEntity.ok(employee);
    }

    // Update profile (update Employee details)
    @PutMapping("/profile")
    public ResponseEntity<Employee> updateProfile(HttpServletRequest request, @RequestBody Employee updatedEmployee) {
        String token = extractTokenFromRequest(request);
        String email = jwtUtil.extractUsername(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Employee employee = user.getEmployee();
        if (employee == null) {
            throw new RuntimeException("Employee details not found for user");
        }

        // Update employee fields from updatedEmployee
        employee.setName(updatedEmployee.getName());
        employee.setMobile(updatedEmployee.getMobile());
        employee.setAddress(updatedEmployee.getAddress());
        employee.setOffice(updatedEmployee.getOffice());

        // Save user (assuming cascade ALL on Employee)
        userRepository.save(user);

        return ResponseEntity.ok(employee);
    }

    // Helper method to extract JWT token from Authorization header
    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new RuntimeException("JWT Token missing");
    }
}
