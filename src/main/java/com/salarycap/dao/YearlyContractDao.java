package com.salarycap.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.salarycap.annotations.TableName;
import com.salarycap.dao.mapper.GenericObjectMapper;
import com.salarycap.resources.YearlyContract;

@Component
public class YearlyContractDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<YearlyContract> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM " + YearlyContract.class.getAnnotation(TableName.class).value(), 
        		new GenericObjectMapper<YearlyContract>(YearlyContract.class));
    }    

    public List<YearlyContract> getAllByTeam(Integer teamId) {
        return this.jdbcTemplate.query("SELECT * FROM " + YearlyContract.class.getAnnotation(TableName.class).value()
        		+ " where team = " + teamId, new GenericObjectMapper<YearlyContract>(YearlyContract.class));
    }
}