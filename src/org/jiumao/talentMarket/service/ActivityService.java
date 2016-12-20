package org.jiumao.talentMarket.service;


import java.util.List;

import org.jiumao.talentMarket.domain.Activity;

import base.BaseService;

public interface ActivityService extends BaseService<Activity>{

	/**
	 * 把策划活动提交给上一级
	 * @return
	 */
	Integer setHandler(Integer activityId);

	List findShopList(int i, int j);
	
	

}
