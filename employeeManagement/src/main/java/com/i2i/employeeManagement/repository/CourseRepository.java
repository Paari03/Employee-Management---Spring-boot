package com.i2i.employeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.employeeManagement.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
}
