package org.jiumao.talentMarket.dao;



import java.sql.Connection;
import java.util.List;

import org.jiumao.talentMarket.domain.ActivityPlanning;
import org.jiumao.talentMarket.domain.ActivityPlanningSql;

import base.DaoSupportImpl;

public class ActivityPlanningDaoImpl extends DaoSupportImpl<ActivityPlanning> implements ActivityPlanningDao {

	public ActivityPlanning getActivityPlanningByActivityId(Integer id) {
		// TODO Auto-generated method stub
		try {
			return (ActivityPlanning) mySqlWriterSessionFactory.getObject(ActivityPlanningSql.getByActivityId, ActivityPlanning.class, id);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List getChildsByParentId(Integer parentId) {
		try {
			System.out.println(parentId+"==parentId");
			return mySqlWriterSessionFactory.getObjects(ActivityPlanningSql.getChildsById, ActivityPlanning.class, parentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	

}
