package com.i2i.employeeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.employeeManagement.dto.DepartmentDto;
import com.i2i.employeeManagement.mapper.DepartmentMapper;
import com.i2i.employeeManagement.service.DepartmentService;
import com.i2i.employeeManagement.model.Department;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment
            (@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>
                (departmentService.saveDepartment(departmentDto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        return new ResponseEntity<>(departmentService.retrieveDepartments(),HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public DepartmentDto getDepartments(@PathVariable int departmentId) {
        return departmentService.retrieveDepartmentById(departmentId);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.updateDepartment (departmentDto),HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public void deleteEmployee(@PathVariable int departmentId){
        departmentService.retrieveDepartmentById(departmentId);


    }


}
