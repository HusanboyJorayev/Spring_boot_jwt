package com.example.spring_boot_jwt.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenUtil {
    private final static String SECRET_KEY = "1234567898765asdfghj876543234567890fdsdfgh98765434567";

    public String generateToken(@NonNull String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(signKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key signKey() {
        byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

    public boolean isValid(@NonNull String token) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(signKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsername(@NonNull String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }
}

