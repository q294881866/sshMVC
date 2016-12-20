package org.jiumao.talentMarket.service;

import org.jiumao.talentMarket.dao.EmployeeDao;
import org.jiumao.talentMarket.domain.Employee;

import base.BaseServiceImpl;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

	public EmployeeServiceImpl() {
	}

	EmployeeDao employeeDao = (EmployeeDao) baseDao;

	@Override
	public Employee  login(String userName, String password) {
		// TODO Auto-generated method stub
		return employeeDao.login(userName, password);
	}

}
