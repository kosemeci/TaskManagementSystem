package com.myProject.task_manager.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.myProject.task_manager"})
@EntityScan(basePackages = {"com.myProject.task_manager"})
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.myProject.task_manager"})
public class TaskManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
