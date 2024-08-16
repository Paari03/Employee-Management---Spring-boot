package com.i2i.employeeManagement.mapper;

import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.model.Department;


/**
 * This class have methods to convert Department to departmentDto.
 * And also has departmentDto to Employee.
 * @author Paari
 */
public class DepartmentMapper {

    /**
     * This Method will convert the Employee to EmployeeDto.
     * @param department {@link Department}
     *     It Contains all the Department Details.
     * @return DepartmentDto {@link DepartmentDto}
     *     It is the Converted DepartmentDto contains only the necessary Data.
     */
    public static DepartmentDto mapDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build();
    }

    /**
     * This Method is to Convert the incoming DepartmentDto to Department.
     * @param departmentDto {@link DepartmentDto}
     *     It Contains all the Department details Which is to be added on the Database
     * @return Department {@link Department}
     *     It is the converted Department object which contains Department Details.
     */
    public static Department mapDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .departmentId(departmentDto.getDepartmentId())
                .departmentName(departmentDto.getDepartmentName())
                .build();
    }

}
