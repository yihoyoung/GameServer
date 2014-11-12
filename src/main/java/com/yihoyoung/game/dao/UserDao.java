package com.yihoyoung.game.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	final static Logger logger = LoggerFactory.getLogger(UserDao.class);

	public UserDao() {
		logger.info("###### constructor ###### ==>" + this);
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public boolean isExistID(String userId) {
		String sql = "select count(*) as cnt from user where user_id = ?";
		if (jdbcTemplate.queryForInt(sql, userId) > 0)
			return true;
		else
			return false;
	}

	public Map<String, Object> login(String user_id, String password) {
		String sql = "select * from user where user_id = :user_id and password = :password";
		Map<String, Object> userInfo = null;
		userInfo = jdbcTemplate.queryForMap(sql, user_id, password);
		return userInfo;
	}

	public Map<String, Object> login(String user_id) {
		String sql = "select * from user where user_id = :user_id";
		Map<String, Object> userInfo = null;
		userInfo = jdbcTemplate.queryForMap(sql, user_id);
		return userInfo;
	}

}
