package com.i2i.employeeManagement.service;

import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.model.Department;
import com.i2i.employeeManagement.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTestImpl {

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;
    
    private  Department department;
    private  Department department1;
    private  DepartmentDto departmentDto;
    
    List<Department> departments = new ArrayList<>();
    
    @BeforeEach
    public  void setup() {
        MockitoAnnotations.openMocks(this);

        department1 = Department.builder()
                .departmentId(1)
                .departmentName("Hr")
                .isDeleted(false)
                .build();
        departments.add(department1);

       Department department2 = Department.builder()
                .departmentId(2)
                .departmentName("IT")
                .isDeleted(false)
                .build();
        departments.add(department2);

        departmentDto = DepartmentDto.builder()
                .departmentId(1)
                .departmentName("FrontEnd")
                .build();
    }
    
    @Test
    public void testSaveDepartment() {
        when (departmentRepository.findByIsDeletedFalse()).thenReturn(departments);
        when(departmentRepository.save(any(Department.class))).thenReturn(department1);
        DepartmentDto result = departmentService.saveDepartment(departmentDto);
        assertEquals("Hr",result.getDepartmentName());
    }

    @Test
    public void testUpdateDepartment() {
        when(departmentRepository.findByDepartmentIdAndIsDeletedFalse(1)).thenReturn(department1);
        when(departmentRepository.save(any(Department.class))).thenReturn(department1);
        DepartmentDto result = departmentService.updateDepartment(departmentDto);
        assertEquals(1,result.getDepartmentId());
    }

    @Test
    public void testRetrieveDepartments() {

        when(departmentRepository.findByIsDeletedFalse()).thenReturn(departments);
        List<DepartmentDto> result = departmentService.retrieveDepartments();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testRetrieveDepartmentById() {
        when(departmentRepository.findByDepartmentIdAndIsDeletedFalse(1)).thenReturn(department1);
        DepartmentDto result = departmentService.retrieveDepartmentById(1);
        assertNotNull(result);
        assertEquals(1,result.getDepartmentId());
    }
}

