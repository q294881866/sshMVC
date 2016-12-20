package org.jiumao.talentMarket.dao;


import java.util.List;

import org.jiumao.talentMarket.domain.ActivityPlanning;

import base.DaoSupport;

public interface ActivityPlanningDao extends DaoSupport<ActivityPlanning>{
	ActivityPlanning getActivityPlanningByActivityId(Integer id);
	List<ActivityPlanning> getChildsByParentId(Integer parentId);
}
