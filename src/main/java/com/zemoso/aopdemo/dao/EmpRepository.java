package com.zemoso.aopdemo.dao;

import com.zemoso.aopdemo.dto.EmpDTO;
import com.zemoso.aopdemo.dto.mapper.EmpDTOMapper;
import com.zemoso.aopdemo.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;

@Repository
public class EmpRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<EmpDTO> getAllEmployees() {
        String sql = "select * from emp";
        return jdbcTemplate.query(sql, new EmpDTOMapper());
    }

    public EmpDTO getEmployeeById(UUID id) {
        String sql = "select * from emp where id = ?";
        EmpDTO emp = null;
        try {
            emp = jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmpDTOMapper());
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Could not find employee with id : " + id);
        }
        return emp;
    }

    public EmpDTO addEmployee(EmpDTO emp) {
        String sqlInsertQuery = "INSERT INTO emp VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlInsertQuery, emp.getName(),emp.getEmail(),emp.getPhone());
        return emp;
    }

    public void updateEmployee(EmpDTO emp) {
        if(getEmployeeById(emp.getId()) == null) throw new EmployeeNotFoundException("Could not find employee with id : " + emp.getId());
        String sqlUpdateQuery = "UPDATE emp set name=? email=? phone=? where id=?";
        jdbcTemplate.update(sqlUpdateQuery, emp.getName(), emp.getEmail(), emp.getPhone(), emp.getId());
    }

    public void deleteEmployee(UUID id) {
        if(getEmployeeById(id) == null) throw new EmployeeNotFoundException("Could not find employee with id : " + id);
        String sqlDeleteQuery = "DELETE FROM emp where id=?";
        jdbcTemplate.update(sqlDeleteQuery, id);
    }
}
