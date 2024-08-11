package com.i2i.employeeManagement.course.service;

import com.i2i.employeeManagement.model.Course;

import java.util.List;

public interface CourseService {



    Course saveCourse(Course course);

    List<Course> retrieveCourses();

    Course retrieveCourseById(int courseId);

    Course updateCourse(Course course);

    void deleteCourse(Course course);
}
