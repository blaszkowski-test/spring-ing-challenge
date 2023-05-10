package com.blapiter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
class ValidateParametersController {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {

        Map<String, Object> info = new HashMap<>();

        info.put("status", "Error");
        info.put("errors", e.getMessage());

        return new ResponseEntity<Object>(info, HttpStatus.BAD_REQUEST);
    }
}