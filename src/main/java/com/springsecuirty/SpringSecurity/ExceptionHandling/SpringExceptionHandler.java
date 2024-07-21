package com.springsecuirty.SpringSecurity.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpringExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException exe){
        //*************Defining Error Response Body***********
        ErrorResponse error=new ErrorResponse();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(exe.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
       return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    //Handle all the Global Exception...
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exe){
        //*************Defining Error Response Body***********
        ErrorResponse error=new ErrorResponse();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exe.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
