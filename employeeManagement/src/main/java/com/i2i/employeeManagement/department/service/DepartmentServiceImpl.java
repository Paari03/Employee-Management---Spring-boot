package com.i2i.employeeManagement.department.service;

import com.i2i.employeeManagement.department.repository.DepartmentRepository;
import com.i2i.employeeManagement.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> retrieveDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public Department retrieveDepartmentById(int departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(null);
    }
    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Department department) {
        department.setDeleted(true);
        departmentRepository.save(department);
    }
}
