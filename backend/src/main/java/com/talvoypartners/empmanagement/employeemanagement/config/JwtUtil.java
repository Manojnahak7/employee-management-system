package com.talvoypartners.empmanagement.employeemanagement.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "9f0Z2g5X7iLb4oFp8YxRpR4eT/ERJo9Ghbqq2OaYnpE=";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));
    }

    public String generateToken(UserDetails user,String name) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("name", name)

                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 259200000)) // 3 days
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validate(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername());
    }
}
