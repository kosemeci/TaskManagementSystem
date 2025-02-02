package com.myProject.task_manager.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;
    
    public void sendToMail(String to, String subject, String text){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(text);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom(sender);

        javaMailSender.send(simpleMailMessage);
        System.out.println("Mail başarıyla gönderildi -> " + to);
    }
}
