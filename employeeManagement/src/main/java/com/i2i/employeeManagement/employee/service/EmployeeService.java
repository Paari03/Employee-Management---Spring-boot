package com.i2i.employeeManagement.employee.service;

import com.i2i.employeeManagement.model.Employee;

import java.util.List;

public interface EmployeeService {



    Employee saveEmployee(Employee employee);

    List<Employee> retrieveEmployees();

    Employee retrieveEmployeeById(int employeeId);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
