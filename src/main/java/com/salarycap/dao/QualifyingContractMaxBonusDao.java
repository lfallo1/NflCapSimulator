package com.salarycap.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.salarycap.annotations.TableName;
import com.salarycap.dao.mapper.GenericObjectMapper;
import com.salarycap.resources.QualifyingContractMaxBonus;

@Component
public class QualifyingContractMaxBonusDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<QualifyingContractMaxBonus> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM " + QualifyingContractMaxBonus.class.getAnnotation(TableName.class).value(), new GenericObjectMapper<QualifyingContractMaxBonus>(QualifyingContractMaxBonus.class));
    }
}
