package com.i2i.employeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.i2i.employeeManagement.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.exceptionHandler.EmployeeException;
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
    private DepartmentService departmentService;

    @Autowired
    private CourseService courseService;

    private static final Logger logger = LogManager.getLogger();

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
//        int employeeId = employeeDto.getEmployeeId();
//        Employee employee1 = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
//        if (null == employee1) {
//            logger.warn("The Employee Id Already Exist");
//            throw new EmployeeException("The Employee Id already exist - "+ employeeId);
//        }

        Employee employee = EmployeeMapper.mapEmployee(employeeDto);
        Department department = departmentService.retrieveDepartmentForEmployee(employeeDto.getDepartmentId());
        employee.setDepartment(department);
        return EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDto> retrieveEmployees() {
        List<EmployeeDto> employeeDto = new ArrayList<>();
        List<Employee> employees = employeeRepository.findByIsDeletedFalse();
        for (Employee employee : employees) {
            employeeDto.add(EmployeeMapper.mapEmployeeDto(employee));
        }
        return employeeDto;

    }

    @Override
    public EmployeeDto retrieveEmployeeById(int employeeId) {
        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
        if (null == employee) {
            logger.warn(" EmployeeId- {} Not found",employeeId );
            exceptionHandling(employeeId);
        }
        return EmployeeMapper.mapEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
//        int employeeId = employeeDto.getEmployeeId();
//        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
//        if (null == employee) {
//            logger.warn("Wrong Employee Id for updation of employee");
//            exceptionHandling(employeeId);
//        }
        Employee updatedEmployee = EmployeeMapper.mapEmployee(employeeDto);
        return EmployeeMapper.mapEmployeeDto(employeeRepository.save(updatedEmployee));
    }

    @Override
    public boolean deleteEmployee(int employeeId) {
        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
        if (null == employee) {
            logger.warn("No Employee found for employeeId");
            exceptionHandling(employeeId);
        }
        employee.setDeleted(true);
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public EmployeeDto addCourseToEmployee(int employeeId, int courseId) {
        Employee employee = employeeRepository.findByEmployeeIdAndIsDeletedFalse(employeeId);
        if (null == employee) {
            logger.warn("There is no Employee in that EmployeeId");
            exceptionHandling(employeeId);
        }
        for (Course course : employee.getCourses()) {
            if (courseId == course.getCourseId()) {
                logger.warn("Course-{} already assigned to employeeID-{}",
                        course.getCourseName(), employeeId);
                throw new EmployeeException("Course already assigned to this employeeID");
            }
        }
        Course course = CourseMapper.mapCourse(courseService.retrieveCourseById(courseId));
        List<Course> courses = employee.getCourses();
        courses.add(course);
        employee.setCourses(courses);
        EmployeeDto employeeDto;
        employeeDto = EmployeeMapper.mapEmployeeDto(employeeRepository.save(employee));
        return employeeDto;
    }

    private void exceptionHandling(int employeeId){
        throw new NoSuchElementException("The Employee Id - "+ employeeId+" does not exist");
    }

}
