package com.yihoyoung.game.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogDao {
	final static Logger logger = LoggerFactory.getLogger(LogDao.class);
	public static LogDao thisInstance;

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public LogDao() {
		if (LogDao.thisInstance == null)
			LogDao.thisInstance = this;
	}

	protected void finalize() throws Throwable {
		logger.info("###### finalize ###### ==>" + this);
	}

	// -----------------------------------------------------------------------------------------------------------------
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;

		jdbcTemplate = new JdbcTemplate(this.dataSource);
		logger.info("###### setDataSource ###### ==>" + this + ",dataSource="
				+ dataSource.toString());
	}

	@SuppressWarnings("rawtypes")
	public List runSQL_select(String strSQL) throws SQLException {
		List rows = jdbcTemplate.queryForList(strSQL);
		return rows;
	}

	public void LogAccess(int playerID, String remote_IP,
			String access_address, String body) {

	}

	public void LogLogin(String AccountID, int playerID, String connectIP,
			String os, String ver, int isHD, String deviceModel) {
	}

	public void LogCash(int playerID, int isAdd, int useType, int value,
			int bef_cash_money, int bef_cash_event, int objectKind, int data) {
	}

	private void LogPlay(String logTable, int playerID, int logType, int CD) {
	}
}
