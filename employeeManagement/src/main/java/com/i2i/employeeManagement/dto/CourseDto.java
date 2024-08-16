package com.i2i.employeeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This class is the Course Dto class which contains CourseId and Course Name.
 * @author paari
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private int courseId;
    private String courseName;

}
