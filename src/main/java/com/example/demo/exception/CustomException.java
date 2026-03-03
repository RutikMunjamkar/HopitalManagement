package com.example.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
public class CustomException extends RuntimeException{
    private LocalDate timeStamp;
    private String error;
    private HttpStatus statusCode;

    public CustomException(){
        this.timeStamp=LocalDate.now();
    }

    public CustomException(String error, HttpStatus statusCode){
        this();
        this.error=error;
        this.statusCode=statusCode;
    }
}
