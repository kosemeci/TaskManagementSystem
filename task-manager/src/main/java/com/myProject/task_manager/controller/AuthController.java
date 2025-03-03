package com.myProject.task_manager.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.task_manager.dto.DtoUserIU;
import com.myProject.task_manager.dto.DtoUserResponse;
import com.myProject.task_manager.dto.LoginRequest;
import com.myProject.task_manager.entity.User;
// import com.myProject.task_manager.mail.MailService;
import com.myProject.task_manager.repository.UserRepository;
import com.myProject.task_manager.security.JwtUtil;
import com.myProject.task_manager.services.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    // @Autowired
    // private MailService mailService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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
        // mailService.sendToMail(user.getMailAdress(), "HOSGELDİN DUYURUSU", "Sinyor LTD.ŞTİ ' ye hoş geldin! ");
        return ResponseEntity.ok("User saved successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request, HttpServletResponse response) {
    try {
        // Kullanıcıyı yükle (Spring Security tarafından yönetiliyor)
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getMailAdress());

        if (userDetails == null) {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı.");
        }

        // Kullanıcı doğrulama işlemi
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getMailAdress(), request.getPassword()
        ));

        // JWT Token oluştur
        String token = jwtUtil.generateToken(request.getMailAdress());

        // HttpOnly Cookie oluştur
        ResponseCookie cookie = ResponseCookie.from("auth_token", token)
                .httpOnly(true)   // JavaScript erişemez
                .secure(true)     // HTTPS üzerinden çalışmalı
                .sameSite("Lax") // CSRF koruması sağlar // Çapraz site çerezleri için gerekli
                .path("/")        // Tüm uygulama için geçerli
                .maxAge(Duration.ofDays(1)) // 1 gün süresi var
                .build();

        // Çerezi HTTP yanıtına ekle
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok("Login succesfull");

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı bulunamadı.");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hatalı şifre, lütfen tekrar deneyin.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Giriş yapılamadı, lütfen tekrar deneyin.");
        }
    }

    @GetMapping("/fetch/id")
    public ResponseEntity<DtoUserResponse> getUserIdByMail(@RequestParam String mail) {
        return userRepository.findByMailAdress(mail)
                .map(user -> new DtoUserResponse(user.getId(), user.getRole()))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @ExceptionHandler(Exception.class)
public ResponseEntity<String> handleException(Exception e) {
    e.printStackTrace(); // Hata detaylarını konsola yazdır
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Hata oluştu: " + e.getMessage());
}

}