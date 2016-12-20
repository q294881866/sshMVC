package org.jiumao.talentMarket.action;

import org.jiumao.login.LoginProcess;
import org.jiumao.talentMarket.domain.Employee;
import org.jiumao.talentMarket.service.EmployeeService;

import base.BaseAction;

public class EmployeeAction extends BaseAction<Employee> implements LoginProcess<Employee>{

	EmployeeService employeeService = (EmployeeService) baseService;
	/**
	 * 用户登录
	 */
	@Override
	public Employee doLogin(String userName, String password) {
		 return employeeService.login(userName, password);
	}

	

}
