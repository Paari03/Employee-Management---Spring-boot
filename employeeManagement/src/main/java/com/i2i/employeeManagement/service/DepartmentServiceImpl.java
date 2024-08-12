package com.i2i.employeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.employeeManagement.repository.DepartmentRepository;
import com.i2i.employeeManagement.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.save(DepartmentMapper.mapDepartment(departmentDto));
        return DepartmentMapper.mapDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> retrieveDepartments() {
        List<DepartmentDto> departmentDto = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            departmentDto.add(DepartmentMapper.mapDepartmentDto(department));
        }
        return departmentDto;
    }

    @Override
    public DepartmentDto retrieveDepartmentById(int departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (null != department){
            return DepartmentMapper.mapDepartmentDto(department);
        }
        return null;
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department updatedDepartment = DepartmentMapper.mapDepartment(departmentDto);
        Department department = departmentRepository.save(updatedDepartment);
        return DepartmentMapper.mapDepartmentDto(department);
    }

    @Override
    public void deleteDepartment(int departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (null != department) {
            department.setDeleted(true);
            departmentRepository.save(department);
        }
    }
}
