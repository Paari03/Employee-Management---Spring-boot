package com.i2i.employeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.exception.EmployeeException;
import com.i2i.employeeManagement.mapper.CourseMapper;
import com.i2i.employeeManagement.mapper.EmployeeMapper;
import com.i2i.employeeManagement.model.Course;
import com.i2i.employeeManagement.model.Employee;
import com.i2i.employeeManagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CourseService courseService;

    private static final Logger logger = LogManager.getLogger();

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
    public EmployeeDto retrieveEmployeeById(int employeeId){
        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
        if(null == employee) {
            logger.warn("No such EmployeeId found");
            throw new NoSuchElementException();
        }
        return EmployeeMapper.mapEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        int employeeId = employeeDto.getEmployeeId();
        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
        if(null == employee) {
            logger.warn("Wrong Employee Id for updation of employee");
            throw new NoSuchElementException();
        }
        Employee updatedEmployee = EmployeeMapper.mapEmployee(employeeDto);
        return EmployeeMapper.mapEmployeeDto(employeeRepository.save(updatedEmployee));
    }

    @Override
    public boolean deleteEmployee(int employeeId) {
        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
        if(null == employee) {
            logger.warn("No Employee found for employeeId");
            throw new NoSuchElementException();
        }
        employee.setDeleted(true);
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public EmployeeDto addCourseToEmployee(int employeeId, int courseId) {
        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
        if(null == employee) {
            logger.warn("There is no Employee in that EmployeeId");
            throw new NoSuchElementException();
        }
        for(Course course :employee.getCourses()) {
            if (courseId == course.getCourseId()) {
                logger.warn("Course-{} already assigned to employeeID-{}",
                        course.getCourseName(),employeeId);
                throw new EmployeeException("Course already assigned to this employeeID");
            }
        }
        Course course = CourseMapper.mapCourse(courseService.retrieveCourseById(courseId));
        List<Course>courses = employee.getCourses();
        courses.add(course);
        employee.setCourses(courses);
        EmployeeDto employeeDto;
        employeeDto = EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
        return employeeDto;
        }
}
