package com.i2i.employeeManagement.controller;

import java.util.List;

import com.i2i.employeeManagement.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.employeeManagement.dto.CourseDto;
import com.i2i.employeeManagement.service.CourseService;

/**
 * This class is the Controller class which will manage all the HTTP request.
 * This class Contain Post,Put,Get,Delete Http request.
 */
@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * This Method is to add Course to the Database.
     * @param courseDto
     *     It contains the Course name and the ID will be Auto Generated.
     * @return CourseDto
     *     It contains the Course details which is added to the database.
     */
    @PostMapping
    public ResponseEntity<CourseDto> createCourse
    (@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>
                (courseService.saveCourse(courseDto),
                        HttpStatus.CREATED);
    }

    /**
     * This method is to Retrieve all the courses from the database.
     * @return List<CourseDto>
     *     It contains the list of course in the database.
     */
    @GetMapping
    public ResponseEntity<List<CourseDto>> getCourses() {
        return new ResponseEntity<>(courseService.retrieveCourses(),HttpStatus.OK);
    }

    /**
     * This Method is to retrieve the single course.
     * @param courseId
     *     It is the Id of the course to be retrieved
     * @return CourseDto
     *     It contains the Single course.
     */
    @GetMapping("/{courseId}")
    public CourseDto getCourses(@PathVariable int courseId) {
        return courseService.retrieveCourseById(courseId);
    }

    /**
     * This Method is to update Course in the Database.
     * @param courseDto {@Link -CourseDto}
     *     It contains the Course name and the ID of the Course.
     * @return CourseDto
     *     It contains the Course details which is updated in the database.
     */
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) {
        return new ResponseEntity<>(courseService.updateCourse (courseDto),HttpStatus.OK);
    }

    /**
     * This method is to Delete course from the Database.
     * @param courseId
     *     It is id of the employee to be deleted.
     */
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int courseId){
        if (courseService.deleteCourse(courseId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return null;
    }

    /**
     * This method is to retrieve employee by course.
     * @param courseId
     *     It is the courseId to retrieve employees in it. 
     * @return List<EmployeeDto>
     *     It contains all the employee details.
     */
    @GetMapping("/EmployeesByCourse/{courseId}")
    public List<EmployeeDto> getEmployeesByCourse (@PathVariable int courseId) {
        return courseService.retrieveEmployeesByCourse(courseId);
    }

}
