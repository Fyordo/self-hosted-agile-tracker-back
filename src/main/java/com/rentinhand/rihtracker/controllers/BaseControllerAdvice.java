package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseControllerAdvice {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ModelNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
