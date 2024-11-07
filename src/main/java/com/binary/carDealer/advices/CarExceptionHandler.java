package com.binary.carDealer.advices;

import com.binary.carDealer.exceptions.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CarExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<Object> handleCarnotFoundException(CarNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        errors.put("timestamp", new Date().toString());
        errors.put("httpStatus", HttpStatus.BAD_REQUEST.toString());
        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(
                error ->{
                    errors.put(error.getField(), error.getDefaultMessage());
                }
        );

        errors.put("timestamp", new Date().toString());
        errors.put("httpStatus", HttpStatus.BAD_REQUEST.toString());

        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

}
