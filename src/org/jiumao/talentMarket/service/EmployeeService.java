package org.jiumao.talentMarket.service;

import org.jiumao.talentMarket.domain.Employee;

import base.BaseService;

public interface EmployeeService extends BaseService<Employee>{

	/**
	 * login
	 * 
	 * @param userName
	 * @param password
	 * @return 
	 */
	Employee login(String userName, String password);


}
