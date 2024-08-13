package com.i2i.employeeManagement.service;

import java.util.List;

import com.i2i.employeeManagement.dto.CourseDto;
import com.i2i.employeeManagement.dto.EmployeeDto;


public interface CourseService {
        /**
         * This method will convert the CourseDto to course.
         * And also add course to the Database.
         * @param courseDto
         *     It Contains all the course details.
         * @return courseDto
         *     It returns the added course details.
         */
        CourseDto saveCourse(CourseDto courseDto);

        /**
         * This Method is to retrieve all the courses from the database.
         * And also convert the course to courseDto.
         * @return List<CourseDto>
         *     It contains the List of courses.
         */
        List<CourseDto> retrieveCourses();

        /**
         * This Method is to retrieve course by id.
         * @param courseId
         *     It is the Id of the course to be returned.
         * @return courseDto
         *     It contains the single Course Details.
         */
        CourseDto retrieveCourseById(int courseId);

        /**
         * This method is to update the course details.
         * @param courseDto
         *     It Contains the updated course details.
         * @return CourseDto
         *     It is the updated details of the course
         */
        CourseDto updateCourse(CourseDto courseDto);

        /**
         * This method is to delete course by id.
         *
         * @param courseId It is the course ID to be deleted
         * @return boolean
         *     True if deleted else null
         */
        boolean deleteCourse(int courseId);

        /**
         * This method is to retrieve all the employees in a single course.
         * @param courseId
         *     It is the courseId of the Employee to be retrieved
         * @return List<EmployeeDto>
         *     It contains the employee Details
         */
        List<EmployeeDto> retrieveEmployeesByCourse(int courseId);
}

