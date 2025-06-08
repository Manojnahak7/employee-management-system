package com.talvoypartners.empmanagement.employeemanagement.controllers;

import com.talvoypartners.empmanagement.employeemanagement.dtos.AuthResponse;
import com.talvoypartners.empmanagement.employeemanagement.dtos.LoginRequest;
import com.talvoypartners.empmanagement.employeemanagement.dtos.RegisterRequest;
import com.talvoypartners.empmanagement.employeemanagement.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(service.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(service.login(req));
    }
}
