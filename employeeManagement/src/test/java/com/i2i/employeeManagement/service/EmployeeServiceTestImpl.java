package com.i2i.employeeManagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.i2i.employeeManagement.dto.CourseDto;
import com.i2i.employeeManagement.mapper.CourseMapper;
import com.i2i.employeeManagement.mapper.EmployeeMapper;
import com.i2i.employeeManagement.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.model.Department;
import com.i2i.employeeManagement.model.Employee;
import com.i2i.employeeManagement.repository.EmployeeRepository;
import org.mockito.stubbing.OngoingStubbing;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTestImpl {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CourseServiceImpl courseService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private DepartmentServiceImpl departmentService;

    private Employee employee1;
    private Employee employee2;
    private EmployeeDto employeeDto;
    private EmployeeDto employeeDto1;
    private Department department1;
    private Course course1;
    private Course course2;
    private CourseDto courseDto;
    private final List<Course> courses = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        department1 = Department.builder()
                .departmentId(1)
                .departmentName("Hr")
                .isDeleted(false)
                .build();

        courseDto = CourseDto.builder()
                .courseId(1)
                .courseName("Computer")
                .build();

        course1 = Course.builder()
                .courseId(1)
                .courseName("Java")
                .isDeleted(false)
                .build();
        courses.add(course1);

        course2 = Course.builder()
                .courseId(2)
                .courseName("IOT")
                .isDeleted(false)
                .build();
        courses.add(course2);

        employee1 = Employee.builder()
                .employeeId(1)
                .employeeName("Paari")
                .city("Erode")
                .address("Bhavani")
                .department(department1)
                .dateOfBirth(LocalDate.of(2000,3, 20))
                .isDeleted(false)
                .build();
        employees.add(employee1);

        employee2 = Employee.builder()
                .employeeId(2)
                .employeeName("Hari")
                .city("Coimbatore")
                .address("Sathy")
                .department(department1)
                .dateOfBirth(LocalDate.of(2012,9,20))
                .isDeleted(false)
                .build();
        employees.add(employee2);

        employeeDto = EmployeeDto.builder()
                .employeeName("Siva")
                .city("Salem")
                .address("Mettur")
                .dateOfBirth(LocalDate.of(2002,2,12))
                .departmentId(1)
                .build();

         employeeDto1 = EmployeeDto.builder()
                .employeeId(1)
                .employeeName("Siva")
                .city("Salem")
                .address("Mettur")
                .dateOfBirth(LocalDate.of(2002,2,12))
                .departmentId(1)
                .courses(courses)
                .build();
    }

    @Test
    public void testSaveEmployee() {
        when(departmentService.retrieveDepartmentForEmployee(anyInt())).thenReturn(department1);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);
        EmployeeDto result = employeeService.saveEmployee(employeeDto);
        assertEquals(employee1.getEmployeeId(),result.getEmployeeId());
        assertEquals(employee1.getEmployeeName(),result.getEmployeeName());
        assertEquals(employee1.getCity(),result.getCity());
        assertEquals(employee1.getAddress(),result.getAddress());
        assertEquals(employee1.getDepartment().getDepartmentName(),result.getDepartmentName());
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeRepository.findByEmployeeIdAndIsDeletedFalse(anyInt())).thenReturn(employee1);
//        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);
        EmployeeDto result = employeeService.updateEmployee(employeeDto);
//        assertEquals(employee1.getEmployeeId(),result.getEmployeeId());
        assertEquals(employee1.getEmployeeName(),result.getEmployeeName());
        assertEquals(employee1.getCity(),result.getCity());
        assertEquals(employee1.getAddress(),result.getAddress());
    }

    @Test
    public void testRetrieveEmployeeById() {
        when(employeeRepository.findByEmployeeIdAndIsDeletedFalse(anyInt())).thenReturn(employee1);
        EmployeeDto result = employeeService.retrieveEmployeeById(employee1.getEmployeeId());
        assertNotNull(result);
        assertEquals(employee1.getEmployeeId(),result.getEmployeeId());
        assertEquals(employee1.getEmployeeName(),result.getEmployeeName());
        //assertEquals(employee1.getDateOfBirth(),result.getDateOfBirth());
        assertEquals(employee1.getCity(),result.getCity());
        assertEquals(employee1.getAddress(),result.getAddress());
        assertEquals(employee1.getDepartment().getDepartmentName(),result.getDepartmentName());
    }

    @Test
    public void testRetrieveEmployees() {

        when(employeeRepository.findByIsDeletedFalse()).thenReturn(employees);
        List<EmployeeDto> result = employeeService.retrieveEmployees();
        assertNotNull(result);
        assertEquals(2, result.size());

    }

    @Test
    public void testAddCourseToEmployee() {

        employee1 = Employee.builder()
                .employeeId(1)
                .employeeName("Paari")
                .city("Erode")
                .address("Bhavani")
                .department(department1)
                .dateOfBirth(LocalDate.of(2002,3,12))
                .isDeleted(false)
                .courses(courses)
                .build();

        when(employeeRepository.findByEmployeeIdAndIsDeletedFalse(anyInt())).thenReturn(employee2);
        when(courseService.retrieveCourseById(1)).thenReturn(courseDto);
        when(employeeRepository.save(employee1)).thenReturn(employee1);
        EmployeeDto result = employeeService.addCourseToEmployee(employee1.getEmployeeId(),course1.getCourseId());
        assertEquals(employee1.getEmployeeId(),result.getEmployeeId());

//        when(employeeRepository.findByEmployeeIdAndIsDeletedFalse(employee1.getEmployeeId())).thenReturn(employee1);
//        when(courseService.retrieveCourseById(course1.getCourseId())).thenReturn(courseDto);
//        when(CourseMapper.mapCourse(any())).thenReturn(course1);
//        when(EmployeeMapper.mapEmployee(any())).thenReturn(employee1);
//        when(employeeRepository.save(any())).thenReturn(employee1);
//        EmployeeDto result = employeeService.addCourseToEmployee(employee1.getEmployeeId(), course1.getCourseId());
//        assertEquals(employee1.getEmployeeName(), result.getEmployeeName());
    }
}

