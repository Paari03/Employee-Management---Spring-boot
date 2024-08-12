package com.i2i.employeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.employeeManagement.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
