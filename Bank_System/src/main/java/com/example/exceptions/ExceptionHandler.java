package com.example.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<?> handle(AlreadyExistsException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handle(NotFoundException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
