package com.cartelera.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String password) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(password)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractPassword(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
        .parseClaimsJws(token).getBody();
        return claims.getSubject();
        }

    public Boolean validateToken(String token, String password) {
        final String extractedPassword = extractPassword(token);
        return (extractedPassword.equals(password));
    }
}
