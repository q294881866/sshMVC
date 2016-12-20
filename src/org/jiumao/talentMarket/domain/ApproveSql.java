package org.jiumao.talentMarket.domain;

public class ApproveSql {
	public static final String setHandlerWithActivityIdByEmployeeId = "UPDATE `approve` SET `handler`= ?  WHERE (`activityId`=?)";
	public static String getSuperId = "SELECT  PId FROM privilege WHERE  CId = (SELECT 	app.`handler` FROM 	approve AS app WHERE 	app.activityId = ?) ";
	// 根据用户名和密码查看用户
	public static String findByUsername = "select * from user where username=?";
	// 注册用户insert into users(id,username,password,email,birthday)
	// values(?,?,?,?,?)
	public static String addUser = "insert into user (uid,username,password,email,phone,addr,picture) values(?,?,?,?,?,?,?)";
	// 得到所有用户
	public static String getAllUser = "select * from user";
	// 查看角色是否有权限
	public static String findRolePrivilege = "select * from role_privilege where roleid=? and privilegeid=?";
	// 查看用户是什么角色
	public static String findUserRole = "select * from user_role where userid=? and roleid=?";
	// 添加角色
	public static String addRole = "insert into role (name,description) values(?,?)";
	// 添加产品
	public static String addProduct = "insert into product (name,price,date,picture,description) values(?,?,?,?,?)";
	// 设置权限
	public static String addRolePrivilege = "insert into role_privilege (roleid,privilegeid) values(?,?)";
	//
	public static String addUserRole = "insert into user_role (userid,roleid) values(?,?)";
	// 得到所有角色
	public static String getAllRole = "select * from role";
	// 得到所有权限信息
	public static String getAllPrivilege = "select * from privilege";
	// 根据id得到角色信息
	public static String findRoleById = "select * from role where id=?";
	// 删除角色
	public static String deleteRole = "delete from role where id=?";
	// 修改角色
	// "update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,preference=?,type=?,description=? where id=?";
	public static String updateRole = "update role set name=?,description=? where id=?";

}
