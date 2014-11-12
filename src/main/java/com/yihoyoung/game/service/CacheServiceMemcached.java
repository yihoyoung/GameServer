package com.yihoyoung.game.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClientIF;
import net.spy.memcached.OperationTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheServiceMemcached {

	private static final Logger logger = LoggerFactory
			.getLogger(CacheServiceMemcached.class);

	@Autowired
	MemcachedClientIF memcachedClient;

	int expireTime = 60 * 60 * 24; // 초단위

	public <T> void put(String key, T value) {
		put(key, value, this.expireTime);
	}

	public <T> void put(String key, T value, int expireTime) {

		Future<Boolean> future = memcachedClient.set(key, this.expireTime,
				value);

		try {

			if (!future.get().booleanValue()) {
				logger.info("set failed !! --> add , key=" + key);

				future = memcachedClient.add(key, this.expireTime, value);

				logger.info(String.format("set result - %s", future
						.get().toString()));
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		// logger.info(String.format("cacheServiceMemcached get :: key = %s",
		// key));

		// binary protocol 만 지원
		memcachedClient.touch(key, expireTime); // 폐기시간 연장
		/*
		 * 1.4.4 버전에서는 touch 안됨 Future<Boolean> future =
		 * memcachedClient.touch(key, expireTime); try {
		 * logger.info("memcached get touch test : future.get() " +
		 * future.get() ); } catch (InterruptedException e1) {
		 * e1.printStackTrace(); } catch (ExecutionException e1) {
		 * e1.printStackTrace(); }
		 */
		T data = null;
		try {
			data = (T) memcachedClient.get(key);
		} catch (OperationTimeoutException e) {
			logger.info(String.format(
					"[ERROR] OperationTimeoutException get :: key = %s, %s",
					key, e.toString()));
		}

		return data;
	}

	public void delete(String key) {

		Future<Boolean> future = memcachedClient.delete(key);

		try {
			if (future.get() == false) {
				logger.info("[ERROR] cacheServiceMemcached delete error. key="
								+ key);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}

	public boolean checkMemcachedFeature() {
		String key = "_checkMemcachedFeature";
		put(key, "dummy", 60);
		try {
			delete(key);
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public long incValue(String key, int addValue) {
		return memcachedClient.incr(key, addValue);
	}

	public long decValue(String key, int subValue) {
		return memcachedClient.decr(key, subValue);
	}
}
