package com.myProject.task_manager.security;

import java.io.IOException;

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

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;


    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService,JwtUtil jwtUtil){
        this.customUserDetailsService=customUserDetailsService;
        this.jwtUtil=jwtUtil;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        // Bu yolları filtrelemeyeceğiz
        return path.equals("/auth/login") || path.equals("/auth/register");
    }
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    String bearerToken = request.getHeader("Authorization");
            if(bearerToken!=null && bearerToken.startsWith("Bearer ")){
                bearerToken = bearerToken.substring(7);
                String username = jwtUtil.extractUsername(bearerToken);
                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                    if(jwtUtil.isTokenValidate(bearerToken)){
                        UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(userDetails, 
                                                                        null,null);
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
    
    filterChain.doFilter(request, response);
}

    
}