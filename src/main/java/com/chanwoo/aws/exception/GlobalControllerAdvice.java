package com.chanwoo.aws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = ResourceNotFound.class)
    public ResponseEntity resourceNotFound(ResourceNotFound e){
        System.out.println("================================");
        System.out.println(e.getMessage());
        System.out.println("================================");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
