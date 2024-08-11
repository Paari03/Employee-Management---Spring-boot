package com.i2i.employeeManagement.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private int id;
    private String employeeName;
    private String dateOfBirth;
    private String city;
    private String address;
}
