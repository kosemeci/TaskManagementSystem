package com.myProject.task_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.security.JwtUtil;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping("/hello")
    public ResponseEntity<String> sayHi(@RequestHeader("Authorization") String token) {
        if(token!=null && token.startsWith("Bearer ")){
            String jwtToken = token.substring(7);
            try {
                if(jwtUtil.isTokenValidate(jwtToken)){
                    String username = jwtUtil.extractUsername(jwtToken);
                    return ResponseEntity.ok("Selamun aleyküm "+username);
                }else{
                    return ResponseEntity.status(403).body("Acces denied.");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("invalid token");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED.");
    }
}
