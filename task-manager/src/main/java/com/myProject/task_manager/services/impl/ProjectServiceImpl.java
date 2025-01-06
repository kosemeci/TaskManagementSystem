package com.myProject.task_manager.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.entity.Project;
import com.myProject.task_manager.repository.ProjectRepository;
import com.myProject.task_manager.services.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    
    @Override
    public Project findTaskById(int id) {
        Optional <Project> optional = projectRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        Project response = optional.get();
        return response;
    }
    

}
