package com.talvoypartners.empmanagement.employeemanagement.services;

import com.talvoypartners.empmanagement.employeemanagement.entity.User;
import com.talvoypartners.empmanagement.employeemanagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // In-memory store for reset tokens, production me DB use karo
    private Map<String, String> resetTokens = new HashMap<>();

    public String generateResetToken(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        // Generate random token
        String token = UUID.randomUUID().toString();
        resetTokens.put(token, email);  // store token and email
        return token;
    }

    public void resetPassword(String token, String newPassword) {
        String email = resetTokens.get(token);
        if (email == null) {
            throw new RuntimeException("Invalid or expired token");
        }
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Token use ho gaya, delete kar do
        resetTokens.remove(token);
    }
}
