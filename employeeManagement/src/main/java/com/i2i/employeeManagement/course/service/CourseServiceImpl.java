package com.i2i.employeeManagement.course.service;

import com.i2i.employeeManagement.course.repository.CourseRepository;
import com.i2i.employeeManagement.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> retrieveCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course retrieveCourseById(int courseId) {
        return courseRepository.findById(courseId).orElseThrow(null);
    }
    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Course course) {
        course.setDeleted(true);
        courseRepository.save(course);
    }
}
