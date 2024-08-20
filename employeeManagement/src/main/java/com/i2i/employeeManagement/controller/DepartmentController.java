package com.i2i.employeeManagement.controller;

import java.util.List;

import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.employeeManagement.dto.DepartmentDto;


/**
 * This class is the Controller class which will manage all the HTTP request.
 * This class Contain Post,Put,Get,Delete Http request.
 */
@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * This Method is to add Department to the Database.
     * @param departmentDto
     *     It contains the Department name and the ID will be Auto Generated.
     * @return DepartmentDto
     *     It contains the Department details which is added to the database.
     */
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment
            (@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>
                (departmentService.saveDepartment(departmentDto),
                HttpStatus.CREATED);
    }

    /**
     * This method is to Retrieve all the departments from the database.
     * @return List<DepartmentDto>
     *     It contains the list of department in the database.
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        return new ResponseEntity<>(departmentService.retrieveDepartments(),HttpStatus.OK);
    }

    /**
     * This Method is to retrieve the single department.
     * @param departmentId
     *     It is the Id of the department to be retrieved
     * @return DepartmentDto
     *     It contains the Single department.
     */
    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable int departmentId) {
        return new ResponseEntity<>(departmentService.retrieveDepartmentById(departmentId),HttpStatus.OK);
    }

    /**
     * This Method is to update Department in the Database.
     * @param departmentDto {@Link -DepartmentDto}
     *     It contains the Department name and the ID of the Department.
     * @return DepartmentDto
     *     It contains the Department details which is updated in the database.
     */
    @PutMapping
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.updateDepartment (departmentDto),HttpStatus.OK);
    }

    /**
     * This method is to Delete department from the Database.
     * @param departmentId
     *     It is id of the employee to be deleted.
     */
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable int departmentId){
       if (departmentService.deleteDepartment(departmentId)) {
           return new ResponseEntity<>(HttpStatus.OK);
        }
       return null;

    }

    /**
     * This method is to retrieve employee by department.
     * @param departmentId
     *     It is the departmentId to retrieve employees in it.
     * @return List<EmployeeDto>
     *     It contains all the employee details.
     */
    @GetMapping("/{departmentId}/employees")
    public List<EmployeeDto> getEmployeesByDepartment (@PathVariable int departmentId) {
        return departmentService.retrieveEmployeesByDepartment(departmentId);
    }

}
