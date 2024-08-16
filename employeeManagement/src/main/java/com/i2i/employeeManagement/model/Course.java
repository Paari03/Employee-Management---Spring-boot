package com.i2i.employeeManagement.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Course in the organization.
 * This class contains details about the course's ID, name,
 * and the employees enrolled in the course.
 * @author Paari
 */
@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "course_name", unique = true)
    private String courseName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToMany(mappedBy = "courses")
    private List<Employee> employees;

}
