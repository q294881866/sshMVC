package org.jiumao.talentMarket.service;


import java.util.List;

import org.jiumao.talentMarket.dao.ActivityDao;
import org.jiumao.talentMarket.domain.Activity;

import base.BaseServiceImpl;

public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService {

	ActivityDao dao = (ActivityDao)baseDao;
	@Override
	public Integer setHandler(Integer activityId) {
		Integer integer = dao.getSuperId(activityId);
		if (null == integer || 0 == integer) {
			//如果上级id是空设置自动提交，审核通过设置活动状态为1
			return dao.passed(activityId);
		}
		return dao.setHandler(integer,activityId);
	}

	@Override
	public List findShopList(int begin, int offset) {
		
		return dao.getSubmitActivitys(begin, offset);
//		return null;
	}


}
