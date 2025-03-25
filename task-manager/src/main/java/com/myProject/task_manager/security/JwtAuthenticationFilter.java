package com.myProject.task_manager.security;

import java.io.IOException;
import java.util.Arrays;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.myProject.task_manager.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected boolean shouldNotFilter(@SuppressWarnings("null") HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        // Login ve Register endpointlerini filtreleme
        return path.equals("/auth/login");
    }

    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    //         throws ServletException, IOException {

    //     // 🍪 JWT'yi Cookie'den al
    //     String token = null;
    //     if (request.getCookies() != null) {
    //         token = Arrays.stream(request.getCookies())
    //                 .filter(cookie -> "auth_token".equals(cookie.getName())) // auth_token adındaki çerezi al
    //                 .map(Cookie::getValue)
    //                 .findFirst()
    //                 .orElse(null);
    //     }

    //     // Eğer token mevcutsa doğrula
    //     if (token != null) {
    //         String username = jwtUtil.extractUsername(token);

    //         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    //             UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

    //             if (jwtUtil.isTokenValidate(token)) {
    //                 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
    //                         userDetails, null, userDetails.getAuthorities());
    //                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    //                 // Kullanıcıyı SecurityContext'e ekle
    //                 SecurityContextHolder.getContext().setAuthentication(authToken);
    //             }
    //         }
    //     }

    //     // Filtre zincirine devam et
    //     filterChain.doFilter(request, response);
    // }

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
        throws ServletException, IOException {

    logger.info(" Yeni istek alindi: {}", request.getRequestURI()); 

    String token = null;
    if (request.getCookies() != null) {
        token = Arrays.stream(request.getCookies())
                .filter(cookie -> "auth_token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    if (token == null) {
        logger.warn(" JWT Token bulunamadi!");
    } else {
        logger.info(" JWT Token alindi: {}", token);

        String username = jwtUtil.extractUsername(token);
        logger.info(" user name: {}", username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.isTokenValidate(token)) {
                logger.info(" JWT Token . user login...");
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                logger.warn(" JWT Token nooooooo!");
            }
        }
    }

    filterChain.doFilter(request, response);
}

}
