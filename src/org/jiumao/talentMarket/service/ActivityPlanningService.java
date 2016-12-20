package org.jiumao.talentMarket.service;


import org.jiumao.talentMarket.domain.ActivityPlanning;

import base.BaseService;

public interface ActivityPlanningService extends BaseService<ActivityPlanning>{

	ActivityPlanning getActivityPlanningByActivityId(Integer id);
	double post(String[] goodsIds,String[] goodsNum ,Integer activityId);

}
