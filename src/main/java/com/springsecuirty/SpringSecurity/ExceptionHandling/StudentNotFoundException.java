package com.springsecuirty.SpringSecurity.ExceptionHandling;

public class StudentNotFoundException extends Exception{

    public StudentNotFoundException(String message) {
        super(message);
    }
}
