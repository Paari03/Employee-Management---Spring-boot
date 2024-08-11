package com.i2i.employeeManagement.employee.mapper;

import com.i2i.employeeManagement.employee.dto.EmployeeDto;
import com.i2i.employeeManagement.model.Employee;

public class EmployeeMapper {
    public static EmployeeDto mapEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .dateOfBirth(employee.getDateOfBirth())
                .city(employee.getCity())
                .address(employee.getAddress())
                .build();
    }

    public static Employee mapEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .employeeId(employeeDto.getId())
                .employeeName(employeeDto.getEmployeeName())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .city(employeeDto.getCity())
                .address(employeeDto.getAddress())
                .build();
    }

}
