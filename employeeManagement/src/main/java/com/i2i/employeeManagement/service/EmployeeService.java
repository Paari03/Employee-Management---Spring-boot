package com.i2i.employeeManagement.service;

import java.util.List;

import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.model.Employee;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> retrieveEmployees();

    EmployeeDto retrieveEmployeeById(int employeeId);

    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    void deleteEmployee(int employeeId);

    EmployeeDto addCourseToEmployee(int employeeId,int courseId);
}
