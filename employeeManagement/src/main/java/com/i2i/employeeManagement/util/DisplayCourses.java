package com.i2i.employeeManagement.util;

import com.i2i.employeeManagement.model.Course;

import java.util.List;

public class DisplayCourses {
    public static String displayCourse(List<Course> course) {
        StringBuilder courseList = new StringBuilder();
        for (Course courses : course) {
            courseList.append(courses.getCourseName()).append(", ");
        }
        return courseList.toString();
    }
}
