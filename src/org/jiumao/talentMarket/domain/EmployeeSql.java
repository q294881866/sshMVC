package org.jiumao.talentMarket.domain;

public class EmployeeSql {

	
	public static String findUserByUserNameAndPassword = //
			"select id as id,userName as userName,password as password from employee"
			+ " where  userName = ? and password = ?";
}
