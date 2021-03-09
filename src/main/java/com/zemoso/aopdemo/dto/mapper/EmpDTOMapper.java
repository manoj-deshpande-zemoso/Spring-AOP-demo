package com.zemoso.aopdemo.dto.mapper;

import com.zemoso.aopdemo.dto.EmpDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EmpDTOMapper implements RowMapper<EmpDTO> {
    @Override
    public EmpDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        EmpDTO book = new EmpDTO();
        book.setId(resultSet.getObject(1, UUID.class));
        book.setName(resultSet.getString(2));
        book.setEmail(resultSet.getString(3));
        book.setPhone(resultSet.getString(4));
        return book;
    }
}
