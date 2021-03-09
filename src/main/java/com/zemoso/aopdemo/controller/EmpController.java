package com.zemoso.aopdemo.controller;


import com.zemoso.aopdemo.dto.EmpDTO;
import com.zemoso.aopdemo.exception.EmployeeNotFoundException;
import com.zemoso.aopdemo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/emp")
@CrossOrigin
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public List<EmpDTO> getAllEmployees() {

        return empService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmpDTO getEmployeeById(@PathVariable(name = "id") UUID id) {

        return empService.getEmployeeById(id);
    }

    @PostMapping
    public EmpDTO addEmployee(@RequestBody EmpDTO emp) {
        return empService.addEmployee(emp);
    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody(required = false) EmpDTO emp) {
        try {
            empService.updateEmployee(emp);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException("Employee not found with id : " + emp.getId());
        }
        return new ResponseEntity<>("Successfully updated Employee", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") UUID id) {
        try {
            empService.deleteEmployee(id);
        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException("Employee not found with id: " + id);
        }
        return new ResponseEntity<>("Successfully Deleted Employee", HttpStatus.OK);
    }
}
