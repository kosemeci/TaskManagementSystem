package com.myProject.task_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.dto.AuthRequest;
import com.myProject.task_manager.entity.UserEntity;
import com.myProject.task_manager.repository.UserEntityRepository;
import com.myProject.task_manager.security.JwtUtil;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityRepository userEntityRepository;

    public AuthController(AuthenticationManager authenticationManager,
                                JwtUtil jwtUtil,
                                PasswordEncoder passwordEncoder,
                                UserEntityRepository userEntityRepository){
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
        this.passwordEncoder=passwordEncoder;
        this.userEntityRepository=userEntityRepository;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        if(userEntityRepository.findByUsername(request.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username is already token.");
        }
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        System.out.println("encodedPassword  : " + encodedPassword);
        user.setPassword(encodedPassword);
        userEntityRepository.save(user);
        return ResponseEntity.ok("User saved successfully :)");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        try {
            // UserEntity user = userEntityRepository.findByUsername(request.getUsername())
            //     .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            // if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            //     throw new BadCredentialsException("Invalid credentialsss");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    }

}