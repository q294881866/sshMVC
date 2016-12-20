package org.jiumao.talentMarket.dao;


import java.util.List;

import org.jiumao.talentMarket.domain.Activity;

import base.DaoSupport;

public interface ActivityDao extends DaoSupport<Activity>{

	/**
	 * 
	 * @param superId
	 * @param activityId
	 * @return
	 */
	int setHandler( Integer superId,Integer activityId);
	
	Integer getSuperId(Integer employeeId);

	int passed(Integer activityId);

	List getSubmitActivitys(int begin, int offset);
	
	
}
