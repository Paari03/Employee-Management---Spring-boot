package com.i2i.employeeManagement.mapper;

import com.i2i.employeeManagement.dto.CourseDto;
import com.i2i.employeeManagement.model.Course;

/**
 * This class have methods to convert Course to courseDto.
 * And also has CourseDto to Course.
 * @author paari
 */
public class CourseMapper {
    /**
     * This Method will convert the Employee to EmployeeDto.
     * @param course {@link Course}
     *     It Contains all the Course Details.
     * @return CourseDto {@link CourseDto}
     *     It is the Converted CourseDto contains only the necessary Data.
     */
    public static CourseDto mapCourseDto(Course course) {
        return CourseDto.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .build();
    }

    /**
     * This Method is to Convert the incoming CourseDto to Course.
     * @param courseDto {@link CourseDto}
     *     It Contains all the Course details Which is to be added on the Database
     * @return Course {@link Course}
     *     It is the converted Course object which contains Course Details.
     */
    public static Course mapCourse(CourseDto courseDto) {
        return Course.builder()
                .courseId(courseDto.getCourseId())
                .courseName(courseDto.getCourseName())
                .build();
    }

}
