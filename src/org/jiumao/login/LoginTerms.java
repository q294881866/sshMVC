package org.jiumao.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jiumao.talentMarket.action.EmployeeAction;



/**
 * 登录通用功能处理
 * @author Administrator
 *
 */
public class LoginTerms {
	
	Logger logger = Logger.getLogger(LoginTerms.class);
	/**
	 * System.out.println("添加功能");
	 * LoginTerms.addCondition("学生", StudentAction.class);
	 */
	static{
			conditions = new HashMap<String, Class>();
			choices = new StringBuilder();
			LoginTerms.addCondition("员工", EmployeeAction.class);
	}
	private static Map<String, Class> conditions ;
	//用;;;拼接的登录类型，用户可以选择某种登录类型
	public static StringBuilder choices ;
	//读写锁
//	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	
	/**
	 * 如果需要设置新的处理条件
	 * 那么以前的处理条件将失效
	 * 设置处理的各种规则
	 * 如：学生、教室、企业登录，
	 * 选择学生登录就交给相应的处理类
	 * @param conditions
	 */
	public static void setConditions(Map<String,Class> condition) {
//		lock.writeLock().lock();
		conditions = condition;
//		lock.writeLock().unlock();
	}
	 
	 /**
	  * 添加新的处理条件
	  * @param key 处理的关键字
	  * @param condition 处理的条件
	  */
	public static void addCondition(String key,Class condition) {
		 conditions.put(key, condition);
		 choices.append(key+";;;");//;;;
	}
	
	/**
	 * 用户选择系统的功能
	 * 如用户选择用户登录、管理员登录等功能
	 */
	public static LoginProcess doChoice(String content){
		Class clazz = conditions.get(content);
		System.out.println("clazz== "+clazz+"|| choices=="+choices);
		try {
			//返回一个
			if (null!=clazz) {
				return (LoginProcess) clazz.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		return null;
	}
	
	
}

