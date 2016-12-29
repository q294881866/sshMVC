package org.jiumao.talentMarket.dao;



import java.util.List;

import org.jiumao.talentMarket.domain.ActivityPlanning;
import org.jiumao.talentMarket.domain.ActivityPlanningSql;

import base.DaoSupportImpl;

@SuppressWarnings("unchecked")
public class ActivityPlanningDaoImpl extends DaoSupportImpl<ActivityPlanning> implements ActivityPlanningDao {

	public ActivityPlanning getActivityPlanningByActivityId(Integer id) {
		try {
			return (ActivityPlanning) getSession().getObject(ActivityPlanningSql.getByActivityId, ActivityPlanning.class, id);
		} catch ( Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List getChildsByParentId(Integer parentId) {
		try {
			System.out.println(parentId+"==parentId");
			return getSession().getObjects(ActivityPlanningSql.getChildsById, ActivityPlanning.class, parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	

}
