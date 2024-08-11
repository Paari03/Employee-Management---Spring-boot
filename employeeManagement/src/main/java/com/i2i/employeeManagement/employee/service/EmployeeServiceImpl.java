package com.i2i.employeeManagement.employee.service;

import com.i2i.employeeManagement.employee.repository.EmployeeRepository;
import com.i2i.employeeManagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> retrieveEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee retrieveEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(null);
    }
    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }
}
