package com.yihoyoung.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

	public int sentMailMaster(final String mailSbuject, final int toUser, final int fromUser,
			final String msg, final int rewardItemYn) {
		final String sql = "insert into Mail_M mail_subject, to_user, from_user, msg, reward_item_yn values(:mail_subject, :to_user, :from_user, :msg, :reward_item_yn)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql,
						new String[] { "mail_id" });
				pstmt.setString(1, mailSbuject);
				pstmt.setInt(2, toUser);
				pstmt.setInt(3, fromUser);
				pstmt.setString(4, msg);
				pstmt.setInt(5, rewardItemYn);
				return pstmt;
			}
		}, keyHolder);
		return (Integer) keyHolder.getKey();
	}
	
	public void sentMailDetail(int mailId, int seq, int itemId, int itemType, int qty) {
		String sql = "insert into Mail_D values ( :mail_id, :seq, :item_id, :item_type, :qty)";
		jdbcTemplate.update(sql, mailId, seq, itemId, itemType, qty);
	}
}
