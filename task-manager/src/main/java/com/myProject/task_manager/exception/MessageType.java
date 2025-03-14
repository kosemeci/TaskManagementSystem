package com.myProject.task_manager.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    
    NOT_EXIST_USER_RECORD("1001","Kullanıcı kaydı bulunamadı."),
    NOT_EXIST_PROJECT_RECORD("1002","Proje kaydı bulunamadı"),
    NOT_EXIST_TASK_RECORD("1003","Task kaydı bulunamadı."),
    UNAUTHORIZED_ACCESS("1004", "Try unauthorizated access"),
    ALREADY_CANCELLED("1005","Task zaten iptal edilmiş"),
    UNKNOWN_ERROR("9999","Bilinmeyen bir hata oluştu.");

    private String message;
    private String code;
    
    MessageType(String code, String message) {
        this.code=code;
        this.message=message;
    }
}
