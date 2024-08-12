package com.i2i.employeeManagement.service;

import java.util.List;

import com.i2i.employeeManagement.model.Course;

public interface CourseService {

    Course saveCourse(Course course);

    List<Course> retrieveCourses();

    Course retrieveCourseById(int courseId);

    Course updateCourse(Course course);

    void deleteCourse(Course course);
}
