package com.i2i.employeeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.employeeManagement.model.Course;

/**
 * This class is the repository which extends the Jpa Repository.
 * @author paari
 */
@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {

    /**
     * This Method will return the List of course if it's delete is false.
     * @return List<Course>
     *     It contains all the course details.
     */
    List<Course> findByIsDeletedFalse();

    /**
     * This Method will return single course based on the courseId.
     * @param courseId
     *     The id of the Course to be retrieved
     * @return Course
     *     It Contains the Single Course Details.
     */
    Course findByCourseIdAndIsDeletedFalse(int courseId);
}