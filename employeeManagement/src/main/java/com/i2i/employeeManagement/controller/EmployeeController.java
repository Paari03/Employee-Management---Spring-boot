package com.i2i.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.employeeManagement.service.CourseService;
import com.i2i.employeeManagement.service.DepartmentService;
import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.mapper.EmployeeMapper;
import com.i2i.employeeManagement.service.EmployeeService;
import com.i2i.employeeManagement.model.Course;
import com.i2i.employeeManagement.model.Department;
import com.i2i.employeeManagement.model.Employee;

/**
 * This Class is the Controller class.It can Handle all the HTTP request.
 */
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CourseService courseService;

    /**
     * This Method is called when the Request is POST.
     * It will convert the incoming JSON as Object
     * And send to the service class.
     * @param employeeDto {@Link EmployeeDto }
     *     It is the JSON input from the user
     * @return employeeDto
     *     It will return the Same JSON as output to the User.
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {

        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto),HttpStatus.CREATED);
    }

    /**
     * This Method is to assign Course to Employee.
     * @param employeeId {@Link employeeID}
     *     This is employee id to be assigned.
     * @param courseId
     *     This is the CourseId of the course to be assigned
     * @return employeeDto
     */
    @PutMapping("{employeeId}/{courseId}")
    public ResponseEntity<EmployeeDto> assignCourseToEmployee(@PathVariable int employeeId,
                                                           @PathVariable int courseId ){

        return new ResponseEntity<>(employeeService.addCourseToEmployee
                (employeeId,courseId),HttpStatus.CREATED);
    }

    /**
     * This Method is to retrieve all the employee in the database.
     * @return List<EmployeeDto>
     *    It Contains the List of Employees details.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>>getEmployees() {

        return new ResponseEntity<>(employeeService.retrieveEmployees(),HttpStatus.OK);
    }

    /**
     * This method is to retrieve employee by id.
     * @param employeeId
     *     The id of the employee to be retrieved.
     * @return EmployeeDto
     *     It contains the single employee details.
     */
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployees(@PathVariable int employeeId) {
        return new ResponseEntity<>(employeeService.retrieveEmployeeById(employeeId),
                HttpStatus.OK);
    }

    /**
     * This Method is to update the employee Details.
     * @param employeeDto {@Link - employeeDto}
     * @return employeeDto
     *     It contains the updated Employee Details
     */
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(
                                                      @RequestBody EmployeeDto employeeDto) {


        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto),HttpStatus.CREATED);
    }

    /**
     * This method is to delete the Employee by Id.
     * @param employeeId
     *     The Id of the employee to be Deleted.
     */
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

}
