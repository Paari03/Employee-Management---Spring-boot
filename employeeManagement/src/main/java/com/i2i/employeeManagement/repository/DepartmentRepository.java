package com.i2i.employeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.employeeManagement.model.Department;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    List<Department> findByAndIsDeletedFalse();

    Department findByDepartmentIdAndIsDeletedFalse(int departmentId);
}
