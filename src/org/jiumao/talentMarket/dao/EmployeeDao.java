package org.jiumao.talentMarket.dao;

import org.jiumao.talentMarket.domain.Employee;

import base.DaoSupport;

public interface EmployeeDao extends DaoSupport<Employee>{

	Employee	 login(String userName, String password);
	
}
