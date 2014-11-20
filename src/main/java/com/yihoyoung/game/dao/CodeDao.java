package com.yihoyoung.game.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CodeDao {
	final static Logger logger = LoggerFactory.getLogger(CodeDao.class);

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public CodeDao() {
	}

	protected void finalize() throws Throwable {
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public List<Map<String, Object>> getConfig() {
		String sql = "select * from Config";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getEventList(){
		String sql = "select * from Event";
		return jdbcTemplate.queryForList(sql);
	}
}
