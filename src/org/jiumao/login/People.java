package org.jiumao.login;

import java.math.BigInteger;

/**
 * 登录实体需要继承的类
 * @author Administrator
 *			登录必备的一些元素
 *				用户名、密码
 */
public abstract class People {

	private BigInteger id;
	private String userName;
	private String password;
	private String nickname;
	private String type;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	public static void main(String[] args) {
		String clazz = People.class.getSimpleName();
		System.out.println(clazz.substring(0, 1).toLowerCase()+clazz.substring(1));
	}
}
