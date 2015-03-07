package com.salarycap.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.salarycap.annotations.TableName;
import com.salarycap.dao.mapper.GenericObjectMapper;
import com.salarycap.resources.Player;

@Component
public class PlayerDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Player getById(Integer playerId) {
        return this.jdbcTemplate.query("SELECT * FROM " + Player.class.getAnnotation(TableName.class).value()
        		+ " where id = " + playerId, new GenericObjectMapper<Player>(Player.class)).get(0);
    }

	public List<Player> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM " + Player.class.getAnnotation(TableName.class).value(),
        		new GenericObjectMapper<Player>(Player.class));
	}
}
