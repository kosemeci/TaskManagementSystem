package com.myProject.task_manager.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    
    NOT_EXIST_RECORD("1001","Kayıt bulunamadı."),
    UNKNOWN_ERROR("9999","Bilinmeyen bir hata oluştu.");

    private String message;
    private String code;
    
    MessageType(String code, String message) {
        this.code=code;
        this.message=message;
    }
}
