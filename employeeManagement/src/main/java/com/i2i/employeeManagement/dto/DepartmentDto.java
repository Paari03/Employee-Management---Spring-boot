package com.i2i.employeeManagement.dto;

import lombok.*;

/**
 * This is the DepartmentDto class which contains departmentId And Department Name.
 * @author paari
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class DepartmentDto {
    private int departmentId;
    private String departmentName;

}
