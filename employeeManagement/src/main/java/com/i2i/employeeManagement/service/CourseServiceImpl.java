package com.i2i.employeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


import com.i2i.employeeManagement.exceptionHandler.EmployeeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.employeeManagement.dto.CourseDto;
import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.mapper.CourseMapper;
import com.i2i.employeeManagement.mapper.EmployeeMapper;
import com.i2i.employeeManagement.model.Employee;
import com.i2i.employeeManagement.repository.CourseRepository;
import com.i2i.employeeManagement.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CourseDto saveCourse(CourseDto courseDto) {
        String courseName = courseDto.getCourseName();
        List<Course> course = courseRepository.findByIsDeletedFalse();
        for (Course course1 : course) {
            if (courseName.equals(course1.getCourseName())) {
                logger.warn("The Course {} Already Exist",courseName);
                throw new EmployeeException("The Course -"
                        + courseName +" already exist");
            }
        }
        Course course1 = CourseMapper.mapCourse(courseDto);
        return CourseMapper.mapCourseDto(courseRepository.save(course1));
    }

    @Override
    public List<CourseDto> retrieveCourses() {
        List<CourseDto> courseDto = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            courseDto.add(CourseMapper.mapCourseDto(course));
        }
        return courseDto;

    }

    @Override
    public CourseDto retrieveCourseById(int courseId) {
        Course course = courseRepository.
                findByCourseIdAndIsDeletedFalse(courseId);
        if (null == course) {
            logger.warn(" CourseId- {} Not found",courseId );
            exceptionHandling(courseId);
        }
        return CourseMapper.mapCourseDto(course);
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto) {
        int courseId = courseDto.getCourseId();
        Course course = courseRepository.
                findByCourseIdAndIsDeletedFalse(courseId);
        if (null == course) {
            logger.warn("The Update Course Id {}does not exist",
                    courseId);
            exceptionHandling(courseId);
        }
        Course updatedCourse = CourseMapper.mapCourse(
                courseDto);
        return CourseMapper.mapCourseDto(
                courseRepository.save(updatedCourse));
    }

    @Override
    public boolean deleteCourse(int courseId) {
        Course course = courseRepository.
                findByCourseIdAndIsDeletedFalse(courseId);
        if (null == course) {
            logger.warn("No Course found for courseId-{}",courseId);
            exceptionHandling(courseId);
        }
        course.setDeleted(true);
        courseRepository.save(course);
        return true;
    }

    @Override
    public List<EmployeeDto> retrieveEmployeesByCourse(int courseId) {
        Course course = courseRepository.findByCourseIdAndIsDeletedFalse(courseId);
        if(null == course) {
            logger.warn("There is no course found for {}",courseId);
            exceptionHandling(courseId);
        }
        List<EmployeeDto> employeeDto = new ArrayList<>();
        for (Employee employee : course.getEmployees()) {
            if(!employee.isDeleted()) {
                employeeDto.add(EmployeeMapper.mapEmployeeDto(employee));
            }
        }
        return employeeDto;
    }
    private void exceptionHandling(int courseId){
        throw new NoSuchElementException("The Course Id - "+ courseId+" does not exist");
    }
}
