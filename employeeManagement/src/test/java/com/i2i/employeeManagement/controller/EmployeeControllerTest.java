package com.i2i.employeeManagement.controller;


import com.i2i.employeeManagement.dto.EmployeeDto;
import com.i2i.employeeManagement.model.Department;
import com.i2i.employeeManagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeDto employeeDto;

    private final List<EmployeeDto> employeeDtoList = new ArrayList<>();


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);

        Department department1 = Department.builder()
                .departmentId(1)
                .departmentName("Hr")
                .isDeleted(false)
                .build();

        employeeDto = EmployeeDto.builder()
                .employeeId(1)
                .employeeName("Paari")
                .city("Erode")
                .address("Bhavani")
                .departmentId(1)
                .dateOfBirth(LocalDate.ofEpochDay(25 - 3 - 2003))
                .build();
        employeeDtoList.add(employeeDto);
    }

    @Test
    public void testCreateEmployee() {
        when(employeeService.saveEmployee(employeeDto)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> createEmployee = employeeController.createEmployee(employeeDto);
        assertEquals(HttpStatus.CREATED,createEmployee.getStatusCode());
    }

    @Test
    public void testRetrieveEmployees() {
        when(employeeService.retrieveEmployees()).thenReturn(employeeDtoList);
        ResponseEntity<List<EmployeeDto>> retrieveEmployees = employeeController.getEmployees();
        assertEquals(HttpStatus.OK,retrieveEmployees.getStatusCode());
    }

    @Test
    public void testRetrieveEmployeeById() {
        when(employeeService.retrieveEmployeeById(1)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> retrieveEmployeeById = employeeController.getEmployeeById(1);
        assertEquals(HttpStatus.OK,retrieveEmployeeById.getStatusCode());
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeService.updateEmployee(employeeDto)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> updateEmployee = employeeController.updateEmployee(employeeDto);
        assertEquals(HttpStatus.OK,updateEmployee.getStatusCode());
    }

    @Test
    public void testDeleteEmployee() {
        when(employeeService.deleteEmployee(1)).thenReturn(true);
        ResponseEntity<Void> deleteEmployee = employeeController.deleteEmployeeById(1);
        assertEquals(HttpStatus.OK,deleteEmployee.getStatusCode());

    }

}
