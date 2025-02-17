package com.myProject.task_manager.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.myProject.task_manager.dto.DtoTask;
import com.myProject.task_manager.dto.DtoUser;
import com.myProject.task_manager.dto.DtoUserIU;
import com.myProject.task_manager.entity.Role;
import com.myProject.task_manager.entity.Status;
import com.myProject.task_manager.entity.Task;
import com.myProject.task_manager.entity.User;
import com.myProject.task_manager.exception.BaseException;
import com.myProject.task_manager.exception.ErrorMessage;
import com.myProject.task_manager.exception.MessageType;
import com.myProject.task_manager.mail.MailService;
import com.myProject.task_manager.repository.TaskRepository;
import com.myProject.task_manager.repository.UserRepository;
import com.myProject.task_manager.services.IUserService;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MailService mailService;
    
    @Override
    public DtoUser saveUser(DtoUserIU dtoUserIU) {
        DtoUser response = new DtoUser();
        User user = new User();
        BeanUtils.copyProperties(dtoUserIU, user);
        User dbUser = userRepository.save(user);
        BeanUtils.copyProperties(dbUser, response);
        return response;
    }

    @Override
    public List<DtoUser> getUserList() {
        List<User> userList = userRepository.findAll();
        List<DtoUser> dtoUserList = new ArrayList<>();
        for (User user : userList) {
            List<DtoTask> dtoTaskList = new ArrayList<>();
            DtoUser dtoUser = new DtoUser();    
            BeanUtils.copyProperties(user, dtoUser);
            for (Task task : user.getTask()) {
                DtoTask dtoTask = new DtoTask();
                dtoTask.setId(task.getId());
                dtoTask.setDescription(task.getDescription());
                dtoTask.setPriority(task.getPriority());
                dtoTask.setDeadline(task.getDeadline());
                dtoTask.setAssignedDate(task.getAssignedDate());
                dtoTask.setCompletionDate(task.getCompletionDate());
                dtoTask.setStatus(task.getStatus());
                dtoTask.setTaskTitle(task.getTaskTitle());
                dtoTask.setCreatedDate(task.getCreatedDate());
                dtoTaskList.add(dtoTask);
            }
            dtoUser.setTask(dtoTaskList);
            dtoUserList.add(dtoUser);
        }
        return dtoUserList;
    }

    @Override
    public DtoUser getUserById(Integer id) {
    String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

    Optional<User> optional = userRepository.findById(id);

    if (optional.isPresent()) {
        User user = optional.get();

        // Kullanıcının sadece kendi kaydına erişebilmesi için mail adresini kontrol ediyoruz
        if (user.getMailAdress() != null && user.getMailAdress().equals(currentUsername)) {
            DtoUser dtoUser = new DtoUser();
            List<DtoTask> dtoTaskList = new ArrayList<>();

            // Kullanıcı bilgilerini DTO'ya kopyalıyoruz
            BeanUtils.copyProperties(user, dtoUser);

            // Kullanıcının görevleri varsa, görevleri DTO'ya kopyalıyoruz
            if (user.getTask() != null) {
                for (Task task : user.getTask()) {
                    DtoTask dtoTask = new DtoTask();
                    dtoTask.setId(task.getId());
                    dtoTask.setDescription(task.getDescription());
                    dtoTask.setPriority(task.getPriority());
                    dtoTask.setDeadline(task.getDeadline());
                    dtoTask.setAssignedDate(task.getAssignedDate());
                    dtoTask.setCompletionDate(task.getCompletionDate());
                    dtoTask.setStatus(task.getStatus());
                    dtoTask.setTaskTitle(task.getTaskTitle());
                    dtoTask.setCreatedDate(task.getCreatedDate());
                    dtoTaskList.add(dtoTask);
                }
                dtoUser.setTask(dtoTaskList);
            }

            return dtoUser;
        } else {
            throw new BaseException(new ErrorMessage(MessageType.NOT_EXIST_PROJECT_RECORD, "You are not authorized to access this user."));
        }
    } else {
        throw new BaseException(new ErrorMessage(MessageType.NOT_EXIST_USER_RECORD, id.toString()));
    }
}

    @Override
    public DtoUser changeRole(Integer id, String role) {
        Optional<User> optional = userRepository.findById(id);
        DtoUser dtoUser = new DtoUser();
        if(optional.isPresent()){
            User user = optional.get();
            Role enumRole = Role.valueOf(role.toUpperCase());
            user.setRole(enumRole);
            userRepository.save(user);
            BeanUtils.copyProperties(user, dtoUser); 
            String text = "Tebrikler! Sınyor Ltd. Şti ' de artık '" + role +"' rolündesiniz. İyi çalışmalar.";
            mailService.sendToMail(user.getMailAdress(), "NEW ROLE", text);
        }
        return dtoUser;
    }

    @Override
    public DtoUser chooseTask(Integer userId, Integer taskId) {
        //taski user a atama işlemini ya user kendi yapacak ya admin atayacak.
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(userId)
                        .orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.NOT_EXIST_USER_RECORD,userId.toString())));
        Task task = taskRepository.findById(taskId)
                        .orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.NOT_EXIST_TASK_RECORD,userId.toString())));
        User dbCurrentUser = userRepository.findByMailAdress(currentUser)
                        .orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.NOT_EXIST_USER_RECORD,userId.toString())));
        if((currentUser != null && user.getMailAdress().equals(currentUser))||dbCurrentUser.getRole()==Role.ADMIN){
            DtoUser dtoUser = new DtoUser();
            if(task.getUser() == null){// yani task bir kullanıcıya atanmamışsa
                task.setUser(user);
                task.setAssignedDate(LocalDate.now());
                task.setStatus(Status.IN_PROGRESS);
                taskRepository.save(task);
                List<Task> taskOfUser = user.getTask();
                List<DtoTask> dtoTaskList = new ArrayList<>();
                BeanUtils.copyProperties(user, dtoUser);
                for (Task tasking : taskOfUser) {
                    DtoTask dtoTask = new DtoTask();
                    dtoTask.setAssignedDate(tasking.getAssignedDate());
                    dtoTask.setDescription(tasking.getDescription());
                    dtoTask.setId(tasking.getId());
                    dtoTask.setPriority(tasking.getPriority());
                    dtoTask.setTaskTitle(tasking.getTaskTitle());
                    dtoTask.setStatus(tasking.getStatus());
                    dtoTask.setCompletionDate(tasking.getCompletionDate());
                    dtoTask.setCreatedDate(tasking.getCreatedDate());
                    dtoTask.setDeadline(task.getDeadline());
                    dtoTaskList.add(dtoTask);
                }
                dtoUser.setTask(dtoTaskList);
            }
            // String text = "Yeni taskiniz '" + task.getTaskTitle() +"' başarıyla atanmıştır. Kolay gelsin :)";
            // mailService.sendToMail(currentUser, "NEW CHOOSING TASK", text);
            return dtoUser;   
        }
        else{
            throw new BaseException(new ErrorMessage(MessageType.UNAUTHORIZED_ACCESS,null));
        }
    }

}