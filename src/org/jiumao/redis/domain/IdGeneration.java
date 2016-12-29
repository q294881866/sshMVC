package org.jiumao.redis.domain;


import jdbcUtils.IdGenerator;

import org.jiumao.redis.JedisPoolUtils;

import redis.clients.jedis.Jedis;

public class IdGeneration implements IdGenerator<Integer>{
	
	public static final String ActivityId = "ActivityId";
	public static final String ActivityPlanningId = "ActivityPlanningId";
	public static final String ApproveId = "ApproveId";
	public static final String EmployeeId = "EmployeeId";
	public static final String GoodsId = "GoodsId";
	private String IDName;
	
	/*static{
		Jedis connection = JedisPoolUtils.getConnection();
		connection.select(9);//选择9号数据库
		connection.set(ActivityId,1+"");
		System.out.println(connection.get("ActivityId"));
		connection.set(ActivityPlanningId,1+"");
		connection.set(ApproveId,1+"");
		connection.set(EmployeeId,1+"");
		connection.set(GoodsId,1+"");
		JedisPoolUtils.free(connection);
	}*/

	 public Integer getId(){
		 Jedis connection = JedisPoolUtils.getConnection();
		 connection.select(9);//选择9号数据库
		 int id = Integer.parseInt(connection.get(IDName));
		 synchronized (IDName) {
			 connection.set(IDName,id+++"");
		 }
		JedisPoolUtils.free(connection);
		return id;
	 }
	 
	 public IdGeneration(String IDName) {
		 this.IDName = IDName;
	}
	 public static int getIdByString(String IDName){
		 return new IdGeneration(IDName).getId();
	 }

	 

}