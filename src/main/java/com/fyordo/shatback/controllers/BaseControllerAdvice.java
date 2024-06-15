package com.fyordo.shatback.controllers;

import com.fyordo.shatback.exceptions.ModelNotFoundException;
import com.fyordo.shatback.exceptions.WrongCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleOtherExceptions(){
        return ResponseEntity.internalServerError().build();
    }
}
