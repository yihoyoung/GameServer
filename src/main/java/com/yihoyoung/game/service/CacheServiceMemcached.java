package com.yihoyoung.game.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClientIF;
import net.spy.memcached.OperationTimeoutException;

import org.springframework.beans.factory.annotation.Autowired;

public class CacheServiceMemcached implements CacheService {

	@Autowired
	MemcachedClientIF memcachedClient;
	
	int expireTime = 60 * 60 * 24;  //초단위 

	@Override
	public <T> void put(String key, T value) 
	{
		put(key, value, this.expireTime);
	}
	
	@Override
	public <T> void put(String key, T value, int expireTime)
	{
		//System.out.println(String.format("cacheServiceMemcached put, key = %s, expireTime = %d, value = %s", key, expireTime, value));
		
		Future<Boolean> future = memcachedClient.set(key, this.expireTime, value);
		
		try 
		{
			
			if ( !future.get().booleanValue() )
			{
				System.out.println("set failed !! --> add , key="+key);
				
				future = memcachedClient.add(key, this.expireTime, value);
				
				System.out.println(String.format("set result - %s", future.get().toString()));
			}

		} 
		catch (InterruptedException e) 
		{
			// TODO : memcached 오류 처리
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			// TODO : memcached 오류 처리
			e.printStackTrace();
		}			
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		//System.out.println(String.format("cacheServiceMemcached get :: key = %s", key));

		// binary protocol 만 지원
		memcachedClient.touch(key, expireTime);	//폐기시간 연장
		/* 1.4.4 버전에서는 touch 안됨
		Future<Boolean> future = memcachedClient.touch(key, expireTime);
		try {
			System.out.println("memcached get touch test : future.get() " + future.get() );
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
		*/
		T data = null;
		try {
			data = (T)memcachedClient.get(key);
		} catch (OperationTimeoutException e){
			System.out.println(String.format("[ERROR] OperationTimeoutException get :: key = %s, %s",key, e.toString()));
		}
		

		return data;
	}

	@Override
	public void delete(String key) 
	{
		//System.out.println(String.format("[INFO] cacheServiceMemcached del :: key = %s", key));
		
		Future<Boolean> future = memcachedClient.delete(key);
		
		try {
			if ( future.get() == false)
			{
				System.out.println("[ERROR] cacheServiceMemcached delete error. key="+key);
				
			}
		}
		catch (InterruptedException e) 
		{
			// TODO : memcached 오류 처리
			e.printStackTrace();
		}
		catch (ExecutionException e) 
		{
			// TODO : memcached 오류 처리
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean checkMemcachedFeature(){
		//memcached 기능점검
		//1.4.4 버전에서 터치 기능 안됨.
		String key="_checkMemcachedFeature";
		put(key, "dummy", 60);
		try {
			delete(key);
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public long incValue(String key, int addValue)
	{
		return memcachedClient.incr(key, addValue);
	}

	public long decValue(String key, int subValue)
	{
		return memcachedClient.decr(key, subValue);
	}
	/*
	public boolean WriteLock(int playerID, int keytype)
	{
		
		if (memcachedClient.add(playerID+"_lock_"+keytype, 1, null) != null){;//  ("lock:xyz", "1", System.currentTimeMillis() + 10000)) {
			  try {
			    //doSomeExpensiveStuff();
			  } finally {
				  memcachedClient.delete(playerID+"_lock_"+keytype);
			  }
			} else {
			  // someone else is doing the expensive stuff
			}
	}
	public boolean  DeleteLock(int playerID, int keytype) 
	{
		memcachedClient.delete(playerID+"_lock_"+keytype);
	}
		*/
}
