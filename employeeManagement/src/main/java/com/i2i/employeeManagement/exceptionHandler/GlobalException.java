package com.i2i.employeeManagement.exceptionHandler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is the Global exception handler Class.
 * It is executed when there is exception in whole application.
 * @author paari
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException){
        return new ResponseEntity<>(elementException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleEmployeeException(EmployeeException employeeException){
        return new ResponseEntity<>(employeeException.getMessage(), HttpStatus.CONFLICT);
    }

}
