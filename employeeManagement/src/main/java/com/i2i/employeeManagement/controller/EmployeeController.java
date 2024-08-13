package com.i2i.employeeManagement.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.service.EmployeeService;

/**
 * This Class is the Controller class.It can Handle all the HTTP request.
 */
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private static final Logger logger = LogManager.getLogger();
    /**
     * This Method is called when the Request is POST.
     * It will convert the incoming JSON as Object
     * And send to the service class.
     * @param employeeDto {@link EmployeeDto }
     *     It is the JSON input from the user
     * @return employeeDto
     *     It will return the Same JSON as output to the User.
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(
            @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.saveEmployee(employeeDto);
        logger.info("Employee added successfully with ID : {}" ,
                employeeDto1.getEmployeeId());
        return new ResponseEntity<>(employeeDto1,HttpStatus.CREATED);
    }

    /**
     * This Method is to assign Course to Employee.
     * @param employeeId {@Link employeeID}
     *     This is employee id to be assigned.
     * @param courseId
     *     This is the CourseId of the course to be assigned
     * @return employeeDto
     *     It contains the employee with the course assigned.
     */
    @PutMapping("{employeeId}/assignCourse/{courseId}")
    public ResponseEntity<EmployeeDto> assignCourseToEmployee(
            @PathVariable int employeeId, @PathVariable int courseId ){
        EmployeeDto employeeDto = employeeService.addCourseToEmployee
                (employeeId,courseId);
        logger.info("{}assigned to EmployeeId:{} Successfully",
                employeeDto.getCourseName(),employeeDto.getEmployeeId());
        return new ResponseEntity<>(employeeDto,HttpStatus.CREATED);
    }

    /**
     * This Method is to retrieve all the employee in the database.
     * @return List<EmployeeDto>
     *    It Contains the List of Employees details.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>>getEmployees() {
        return new ResponseEntity<>(employeeService.retrieveEmployees(),
                HttpStatus.OK);
    }

    /**
     * This method is to retrieve employee by id.
     * @param employeeId
     *     The id of the employee to be retrieved.
     * @return EmployeeDto
     *     It contains the single employee details.
     */
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(
            @PathVariable int employeeId)  {
        EmployeeDto employeeDto = employeeService.retrieveEmployeeById(employeeId);
        logger.info("Employee ID-{} retrieved Successfully",employeeId);
        return new ResponseEntity<>(employeeDto,
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
        EmployeeDto employeeDto1 = employeeService.updateEmployee(employeeDto);
        logger.info("Employee ID-{} Updated Successfully",
                employeeDto1.getEmployeeId());
        return new ResponseEntity<>(employeeDto1,HttpStatus.OK);
    }

    /**
     * This method is to delete the Employee by Id.
     * @param employeeId
     *     The ID of the employee to be Deleted.
     */
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeId) {

        if(employeeService.deleteEmployee(employeeId)) {
            logger.info("Employee Id -{}deleted Successfully",employeeId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return null;
    }
}
