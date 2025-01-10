package com.myProject.task_manager.exception;

public class BaseException extends RuntimeException{
    
    public BaseException(){

    }

    public BaseException(ErrorMessage errorMessage ){
        super(errorMessage.prepareErrorMessage());
    }
}
