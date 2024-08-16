package com.i2i.employeeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This is the DepartmentDto class which contains departmentId And Department Name.
 * @author paari
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private int departmentId;
    private String departmentName;
}
