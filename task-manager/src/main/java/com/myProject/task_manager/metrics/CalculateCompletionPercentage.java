
package com.myProject.task_manager.metrics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myProject.task_manager.entity.Project;
import com.myProject.task_manager.entity.Status;
import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.exception.BaseException;
import com.myProject.task_manager.exception.ErrorMessage;
import com.myProject.task_manager.exception.MessageType;
import com.myProject.task_manager.repository.ProjectRepository;

@Component
public class CalculateCompletionPercentage {

    @Autowired
    ProjectRepository projectRepository;

    public double calculateCompletionPercentage(Integer projectId){
        Project dbProject = projectRepository.findById(projectId).orElseThrow(
            ()-> new BaseException(new ErrorMessage(MessageType.NOT_EXIST_PROJECT_RECORD,projectId.toString())));
        List<Task> taskList = dbProject.getTask();
        int count = taskList.size();
        int completedCount = 0;
        double completionPercentage;
        for (Task task : taskList) {
            if(task.getStatus()==Status.COMPLETED){
                completedCount+=1;
            }
        }
        completionPercentage = ((double) completedCount/count)*100;
        completionPercentage = Math.round(completionPercentage*100.0)/100.0;
        dbProject.setCompletionPercentage(completionPercentage);
        projectRepository.save(dbProject);
        return completionPercentage;
    }
}