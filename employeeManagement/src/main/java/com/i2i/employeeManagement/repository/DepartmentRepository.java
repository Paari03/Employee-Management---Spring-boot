package com.i2i.employeeManagement.repository;

import com.i2i.employeeManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.employeeManagement.model.Department;

import java.util.List;

/**
 * This class is the repository which extends the Jpa Repository.
 * @author paari
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {


    /**
     * This Method will return the List of department if it's delete is false.
     * @return List<Department>
     *     It contains all the department details.
     */
    List<Department> findByIsDeletedFalse();

    /**
     * This Method will return single department based on the departmentId.
     * @param departmentId
     *     The id of the Department to be retrieved
     * @return Department
     *     It Contains the Single Department Details.
     */
    Department findByDepartmentIdAndIsDeletedFalse(int departmentId);
}
