package com.i2i.employeeManagement.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.employeeManagement.model.Employee;

/**
 * This class is the repository which extends the Jpa Repository.
 * @author paari
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    /**
     * This Method will return the List of employee if it's delete is false.
     * @return List<Employee>
     *     It contains all the employee details.
     */
    List<Employee> findByIsDeletedFalse();

    /**
     * This Method will return single employee based on the employeeId.
     * @param employeeId
     *     The id of the Employee to be retrieved
     * @return Employee
     *     It Contains the Single Employee Details.
     */
    Employee findByEmployeeIdAndIsDeletedFalse(int employeeId);
}
