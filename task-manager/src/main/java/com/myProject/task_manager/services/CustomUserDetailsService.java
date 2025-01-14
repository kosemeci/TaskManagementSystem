package com.myProject.task_manager.services;


import java.util.ArrayList;

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
        return new org.springframework.security.core.userdetails.User(user.getMailAdress(), user.getPassword(), new ArrayList<>());
    }

}