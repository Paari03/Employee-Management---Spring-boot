package com.i2i.employeeManagement.department.mapper;

import com.i2i.employeeManagement.department.dto.DepartmentDto;
import com.i2i.employeeManagement.model.Department;

public class DepartmentMapper {
    public static DepartmentDto mapDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build();
    }

    public static Department mapDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .departmentId(departmentDto.getId())
                .departmentName(departmentDto.getDepartmentName())
                .build();
    }

}
