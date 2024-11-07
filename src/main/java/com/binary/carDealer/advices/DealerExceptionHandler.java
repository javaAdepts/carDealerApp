package com.binary.carDealer.advices;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DealerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Remove the all car-dealer references before deleting a dealer");
        errors.put("timestamp", new Date().toString());
        errors.put("httpStatus", HttpStatus.BAD_REQUEST.toString());
        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
