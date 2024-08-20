package com.i2i.employeeManagement.mapper;

import com.i2i.employeeManagement.dto.EmployeeDto;

import com.i2i.employeeManagement.exceptionHandler.EmployeeException;

import com.i2i.employeeManagement.model.Employee;
import com.i2i.employeeManagement.util.DisplayCourses;
import com.i2i.employeeManagement.util.EmployeeUtil;

/**
 * This class have methods to convert Employee to employeeDto.
 * And also has EmployeeDto to Employee.
 * @author paari
 */
public class EmployeeMapper {

    /**
     * This Method will convert the Employee to EmployeeDto.
     * @param employee {@link Employee}
     *     It Contains all the Employee Details.
     * @return EmployeeDto {@link EmployeeDto}
     *     It is the Converted EmployeeDto contains only the necessary Data.
     */
    public static EmployeeDto mapEmployeeDto(Employee employee) {

        return EmployeeDto.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .age(EmployeeUtil.interval(employee.getDateOfBirth()))
                .city(employee.getCity())
                .address(employee.getAddress())
                .departmentId(employee.getDepartment() == null ? 0 : employee.getDepartment().getDepartmentId())
                .departmentName(employee.getDepartment() == null ? "Not assigned":employee.getDepartment().getDepartmentName())
                .courseName(employee.getCourses()  == null ? new StringBuilder("Not assigned") :new StringBuilder(DisplayCourses.displayCourse(employee.getCourses())))
                .build();
    }
    /**
     * This Method is to Convert the incoming EmployeeDto to Employee to
     * Store it in the Database.
     * @param employeeDto {@link EmployeeDto}
     *     It Contains all the Employee details Which is to be added on the Database
     * @return Employee {@link Employee}
     *     It is the converted Employee object which contains Employee Details.
     */
    public static Employee mapEmployee(EmployeeDto employeeDto) {


        return Employee.builder()
                .employeeId(employeeDto.getEmployeeId())
                .employeeName(employeeDto.getEmployeeName())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .city(employeeDto.getCity())
                .address(employeeDto.getAddress())
                .build();
    }

}
