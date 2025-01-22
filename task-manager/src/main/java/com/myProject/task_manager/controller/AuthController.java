package com.myProject.task_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.dto.DtoUserIU;
import com.myProject.task_manager.dto.LoginRequest;
import com.myProject.task_manager.entity.User;
import com.myProject.task_manager.repository.UserRepository;
import com.myProject.task_manager.security.JwtUtil;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                                JwtUtil jwtUtil,
                                PasswordEncoder passwordEncoder,
                                UserRepository userRepository){
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid DtoUserIU dtoUserIU) {
        if(userRepository.findByMailAdress(dtoUserIU.getMailAdress()).isPresent()){
            return ResponseEntity.badRequest().body("Username is already token.");
        }
        String encodedPassword = passwordEncoder.encode(dtoUserIU.getPassword());
        User user = new User();
        user.setMailAdress(dtoUserIU.getMailAdress());
        user.setBirthOfDate(dtoUserIU.getBirthOfDate());
        user.setFirstName(dtoUserIU.getFirstName());
        user.setLastName(dtoUserIU.getLastName());
        user.setPosition(dtoUserIU.getPosition());
        user.setTelNumber(dtoUserIU.getTelNumber());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return ResponseEntity.ok("User saved successfully :)");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMailAdress(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hatalı şifre, lütfen tekrar deneyin.");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı bulunamadı.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Giriş yapılamadı, lütfen tekrar deneyin.");
        }

        String token = jwtUtil.generateToken(request.getMailAdress());
        return ResponseEntity.ok(token);
    }
}