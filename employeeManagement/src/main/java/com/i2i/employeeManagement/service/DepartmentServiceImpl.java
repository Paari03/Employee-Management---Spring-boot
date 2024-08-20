package com.i2i.employeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.exceptionHandler.EmployeeException;
import com.i2i.employeeManagement.mapper.DepartmentMapper;
import com.i2i.employeeManagement.mapper.EmployeeMapper;
import com.i2i.employeeManagement.model.Department;
import com.i2i.employeeManagement.model.Employee;

import com.i2i.employeeManagement.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private static final Logger logger = LogManager.getLogger();

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        String departmentName = departmentDto.getDepartmentName();
        List<Department> department = departmentRepository.findByIsDeletedFalse();
        for (Department department1 : department) {
            if (departmentName.equals(department1.getDepartmentName())) {
                logger.warn("The Department {} Already Exist",departmentName);
                throw new EmployeeException("The Department -"
                        + departmentName +" already exist");
            }
        }
        Department department1 = DepartmentMapper.mapDepartment(departmentDto);
        return DepartmentMapper.mapDepartmentDto(departmentRepository.save(department1));
    }

    @Override
    public List<DepartmentDto> retrieveDepartments() {
        List<DepartmentDto> departmentDto = new ArrayList<>();
        List<Department> departments = departmentRepository.findByIsDeletedFalse();
        for (Department department : departments) {
            departmentDto.add(DepartmentMapper.mapDepartmentDto(department));
        }
        return departmentDto;

    }

    @Override
    public DepartmentDto retrieveDepartmentById(int departmentId) {
        Department department = departmentRepository.
                findByDepartmentIdAndIsDeletedFalse(departmentId);
        if (null == department) {
            logger.warn(" DepartmentId- {} Not found",departmentId );
            exceptionHandling(departmentId);
        }
        return DepartmentMapper.mapDepartmentDto(department);
    }

    @Override
    public Department retrieveDepartmentForEmployee(int departmentId) {
        Department department = departmentRepository.
                findByDepartmentIdAndIsDeletedFalse(departmentId);
        if (null == department) {
            logger.warn(" DepartmentId- {} found",departmentId );
            exceptionHandling(departmentId);
        }
        return department;
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        int departmentId = departmentDto.getDepartmentId();
        Department department = departmentRepository.
                findByDepartmentIdAndIsDeletedFalse(departmentId);
        if (null == department) {
            logger.warn("Wrong Department Id {}for updation of department",
                    departmentId);
            exceptionHandling(departmentId);
        }
        Department updatedDepartment = DepartmentMapper.mapDepartment(
                departmentDto);
        return DepartmentMapper.mapDepartmentDto(
                departmentRepository.save(updatedDepartment));
    }

    @Override
    public boolean deleteDepartment(int departmentId) {
        Department department = departmentRepository.
                findByDepartmentIdAndIsDeletedFalse(departmentId);
        if (null == department) {
            logger.warn("No Department found for departmentId");
            exceptionHandling(departmentId);
        }
        department.setDeleted(true);
        departmentRepository.save(department);
        return true;
    }

    @Override
    public List<EmployeeDto> retrieveEmployeesByDepartment(int departmentId) {
        Department department = departmentRepository.
                findByDepartmentIdAndIsDeletedFalse(departmentId);
        if(null == department) {
            exceptionHandling(departmentId);
        }
        List<EmployeeDto> employeeDto = new ArrayList<>();
        for (Employee employee: department.getEmployees()) {
            if(!department.isDeleted()) {
                employeeDto.add(EmployeeMapper.mapEmployeeDto(employee));
            }
        }
        return employeeDto;
    }

    private void exceptionHandling(int departmentId){
        throw new NoSuchElementException("The Department Id - "+ departmentId+" does not exist");
    }
}
