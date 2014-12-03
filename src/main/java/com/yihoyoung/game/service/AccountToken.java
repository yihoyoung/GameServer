package com.yihoyoung.game.service;

import java.io.Serializable;
import java.util.UUID;

public class AccountToken implements Serializable {

	//private static final long serialVersionUID = -6949485834673858852L;

	/**
	 * 
	 */
	private String userId = null;
	private String key = null;
	private long issueTime = 0; // format - unix time

	public String getUserId() {
		return userId;
	}

	public String getKey() {
		return key;
	}

	public long getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(long issueTime) {
		this.issueTime = issueTime;
	}

	public AccountToken(String userId) {
		this.userId = userId;
		this.key = (UUID.randomUUID()).toString();
		this.issueTime = System.currentTimeMillis() / 1000;
	}

	public String getAccountToken() {
		return String.format("%s.%s.%d", userId, key, issueTime);
	}

	public void parseValue(String tokenString) {
		if (tokenString == null) {
			return;
		}
		String[] values = tokenString.split(".");
 
		if (values.length != 3) {
			return;
		}
		this.userId = values[0];
		this.key = values[1];
		this.issueTime = Long.parseLong(values[2]);
	}

}
