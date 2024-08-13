package com.i2i.employeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.mapper.DepartmentMapper;
import com.i2i.employeeManagement.mapper.EmployeeMapper;
import com.i2i.employeeManagement.model.Employee;
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
        List<Department> departments = departmentRepository.findByAndIsDeletedFalse();
        if(null == departments) {
            throw new NoSuchElementException();
        }
        for (Department department : departments) {
            departmentDto.add(DepartmentMapper.mapDepartmentDto(department));
        }
        return departmentDto;
    }

    @Override
    public DepartmentDto retrieveDepartmentById(int departmentId) {
        Department department = departmentRepository.findByDepartmentIdAndIsDeletedFalse(departmentId);
        if(null == department) {
            throw new NoSuchElementException();
        }
        return DepartmentMapper.mapDepartmentDto(department);

    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Department updatedDepartment = DepartmentMapper.mapDepartment(departmentDto);
        Department department = departmentRepository.save(updatedDepartment);
        return DepartmentMapper.mapDepartmentDto(department);
    }

    @Override
    public boolean deleteDepartment(int departmentId) {
        Department department = departmentRepository.findById(departmentId).orElse(null);
        if (null != department) {
            department.setDeleted(true);
            departmentRepository.save(department);
            return true;
        }
        return false;
    }


    @Override
    public List<EmployeeDto> retrieveEmployeesByDepartment(int departmentId) {
        Department department = departmentRepository.findByDepartmentIdAndIsDeletedFalse(departmentId);
        if(null == department) throw new NoSuchElementException();
        List<EmployeeDto> employeeDto = new ArrayList<>();
        for (Employee employee : department.getEmployees()) {
            if(!employee.isDeleted()) {
                employeeDto.add(EmployeeMapper.mapEmployeeDto(employee));
            }
        }
        return employeeDto;
    }
}
