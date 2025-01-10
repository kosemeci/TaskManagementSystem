package com.myProject.task_manager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    
    private MessageType messageType;
    private String ofStatic;

    public String prepareErrorMessage(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messageType.getMessage());
        if(ofStatic!=null){
            stringBuilder.append(" : " + ofStatic);
        }
        return stringBuilder.toString();
    }


}
