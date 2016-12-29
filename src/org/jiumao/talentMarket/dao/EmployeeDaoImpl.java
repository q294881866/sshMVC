package org.jiumao.talentMarket.dao;

import java.util.List;

import org.jiumao.talentMarket.domain.Employee;
import org.jiumao.talentMarket.domain.EmployeeSql;

import base.DaoSupportImpl;

public class EmployeeDaoImpl extends DaoSupportImpl<Employee> implements EmployeeDao {

	@Override
	public Employee login(String userName, String password) {
		try {
			System.out.println("employeedaoImpl");
			return (Employee) getSession().getObject(
					EmployeeSql.findUserByUserNameAndPassword, Employee.class,
					userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}





}
