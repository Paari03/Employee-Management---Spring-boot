package com.i2i.employeeManagement.department.service;

import com.i2i.employeeManagement.model.Department;

import java.util.List;

public interface DepartmentService {



    Department saveDepartment(Department department);

    List<Department> retrieveDepartments();

    Department retrieveDepartmentById(int departmentId);

    Department updateDepartment(Department department);

    void deleteDepartment(Department department);
}
