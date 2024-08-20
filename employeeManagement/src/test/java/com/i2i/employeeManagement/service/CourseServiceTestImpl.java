package com.i2i.employeeManagement.service;

import com.i2i.employeeManagement.dto.CourseDto;
import com.i2i.employeeManagement.model.Course;
import com.i2i.employeeManagement.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTestImpl {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course1;
    private Course course2;
    private CourseDto courseDto;

    private final List<Course> courses = new ArrayList<>();

    @BeforeEach
    public  void setup() {
        MockitoAnnotations.openMocks(this);

        course1 = Course.builder()
                .courseId(1)
                .courseName("Java")
                .isDeleted(false)
                .build();
        courses.add(course1);

         course2 = Course.builder()
                .courseId(2)
                .courseName("IOT")
                .isDeleted(false)
                .build();
        courses.add(course2);

        courseDto = CourseDto.builder()
                .courseId(1)
                .courseName("Computer")
                .build();
    }

    @Test
    public void testSaveCourse() {
        when (courseRepository.findByIsDeletedFalse()).thenReturn(courses);
        when(courseRepository.save(any(Course.class))).thenReturn(course1);
        CourseDto result = courseService.saveCourse(courseDto);
        assertEquals("Java",result.getCourseName());
    }

    @Test
    public void testUpdateCourse() {
        when(courseRepository.findByCourseIdAndIsDeletedFalse(1)).thenReturn(course2);
        when(courseRepository.save(any(Course.class))).thenReturn(course2);
        CourseDto result = courseService.updateCourse(courseDto);
        assertEquals(2,result.getCourseId());
    }

    @Test
    public void testRetrieveCourses() {
        when(courseRepository.findByIsDeletedFalse()).thenReturn(courses);
        List<CourseDto> result = courseService.retrieveCourses();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testRetrieveCourseById() {
        when(courseRepository.findByCourseIdAndIsDeletedFalse(1)).thenReturn(course1);
        CourseDto result = courseService.retrieveCourseById(1);
        assertNotNull(result);
        assertEquals(1,result.getCourseId());
    }
}

