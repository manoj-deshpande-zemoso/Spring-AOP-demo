package com.zemoso.aopdemo.service;

import com.zemoso.aopdemo.dao.EmpRepository;
import com.zemoso.aopdemo.dto.EmpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class EmpService {

    @Autowired
    private EmpRepository empRepository;

    public List<EmpDTO> getAllEmployees() {
        return empRepository.getAllEmployees();
    }

    public EmpDTO getEmployeeById(UUID id) {
        return empRepository.getEmployeeById(id);
    }

    public EmpDTO addEmployee(EmpDTO emp) {
        return empRepository.addEmployee(emp);
    }

    public void updateEmployee(EmpDTO emp) {
        empRepository.updateEmployee(emp);
    }

    public void deleteEmployee(UUID id) {
        empRepository.deleteEmployee(id);
    }
}
