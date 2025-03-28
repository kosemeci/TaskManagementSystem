package com.myProject.task_manager.services;


import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.entity.User;
import com.myProject.task_manager.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mailAddress) {
        User user = userRepository.findByMailAdress(mailAddress).orElseThrow(()->new UsernameNotFoundException(mailAddress+"User not found."));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        System.out.println(user.getRole().name());

        return new org.springframework.security.core.userdetails.User(user.getMailAdress(), user.getPassword(), authorities);
    }

}