package com.i2i.employeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
        Course course = courseRepository.save(CourseMapper.mapCourse(courseDto));
        return CourseMapper.mapCourseDto(course);
    }

    @Override
    public List<CourseDto> retrieveCourses() {
        List<CourseDto> courseDto = new ArrayList<>();
        List<Course> courses = courseRepository.findByIsDeletedFalse();
        if(null == courses) {
            throw new NoSuchElementException();
        }
        for (Course course : courses) {
            courseDto.add(CourseMapper.mapCourseDto(course));
        }
        return courseDto;
    }

    @Override
    public CourseDto retrieveCourseById(int courseId) {
        Course course = courseRepository.findByCourseIdAndIsDeletedFalse(courseId);
        if(null == course) {
            throw new NoSuchElementException();
        }
        return CourseMapper.mapCourseDto(course);

    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto) {
        Course updatedCourse = CourseMapper.mapCourse(courseDto);
        Course course = courseRepository.save(updatedCourse);
        return CourseMapper.mapCourseDto(course);
    }

    @Override
    public boolean deleteCourse(int courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (null != course) {
            course.setDeleted(true);
            courseRepository.save(course);
            return true;
        }
        return false;
    }

    @Override
    public List<EmployeeDto> retrieveEmployeesByCourse(int courseId) {
        Course course = courseRepository.findByCourseIdAndIsDeletedFalse(courseId);
        if(null == course) {
            logger.warn("There is no course found for {}",courseId);
            throw new NoSuchElementException();
        }
        List<EmployeeDto> employeeDto = new ArrayList<>();
        for (Employee employee : course.getEmployees()) {
            if(!employee.isDeleted()) {
                employeeDto.add(EmployeeMapper.mapEmployeeDto(employee));
            }
        }
        return employeeDto;
    }
}
