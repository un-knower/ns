package com.newsee.redis.util;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.newsee.redis.config.RedisPoolConfig;

import redis.clients.jedis.Jedis;

/**
 * redis操作工具类
 * @author xiaoss
 * @desc
 *
 */
@Component
public class RedisUtil {
	
	//redis连接池配置类
	@Autowired
	private RedisPoolConfig redisPoolConfig;

	/**
	 * 获取redis的resource
	 * @return
	 */
	public Jedis getResource() {
		return redisPoolConfig.getJedisPool().getResource();
	}

	/**
	 * 获取redis的resource
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	public void returnResource(Jedis jedis) {
		if(jedis != null){
			redisPoolConfig.getJedisPool().returnResourceObject(jedis);
		}
	}

	/**
	 * 将String类型的数据设置到redis中
	 * @param key
	 * @param value
	 */
	public void setStringValue(final String key, final String value) {
		Jedis jedis=null;
		try{
			jedis = getResource();
			jedis.set(key, value);
			//logger.info("Redis set string value success: key: " + key + ", value:" + value);
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Redis set error: key:"+ key + ", value:" + value, e);
		}finally{
			returnResource(jedis);
		}
	}
	
	/**
	 * 将String类型的数据设置到redis中
	 * @param key
	 * @param value
	 * @param seconds 多少秒后过期
	 */
	public void setStringValue(final String key, final String value, final int seconds) {
		Jedis jedis=null;
		try{
			jedis = getResource();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
	}
	
	/**
	 * 从redis中获取String类型的数据
	 * @param key
	 * @return
	 */
	public String getStringValue(final String key) {
		String result = null;
		Jedis jedis=null;
		try{
			jedis = getResource();
			result = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
		return result;
	}
	
	
	/**
	 * 将Object类型的数据设置到redis中
	 * @param key
	 * @param value
	 */
	public void setObjectValue(final String key, final Object value) {
		Jedis jedis=null;
		try{
			jedis = getResource();
			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
	}
	
	/**
	 * 将Object类型的数据设置到redis中
	 * @param key
	 * @param value
	 * @param seconds 多少秒后过期
	 */
	public void setObjectValue(final String key, final Object value, final int seconds) {
		Jedis jedis=null;
		try{
			jedis = getResource();
			jedis.setex(key.getBytes(), seconds, SerializeUtil.serialize(value));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
	}
	
	
	/**
	 * 从redis中获取String类型的数据
	 * @param key
	 * @return
	 */
	public Object getObjectValue(final String key) {
		Object result = null;
		Jedis jedis=null;
		try{
			jedis = getResource();
			result = (Object) SerializeUtil.unserialize(jedis.get(key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 检查key是否存在
	 * @param key
	 * @return
	 */
	public boolean isExits(final String key){
		Jedis jedis=null;
		boolean isExits = false;
		try{
			jedis = getResource();
			isExits = jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
		return isExits;
	}
	
	/**
	 * 根据key删除村存储对象
	 * @param key
	 * @return
	 */
	public boolean delete(final String key){
		Jedis jedis=null;
		boolean isDeleted = false;
		try{
			jedis = getResource();
			Long count = jedis.del(key);
			if(count.longValue() > 0){
				isDeleted = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
		return isDeleted;
	}

	/**
	 * 根据前缀删除redis中的key值
	 * @param keyPrefix
	 * @return
	 */
	public boolean deleteByPrefix(String keyPrefix){
		Jedis jedis=null;
		boolean isDeleted = false;
		try{
			jedis = getResource();
			/*Long count = jedis.del(keyPrefix + "*");
			if(count.longValue() > 0){
				isDeleted = true;
			}*/
			 Set<String> set = jedis.keys(keyPrefix +"*");  
		     Iterator<String> it = set.iterator();  
	         while(it.hasNext()){ 
	            String keyStr = it.next();  
	            Long count = jedis.del(keyStr); 
	            if(count.longValue() > 0){
	                isDeleted = true;
	            }
	          }
		        
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
		return isDeleted;
	}
	
}
