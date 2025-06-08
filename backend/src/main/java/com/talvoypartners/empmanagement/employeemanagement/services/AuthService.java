package com.talvoypartners.empmanagement.employeemanagement.services;

import com.talvoypartners.empmanagement.employeemanagement.config.JwtUtil;
import com.talvoypartners.empmanagement.employeemanagement.dtos.AuthResponse;
import com.talvoypartners.empmanagement.employeemanagement.dtos.LoginRequest;
import com.talvoypartners.empmanagement.employeemanagement.dtos.RegisterRequest;
import com.talvoypartners.empmanagement.employeemanagement.entity.Employee;
import com.talvoypartners.empmanagement.employeemanagement.entity.EmployeeAddress;
import com.talvoypartners.empmanagement.employeemanagement.entity.Office;
import com.talvoypartners.empmanagement.employeemanagement.entity.User;
import com.talvoypartners.empmanagement.employeemanagement.repositories.EmployeeRepository;
import com.talvoypartners.empmanagement.employeemanagement.repositories.OfficeRepository;
import com.talvoypartners.empmanagement.employeemanagement.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final EmployeeRepository empRepo;
    private final OfficeRepository officeRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        EmployeeAddress addr = new EmployeeAddress(null, req.getPermanentAddress(), req.getCorrespondenceAddress());
        Office office = officeRepo.save(new Office(null, req.getOfficeName(), req.getOfficeAddress(), null));
        Employee emp = new Employee(null, req.getName(), req.getMobile(), addr, office, user);
        user.setEmployee(emp);

        userRepo.save(user);

        // Pass userDetails + name to generate token
        return new AuthResponse(jwtUtil.generateToken(user, req.getName()));
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepo.findByEmail(req.getEmail()).orElseThrow();

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Extract name from employee and pass to JWT
        String name = user.getEmployee().getName();

        return new AuthResponse(jwtUtil.generateToken(user, name));
    }
}
