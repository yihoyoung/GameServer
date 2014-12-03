package com.yihoyoung.game.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihoyoung.game.dao.CodeDao;
import com.yihoyoung.game.dao.GameDao;
import com.yihoyoung.game.dao.LogDao;

@Service
public class EventService {
	@Autowired
	private GameDao gameDao;
	@Autowired
	private CodeDao codeDao;
	@Autowired
	private LogDao logDao;

	public void getRewardByEventId(int userNo, int eventId) {
		Map<String, Object> rewardMaster = codeDao.getRewardMasterByEventId(eventId);
		List<Map<String, Object>> rewardList = codeDao.getRewardListByEventId(eventId);
		int idx = 0;
		int mailId = 0;
		for(Map<String, Object> reward : rewardList) {
			if(idx == 0) {
				String mailSbuject = MapUtils.getString(rewardMaster, "reward_name");
				String msg = MapUtils.getString(rewardMaster, "reward_msg");
				mailId = gameDao.sentMailMaster(mailSbuject, userNo, 0, msg, 1);
			}
			int seq = ++idx;
			gameDao.sentMailDetail(mailId, seq, MapUtils.getIntValue(reward, "item_id"), MapUtils.getIntValue(reward, "item_type"), MapUtils.getIntValue(reward, "qty"));
		}
	}
}
