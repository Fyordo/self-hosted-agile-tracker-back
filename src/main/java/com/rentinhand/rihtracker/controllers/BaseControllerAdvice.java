package com.rentinhand.rihtracker.controllers;

import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.exceptions.WrongCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseControllerAdvice {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<?> handleNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({WrongCredentialsException.class, BadCredentialsException.class})
    public ResponseEntity<?> handleWrongCredentials(){
        return ResponseEntity.badRequest().build();
    }
}
