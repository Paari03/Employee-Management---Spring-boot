package com.i2i.employeeManagement.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.employeeManagement.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    List<Employee> findByIsDeletedFalse();

    Employee findByEmployeeIdAndIsDeletedFalse(int employeeId);
}
