package com.yihoyoung.game.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihoyoung.game.dao.CodeDao;
import com.yihoyoung.game.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private CodeDao codeDao;
	@Autowired
	private UserDao userDao;

	public void generatorUser(String user_id, String user_pwd, String user_name) {
		List<Map<String, Object>> configList = codeDao.getConfig();
		int initLevel = 0;
		int initExp = 0;
		int initGold = 0;
		int initCrystal = 0;
		int initEnergy = 0;

		int initEventCode = 0;
		for (Map<String, Object> config : configList) {
			if (StringUtils.equals("initLevel",
					MapUtils.getString(config, "code"))) {
				initLevel = MapUtils.getIntValue(config, "value");
			}
			if (StringUtils.equals("initExp",
					MapUtils.getString(config, "code"))) {
				initExp = MapUtils.getIntValue(config, "value");
			}
			if (StringUtils.equals("initGold",
					MapUtils.getString(config, "code"))) {
				initGold = MapUtils.getIntValue(config, "value");
			}
			if (StringUtils.equals("initCrystal",
					MapUtils.getString(config, "code"))) {
				initCrystal = MapUtils.getIntValue(config, "value");
			}
			if (StringUtils.equals("initEnergy",
					MapUtils.getString(config, "code"))) {
				initEnergy = MapUtils.getIntValue(config, "value");
			}
			if (StringUtils.equals("initEventCode",
					MapUtils.getString(config, "code"))) {
				initEventCode = MapUtils.getIntValue(config, "value");
			}
		}

		userDao.generatorUser(user_id, user_pwd, user_name, initLevel, initExp,
				initGold, initCrystal, initEnergy);
		
		if(initEventCode > 0) {
			
		}
	}
}
