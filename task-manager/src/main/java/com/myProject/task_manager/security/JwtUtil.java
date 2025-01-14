package com.myProject.task_manager.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    public static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final int validity = 90*60*1000;

    public String generateToken(String username){
        
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))    
                .setExpiration(new Date(System.currentTimeMillis()+validity))
                .signWith(secretKey)
                .compact();
    }

    public boolean isTokenValidate(String token){
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isExpired(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
}


    public String extractUsername(String token) { 
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey) // secretKey güvenli anahtar olmalı
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        return claims.getSubject();
    }
}