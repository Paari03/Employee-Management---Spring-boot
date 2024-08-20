package com.i2i.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.i2i.employeeManagement.dto.CourseDto;
import com.i2i.employeeManagement.service.CourseService;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private CourseDto courseDto;

    private final List<CourseDto> courseDtoList = new ArrayList<>();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        courseDto = CourseDto.builder()
                .courseId(1)
                .courseName("FrontEnd")
                .build();

        courseDtoList.add(courseDto);
    }

    @Test
    public void testCreateCourse() {
        when(courseService.saveCourse(courseDto)).thenReturn(courseDto);
        ResponseEntity<CourseDto> createCourse = courseController.createCourse(courseDto);
        assertEquals(HttpStatus.CREATED,createCourse.getStatusCode());
    }

    @Test
    public void testRetrieveCourses() {
        when(courseService.retrieveCourses()).thenReturn(courseDtoList);
        ResponseEntity<List<CourseDto>> retrieveCourses = courseController.getCourses();
        assertEquals(HttpStatus.OK,retrieveCourses.getStatusCode());
    }

    @Test
    public void testRetrieveCourseById() {
        when(courseService.retrieveCourseById(1)).thenReturn(courseDto);
        ResponseEntity<CourseDto> retrieveCourseById = courseController.getCourseById(1);
        assertEquals(HttpStatus.OK,retrieveCourseById.getStatusCode());
    }

    @Test
    public void testUpdateCourse() {
        when(courseService.updateCourse(courseDto)).thenReturn(courseDto);
        ResponseEntity<CourseDto> updateCourse = courseController.updateCourse(courseDto);
        assertEquals(HttpStatus.OK,updateCourse.getStatusCode());
    }

    @Test
    public void testDeleteCourse() {
        when(courseService.deleteCourse(1)).thenReturn(true);
        ResponseEntity<Void> deleteCourse = courseController.deleteCourseById(1);
        assertEquals(HttpStatus.OK,deleteCourse.getStatusCode());

    }
}
