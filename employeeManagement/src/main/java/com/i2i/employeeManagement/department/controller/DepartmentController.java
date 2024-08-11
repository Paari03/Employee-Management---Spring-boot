package com.i2i.employeeManagement.department.controller;

import com.i2i.employeeManagement.department.dto.DepartmentDto;
import com.i2i.employeeManagement.department.mapper.DepartmentMapper;
import com.i2i.employeeManagement.department.service.DepartmentService;
import com.i2i.employeeManagement.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public DepartmentDto createDepartment(@RequestBody DepartmentDto departmentDto) {
        return DepartmentMapper.mapDepartmentDto(
                departmentService.saveDepartment(
                        DepartmentMapper.mapDepartment(
                                departmentDto
                        )
                )
        );
    }

    @GetMapping
    public List<DepartmentDto> getDepartments() {
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        List<Department> departments = departmentService.retrieveDepartments();
        for (Department department : departments) {
            departmentDtos.add(DepartmentMapper.mapDepartmentDto(department));
        }
        return departmentDtos;
    }

    @GetMapping("/{departmentId}")
    public DepartmentDto getDepartments(@PathVariable int departmentId) {

        return DepartmentMapper.mapDepartmentDto(
                departmentService.retrieveDepartmentById(departmentId)
        );
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable int departmentId,@RequestBody DepartmentDto departmentDto) {
        Department department = departmentService.retrieveDepartmentById(departmentId);
        Department updatedDepartment = DepartmentMapper.mapDepartment(departmentDto);
        updatedDepartment.setDepartmentId(department.getDepartmentId());
        return new ResponseEntity(
                DepartmentMapper.mapDepartmentDto(
                        departmentService.updateDepartment(
                                updatedDepartment)
                ),HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int departmentId) {
        Department department = departmentService.retrieveDepartmentById(departmentId);
        departmentService.deleteDepartment(department);
        return new ResponseEntity(HttpStatus.OK);
    }


}
