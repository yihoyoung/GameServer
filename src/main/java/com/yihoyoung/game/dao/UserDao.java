package com.yihoyoung.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
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

	public Map<String, Object> userInfoByUserId(String user_id) {
		String sql = "select * from user where user_id = :user_id";
		Map<String, Object> userInfo = null;
		userInfo = jdbcTemplate.queryForMap(sql, user_id);
		return userInfo;
	}

	public int generatorUser(final String user_id, final String user_pwd,
			final String user_name, final int level, final int exp,
			final int gold, final int crystal, final int energy) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con
						.prepareStatement(
								"INSERT INTO User (user_id, user_pwd, user_name, level, ,exp, gold, crystal, energy) VALUES ("
										+ "?"
										+ "?"
										+ "?"
										+ "?"
										+ "?"
										+ "?"
										+ "?" + "?" + ")",
								new String[] { "user_no" });
				pstmt.setString(1, user_id);
				pstmt.setString(2, user_pwd);
				pstmt.setString(3, user_name);
				pstmt.setInt(4, level);
				pstmt.setInt(5, exp);
				pstmt.setInt(6, gold);
				pstmt.setInt(7, crystal);
				pstmt.setInt(8, energy);
				return pstmt;
			}
		}, keyHolder);

		if (count < 1) {
			logger.info("Generator is fail.");
		}

		return (Integer) keyHolder.getKey();
	}

}
