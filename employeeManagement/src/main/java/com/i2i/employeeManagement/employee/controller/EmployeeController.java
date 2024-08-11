package com.i2i.employeeManagement.employee.controller;

import com.i2i.employeeManagement.employee.dto.EmployeeDto;
import com.i2i.employeeManagement.employee.mapper.EmployeeMapper;
import com.i2i.employeeManagement.employee.service.EmployeeService;
import com.i2i.employeeManagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return EmployeeMapper.mapEmployeeDto(
                employeeService.saveEmployee(
                        EmployeeMapper.mapEmployee(
                                employeeDto
                        )
                )
        );
    }

    @GetMapping
    public List<EmployeeDto> getEmployees() {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<Employee> employees = employeeService.retrieveEmployees();
        for (Employee employee : employees) {
            employeeDtos.add(EmployeeMapper.mapEmployeeDto(employee));
        }
        return employeeDtos;
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto getEmployees(@PathVariable int employeeId) {

        return EmployeeMapper.mapEmployeeDto(
                employeeService.retrieveEmployeeById(employeeId)
        );
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.retrieveEmployeeById(employeeId);
        Employee updatedEmployee = EmployeeMapper.mapEmployee(employeeDto);
        updatedEmployee.setEmployeeId(employee.getEmployeeId());
        return new ResponseEntity<>(
                EmployeeMapper.mapEmployeeDto(
                        employeeService.updateEmployee(
                                updatedEmployee)
                ),HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.retrieveEmployeeById(employeeId);
        employeeService.deleteEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
