package org.jiumao.redis.domain;

public interface RedisService {
	/**
	 * 把实体对象内容变成redis方便存储的字符串
	 * @return
	 * 	自定义规则，目前规则是用 ;;;
	 */
	String toString(Object model);
	
	/**
	 * 从redis中获取字符串并转化成相应的实体对象
	 * @return
	 */
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
	 * 			如果有多个排序要求，可以多存放几遍数据但是索引是排序字段，也可以存放在不同的数据库实现高可用
	 * @param redisString
	 * 			实现toString后的String
	 * @return
	 */
	boolean setEntry(String tableName,String key,String redisString);
	
	/**
	 * 表之间的关系，把复杂的表关系配置为主键之间的关系
	 * @param parent
	 * @param childs
	 * @return
	 */
	boolean setChild2Parent(String tableName,String parent,String... childs);
	
}
