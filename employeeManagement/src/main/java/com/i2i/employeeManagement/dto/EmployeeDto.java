package com.i2i.employeeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * This is the Employee Dto class Which contains
 * the EmployeeId,employeeName,age,DateOfBirth,City,DepartmentId,
 * DepartmentName,CourseName.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private int employeeId;
    private String employeeName;
    private String age;
    private LocalDate dateOfBirth;
    private String city;
    private String address;
    private int departmentId;
    private String departmentName;
    private StringBuilder courseName;
}
