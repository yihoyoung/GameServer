package com.yihoyoung.util;

import java.util.List;
import java.util.Map;

public class DataList {

	private Map<String, Object> masterData;
	private List<Map<String, Object>> detailList;

	public DataList(Map<String, Object> masterData,
			List<Map<String, Object>> detailList) {
		this.masterData = masterData;
		this.detailList = detailList;
	}

	public void setMasterData(Map<String, Object> masterData) {
		this.masterData = masterData;
	}

	public void setDetailList(List<Map<String, Object>> detailList) {
		this.detailList = detailList;
	}

	public Map<String, Object> getMasterData() {
		return masterData;
	}

	public List<Map<String, Object>> getDetailList() {
		return detailList;
	}

}
