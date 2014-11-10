package com.yihoyoung.game.service;

public interface CacheService {

	public <T> void put(String key, T value);
	public <T> void put(String key, T value, int expireTime);
	public <T> T get(String key);
	public void delete(String key);
	public boolean checkMemcachedFeature();
}
