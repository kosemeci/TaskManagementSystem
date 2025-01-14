package com.myProject.task_manager.services;


import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.entity.UserEntity;
import com.myProject.task_manager.repository.UserEntityRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserEntityRepository userEntityRepository;

    public CustomUserDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository=userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userEntityRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username+"User not found."));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}