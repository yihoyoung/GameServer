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

	public List<Map<String, Object>> getEventList() {
		String sql = "select * from Event";
		return jdbcTemplate.queryForList(sql);
	}

	public Map<String, Object> getEvent(int eventId) {
		String sql = "select * from Event where event_id = :event_id";
		return jdbcTemplate.queryForMap(sql, eventId);
	}

	public List<Map<String, Object>> getReward(int rewardId) {
		String sql = "select * from Reward_M m, Reward_D d where m.reward_id = d.reward_id and m.reward_id = :reward_id";
		return jdbcTemplate.queryForList(sql, rewardId);
	}

	public Map<String, Object> getRewardMasterByEventId(int eventId) {
		String sql = "select * from Reward_M where event_id = :event_id";
		return jdbcTemplate.queryForMap(sql, eventId);
	}

	public List<Map<String, Object>> getRewardListByEventId(int eventId) {
		String sql = "select d.reward_id, d.seq, d.item_id, d.item_type, d.qty from Reward_M m, Reward_D d where m.reward_id = d.reward_id and m.event_id = :event_id";
		return jdbcTemplate.queryForList(sql, eventId);
	}
}
