package com.i2i.employeeManagement.controller;


import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private DepartmentDto departmentDto;

    private final List<DepartmentDto> departmentDtoList = new ArrayList<>();


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        departmentDto = DepartmentDto.builder()
                .departmentId(1)
                .departmentName("FrontEnd")
                .build();

        departmentDtoList.add(departmentDto);
    }

    @Test
    public void testCreateDepartment() {
        when(departmentService.saveDepartment(departmentDto)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> createDepartment = departmentController.createDepartment(departmentDto);
        assertEquals(HttpStatus.CREATED,createDepartment.getStatusCode());
    }

    @Test
    public void testRetrieveDepartments() {
        when(departmentService.retrieveDepartments()).thenReturn(departmentDtoList);
        ResponseEntity<List<DepartmentDto>> retrieveDepartments = departmentController.getDepartments();
        assertEquals(HttpStatus.OK,retrieveDepartments.getStatusCode());
    }

    @Test
    public void testRetrieveDepartmentById() {
        when(departmentService.retrieveDepartmentById(1)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> retrieveDepartmentById = departmentController.getDepartmentById(1);
        assertEquals(HttpStatus.OK,retrieveDepartmentById.getStatusCode());
    }

    @Test
    public void testUpdateDepartment() {
        when(departmentService.updateDepartment(departmentDto)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> updateDepartment = departmentController.updateDepartment(departmentDto);
        assertEquals(HttpStatus.OK,updateDepartment.getStatusCode());
    }

    @Test
    public void testDeleteDepartment() {
        when(departmentService.deleteDepartment(1)).thenReturn(true);
        ResponseEntity<Void> deleteDepartment = departmentController.deleteDepartmentById(1);
        assertEquals(HttpStatus.OK,deleteDepartment.getStatusCode());

    }

}
