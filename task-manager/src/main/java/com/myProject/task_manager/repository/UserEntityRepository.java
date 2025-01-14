package com.myProject.task_manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProject.task_manager.entity.UserEntity;


@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Integer>{
    Optional<UserEntity> findByUsername(String username);
}
