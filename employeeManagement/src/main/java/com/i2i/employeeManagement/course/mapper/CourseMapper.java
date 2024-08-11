package com.i2i.employeeManagement.course.mapper;

import com.i2i.employeeManagement.course.dto.CourseDto;
import com.i2i.employeeManagement.model.Course;

public class CourseMapper {
    public static CourseDto mapCourseDto(Course course) {
        return CourseDto.builder()
                .id(course.getCourseId())
                .courseName(course.getCourseName())
                .build();
    }

    public static Course mapCourse(CourseDto courseDto) {
        return Course.builder()
                .courseId(courseDto.getId())
                .courseName(courseDto.getCourseName())
                .build();
    }

}
