package com.salarycap.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.salarycap.annotations.TableName;
import com.salarycap.dao.mapper.GenericObjectMapper;
import com.salarycap.resources.ContractOverview;

@Component
public class ContractOverviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ContractOverview> getAllByTeam(Integer team) {
        return this.jdbcTemplate.query("SELECT * FROM " + ContractOverview.class.getAnnotation(TableName.class).value()
        		+ " where team = " + team, new GenericObjectMapper<ContractOverview>(ContractOverview.class));
    }

	public List<ContractOverview> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM " + ContractOverview.class.getAnnotation(TableName.class).value(),
        		new GenericObjectMapper<ContractOverview>(ContractOverview.class));
	}
}