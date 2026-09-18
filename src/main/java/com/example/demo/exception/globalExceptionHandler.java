package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class globalExceptionHandler {


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomException> handleUserNameNotFoundException(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomException("User not found", HttpStatus.INTERNAL_SERVER_ERROR ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentException> handleIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e){
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        body.put("error", e.getStackTrace());
        body.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String,Object>> handleCustomException(CustomException e){
        Map<String, Object> body = new HashMap<>();
        HttpStatus statusCode=e.getStatusCode();
        body.put("date",e.getTimeStamp());
        body.put("status", e.getError());
        body.put("error", e.getStatusCode());
        return ResponseEntity.status(statusCode.value()).body(body);
    }
}
