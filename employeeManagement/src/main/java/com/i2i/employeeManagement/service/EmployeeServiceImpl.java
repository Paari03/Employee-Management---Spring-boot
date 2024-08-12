package com.i2i.employeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.i2i.employeeManagement.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.mapper.EmployeeMapper;
import com.i2i.employeeManagement.model.Employee;
import com.i2i.employeeManagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CourseService courseService;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapEmployee(employeeDto);
        return EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDto> retrieveEmployees() {
        List<EmployeeDto> employeeDto = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            employeeDto.add(EmployeeMapper.mapEmployeeDto(employee));
        }
        return employeeDto;
    }

    @Override
    public EmployeeDto retrieveEmployeeById(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(null);
        return EmployeeMapper.mapEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employee = employeeRepository.findById(employeeDto.getEmployeeId());
        Employee updatedEmployee = EmployeeMapper.mapEmployee(employeeDto);
        updatedEmployee.setEmployeeId(employeeDto.getEmployeeId());
        return EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId);
        if (null != employee) {
            employee.setDeleted(true);
            employeeRepository.save(employee);
        }
    }

    @Override
    public EmployeeDto addCourseToEmployee(int employeeId, int courseId) {
        Employee employee = employeeRepository.findById(employeeId);
        Course course = courseService.retrieveCourseById(courseId);
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        if(employee.isPresent()) {
            employee.setCourses(courses);
            EmployeeDto employeeDto;
            employeeDto = EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
            return employeeDto;
        }
    }
}
