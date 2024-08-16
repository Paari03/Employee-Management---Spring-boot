package com.i2i.employeeManagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * This is the Employee Dto class Which contains the EmployeeId,
 * employeeName,age,DateOfBirth,City,DepartmentId,
 * DepartmentName,CourseName.
 * </p>
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
