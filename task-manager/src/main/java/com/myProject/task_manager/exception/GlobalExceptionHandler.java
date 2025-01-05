package com.myProject.task_manager.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private List<String> addMapValue(List<String> list,String  newValue){
        list.add(newValue);
        return list;
    }
    
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,List<String>> errorsMap = new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError)error).getField();
            if(errorsMap.containsKey(fieldName)){
                errorsMap.put(fieldName, addMapValue(errorsMap.get(fieldName), error.getDefaultMessage()));
            }else{
                errorsMap.put(fieldName,addMapValue(new ArrayList<>(),error.getDefaultMessage()));
            }
        }
        return ResponseEntity.badRequest().body(creatApiError(errorsMap));
    }


    private <T> ApiError<T> creatApiError(T errorMessage){
        ApiError<T> apiError = new ApiError<>();
        apiError.setId(UUID.randomUUID().toString());
        apiError.setErrorTime(new Date());
        apiError.setErrors(errorMessage);
        return apiError;
    }
}
