package com.yihoyoung.game.dao;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class GameDao {
	final static Logger logger = LoggerFactory.getLogger(GameDao.class);

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public GameDao() {
	}

	protected void finalize() throws Throwable {
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

}
