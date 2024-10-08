package com.i2i.employeeManagement.exceptionHandler;


/**
 * This Class is a custom exception class for Employee.
 * It extends the RuntimeException.
 */
public class EmployeeException extends RuntimeException {
    public EmployeeException(String message) {
        super(message);
    }
}
