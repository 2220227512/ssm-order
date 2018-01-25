package com.taotao.order.dao;


/**
 * jesisDao接口
* <p>Title: JedisClient</p>  
* <p>Description: </p>  
* @author 唯  
* @date 2018-1-12
 */
public interface JedisClient {
	 String get(String key);
	 String set(String key, String value);
	 String hget(String hkey, String key) ;
	 long hset(String hkey, String key, String value) ;
	 long incr(String key) ;
	 long expire(String key, int second);
	 long ttl(String key) ;
	 long del(String key);
	 long hdel(String hkey,String key);

}
