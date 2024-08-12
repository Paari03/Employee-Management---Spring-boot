package com.i2i.employeeManagement.service;

import java.util.List;

import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.model.Department;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> retrieveDepartments();

    DepartmentDto retrieveDepartmentById(int departmentId);

    DepartmentDto updateDepartment(DepartmentDto departmentdto);

    void deleteDepartment(int departmentId);
}
