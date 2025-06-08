package com.talvoypartners.empmanagement.employeemanagement.controllers;

import com.talvoypartners.empmanagement.employeemanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final UserService userService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> req) {
        String email = req.get("email");
        String resetToken = userService.generateResetToken(email);
        String resetLink = "http://localhost:9090/api/auth/reset-password?token=" + resetToken;

        return ResponseEntity.ok(Map.of("message", "Reset link generated", "resetLink", resetLink));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> req) {
        String token = req.get("token");
        String newPassword = req.get("newPassword");

        userService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }
}
