// package com.myProject.task_manager.config;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.myProject.task_manager.model.Task;
// import com.myProject.task_manager.model.User;

// @Configuration
// public class AppConfig {
//     @Bean
//     public List<User> userList(){
//         List<User> userList = new ArrayList<>();
//         userList.add(new User(1,"Alperen","ksmci@gmail.com"));
//         userList.add(new User(2,"Engin","Dere@gmail.com"));
//         userList.add(new User(3,"Ahmet","Demir@gmail.com"));
//         userList.add(new User(3,"Yusuf","sinyorfc@gmail.com"));
//         return userList;
//     }

//     @Bean
//     public List<Task> taskList(){
//         List<Task> taskList = new ArrayList<>();
//         taskList.add(new Task(1,"Have to create new classes",false,"2024/10/10","2024/10/10"));
//         taskList.add(new Task(2,"Have to deleted old view",false,"2024/10/10","2024/10/10"));
//         taskList.add(new Task(3,"Have to edited front",false,"2024/10/10","2024/10/10"));
//         taskList.add(new Task(4,"Have to create new object",false,"2024/10/10","2024/10/10"));
//         return taskList;
//     }
// }
