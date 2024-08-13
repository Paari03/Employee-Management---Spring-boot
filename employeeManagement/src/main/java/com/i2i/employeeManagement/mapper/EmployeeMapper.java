package com.i2i.employeeManagement.mapper;

import com.i2i.employeeManagement.dto.EmployeeDto;

import com.i2i.employeeManagement.model.Employee;
import com.i2i.employeeManagement.util.DisplayCourses;
import com.i2i.employeeManagement.util.EmployeeUtil;


public class EmployeeMapper {

    public static EmployeeDto mapEmployeeDto(Employee employee) {


        return EmployeeDto.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .age(EmployeeUtil.ageCalculator(employee.getDateOfBirth()))
                .city(employee.getCity())
                .address(employee.getAddress())
                .departmentId(employee.getDepartment().getDepartmentId())
                .departmentName(employee.getDepartment().getDepartmentName())
                .courseName(new StringBuilder(DisplayCourses.displayCourse(employee.getCourses())))
                .build();
    }

    public static Employee mapEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .employeeId(employeeDto.getEmployeeId())
                .employeeName(employeeDto.getEmployeeName())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .city(employeeDto.getCity())
                .address(employeeDto.getAddress())
                .build();
    }

}
