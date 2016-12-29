package org.jiumao.talentMarket.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jiumao.talentMarket.domain.Activity;
import org.jiumao.talentMarket.domain.ActivitySql;
import org.jiumao.talentMarket.domain.ApproveSql;

import base.DaoSupportImpl;

public class ActivityDaoImpl extends DaoSupportImpl<Activity> implements ActivityDao {

	@Override
	public int setHandler(Integer superId, Integer activityId) {
		try {
			return update(ApproveSql.setHandlerWithActivityIdByEmployeeId, superId,activityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Integer getSuperId(Integer employeeId) {
		Connection con = getSession().getConn();
		try {
			PreparedStatement ps = con.prepareStatement(ApproveSql.getSuperId);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int passed(Integer activityId) {
		try {
			return update(ActivitySql.submit, activityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List getSubmitActivitys(int begin, int offset) {
		try {
			return getSession().getObjects(ActivitySql.getSubmitActivitys, Activity.class, begin,offset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
