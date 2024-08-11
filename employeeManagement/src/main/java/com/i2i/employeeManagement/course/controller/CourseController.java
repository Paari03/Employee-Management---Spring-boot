package com.i2i.employeeManagement.course.controller;

import com.i2i.employeeManagement.course.dto.CourseDto;
import com.i2i.employeeManagement.course.mapper.CourseMapper;
import com.i2i.employeeManagement.course.service.CourseService;
import com.i2i.employeeManagement.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        return CourseMapper.mapCourseDto(
                courseService.saveCourse(
                        CourseMapper.mapCourse(
                                courseDto
                        )
                )
        );
    }

    @GetMapping
    public List<CourseDto> getCourses() {
        List<CourseDto> courseDtos = new ArrayList<>();
        List<Course> courses = courseService.retrieveCourses();
        for (Course course : courses) {
            courseDtos.add(CourseMapper.mapCourseDto(course));
        }
        return courseDtos;
    }

    @GetMapping("/{courseId}")
    public CourseDto getCourses(@PathVariable int courseId) {

        return CourseMapper.mapCourseDto(
                courseService.retrieveCourseById(courseId)
        );
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable int courseId,@RequestBody CourseDto courseDto) {
        Course course = courseService.retrieveCourseById(courseId);
        Course updatedCourse = CourseMapper.mapCourse(courseDto);
        updatedCourse.setCourseId(course.getCourseId());
        return new ResponseEntity(
                CourseMapper.mapCourseDto(
                        courseService.updateCourse(
                                updatedCourse)
                ),HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int courseId) {
        Course course = courseService.retrieveCourseById(courseId);
        courseService.deleteCourse(course);
        return new ResponseEntity(HttpStatus.OK);
    }


}
