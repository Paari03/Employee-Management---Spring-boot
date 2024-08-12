package com.i2i.employeeManagement.service;

import java.util.List;

import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.model.Employee;

public interface EmployeeService {

    /**
     * This method will convert the EmployeeDto to employee.
     * And also add employee to the Database.
     * @param employeeDto
     *     It Contains all the employee details.
     * @return employeeDto
     *     It returns the added employee details.
     */
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    /**
     * This Method is to retrieve all the employees from the database.
     * And also convert the employee to employeeDto.
     * @return List<EmployeeDto>
     *     It contains the List of employees.
     */
    List<EmployeeDto> retrieveEmployees();

    /**
     * This Method is to retrieve employee by id.
     * @param employeeId
     *     It is the Id of the employee to be returned.
     * @return employeeDto
     *     It contains the single Employee Details.
     */
    EmployeeDto retrieveEmployeeById(int employeeId);

    /**
     * This method is to update the employee details.
     * @param employeeDto
     *     It Contains the updated employee details.
     * @return EmployeeDto
     *     It is the updated details of the employee
     */
    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    /**
     * This methos is to delete employee by id.
     * @param employeeId
     *     It is the employee ID to be deleted
     */
    void deleteEmployee(int employeeId);

    /**
     * This Method is to add course to the employee.
     * @param employeeId
     *     It is the ID of the employee to be added course.
     * @param courseId
     *     It is the CourseId which is assigned to the Employee.
     * @return EmployeeDto
     *     It contains the employee details with course assigned.
     */
    EmployeeDto addCourseToEmployee(int employeeId,int courseId);
}
