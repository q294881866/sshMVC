package org.jiumao.redis.domain;

import java.util.List;
import java.util.Set;

import org.jiumao.redis.JedisPoolUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public abstract class RedisServiceImpl implements RedisService{
	/**
	 * 把实体对象内容变成redis方便存储的字符串
	 * @return
	 * 	自定义规则，目前规则是用 ;;;,
	 */
	public abstract String toString(Object model);
	
	/**
	 * 从redis中获取字符串并转化成相应的实体对象
	 * @return
	 */
	public  String  getEntryString(String tableName,String key){
		Jedis connection = JedisPoolUtils.getConnection();
		String string = connection.hget(tableName, key);
		connection.zincrby(tableName, 1,key);
		JedisPoolUtils.free(connection);
		return string;
	}
	
	/**
	 * 从redis中获取字符串并转化成相应的实体对象
	 * @return
	 */
	public boolean delEntryString(String tableName,String key){
		Jedis connection = JedisPoolUtils.getConnection();
		Transaction tx = connection.multi();
		connection.hdel(tableName, key);
		connection.zrem(tableName,key);
		List<Object> l =tx.exec();
		JedisPoolUtils.free(connection);
		return null != l;
	}
	
	public abstract Object getEntryByRedisString(String redisString);
	
	/**
	 * 
	 * 把实体对象内容变成redis方便存储的字符串
	 * @return
	 * 	自定义规则，目前规则是用 ;;;,三个分号	 
	 * @param tableName
	 * 			当前实体类型对应的表名
	 * @param key
	 * 			当前实体类型对应的主键方便获取对象<br>
	 * 			如果有多个排序要求，可以多存放几遍数据但是索引是排序字段，也可以存放在不同的数据库实现高可用<br>
	 * 			如果有多个排序要求，如按姓名字段排序。为了节约内存，可以吧redisString写成此条数据id，通过id获取此条数据
	 * 			如果有重复字段，不能用此方法。用list
	 * @param redisString
	 * 			实现toString后的String
	 * @return
	 */
	public boolean setEntry(String tableName,String key,String redisString) {
		Jedis connection = JedisPoolUtils.getConnection();
		Long l = connection.hset(tableName, key, redisString);
		connection.zadd(tableName,1, key);
		JedisPoolUtils.free(connection);
		return null != l;
	}

	public boolean setChild2Parent(String tableName,String parent,String... childs) {
		String childString = "";
		for (int i = 0; i < childs.length; i++) {
			childString = childString+childs[i]+";;;";
		}
		Jedis connection = JedisPoolUtils.getConnection();
		connection.hset(tableName+"_Child2Parent", parent, childString);
		JedisPoolUtils.free(connection);
		return true;
	}
	
	public String[] getChildByParent(String tableName,String parent){
		Jedis connection = JedisPoolUtils.getConnection();
		String s = connection.hget(tableName+"_Child2Parent", parent);
		JedisPoolUtils.free(connection);
		return s.split(";;;");
	}
	
	/**
	 * 按成绩排序，安装redis中score排序
	 * @param tableName
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> getList(String tableName, Integer start,Integer end){
		Jedis connection = JedisPoolUtils.getConnection();
		Set<String> sets = connection.zrange(tableName, start, end);
		JedisPoolUtils.free(connection);
		return sets;
	}
	
	/**
	 * 用于分页的操作
	 * @param firstResult
	 * @param offset
	 * @return
	 */
	public abstract List<String> getPageList(int firstResult,int offset);
	
	/**
	 * 用于初始化操作
	 * @throws Exception
	 */
	public abstract void init() throws Exception ;
	
}
