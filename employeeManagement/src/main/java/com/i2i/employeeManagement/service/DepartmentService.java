package com.i2i.employeeManagement.service;

import java.util.List;

import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.dto.EmployeeDto;


public interface DepartmentService {

    /**
     * This method will convert the DepartmentDto to department.
     * And also add department to the Database.
     * @param departmentDto
     *     It Contains all the department details.
     * @return departmentDto
     *     It returns the added department details.
     */
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    /**
     * This Method is to retrieve all the departments from the database.
     * And also convert the department to departmentDto.
     * @return List<DepartmentDto>
     *     It contains the List of departments.
     */
    List<DepartmentDto> retrieveDepartments();

    /**
     * This Method is to retrieve department by id.
     * @param departmentId
     *     It is the Id of the department to be returned.
     * @return departmentDto
     *     It contains the single Department Details.
     */
    DepartmentDto retrieveDepartmentById(int departmentId);

    /**
     * This method is to update the department details.
     * @param departmentDto
     *     It Contains the updated department details.
     * @return DepartmentDto
     *     It is the updated details of the department
     */
    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    /**
     * This method is to delete department by id.
     *
     * @param departmentId It is the department ID to be deleted
     * @return boolean
     *     True if deleted else null
     */
    boolean deleteDepartment(int departmentId);

    /**
     * This method is to retrieve all the employees in a single department.
     * @param departmentId
     *     It is the departmentId of the Employee to be retrieved
     * @return List<EmployeeDto>
     *     It contains the employee Details
     */
    List<EmployeeDto> retrieveEmployeesByDepartment(int departmentId);
}
