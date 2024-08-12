package com.i2i.employeeManagement.mapper;

import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.model.Department;

public class DepartmentMapper {
    public static DepartmentDto mapDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build();
    }

    public static Department mapDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .departmentId(departmentDto.getDepartmentId())
                .departmentName(departmentDto.getDepartmentName())
                .build();
    }

}
