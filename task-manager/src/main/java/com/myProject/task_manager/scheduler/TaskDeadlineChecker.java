package com.myProject.task_manager.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myProject.task_manager.entity.Status;
import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.repository.TaskRepository;


@Component
public class TaskDeadlineChecker {

    @Autowired 
    TaskRepository taskRepository;

    @Scheduled(cron = "00 00 00 * * *")
    public void taskDeadlineControl(){
        List<Task> taskList =  taskRepository.findAll();
        for (Task task : taskList) {
            if(task.getDeadline().isBefore(LocalDate.now()) && task.getStatus()!=Status.COMPLETED){
                task.setStatus(Status.FAILED);
                taskRepository.save(task);
                //System.out.println(task.getId()+ " is taken fail state.");
            }
        }
    }
}