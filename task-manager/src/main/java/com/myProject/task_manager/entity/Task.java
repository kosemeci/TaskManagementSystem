package com.myProject.task_manager.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "task_title",length = 30)
    private String taskTitle;

    @Column(name = "description",columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(name= "status")
    private String status="Pending";
    // PENDING: Görev oluşturuldu ancak henüz başlatılmadı.
    // IN_PROGRESS: Görev üzerinde çalışılıyor.
    // COMPLETED: Görev başarıyla tamamlandı.
    // FAILED: Görev başarısızlıkla sonuçlandı.
    // CANCELLED: Görev iptal edildi.

    @Column(name="assigned_date",nullable = true)
    private LocalDate assignedDate ;

    @Column(name="completion_date",nullable = true)
    private LocalDate completionDate ;


}
