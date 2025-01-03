package com.myProject.task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myProject.task_manager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{
    
}
