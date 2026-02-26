package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class globalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomException> handleUserNameNotFoundException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException("User not found", HttpStatus.INTERNAL_SERVER_ERROR ));
    }
}
