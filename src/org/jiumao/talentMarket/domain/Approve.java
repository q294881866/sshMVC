package org.jiumao.talentMarket.domain;

import java.math.BigInteger;

public class Approve {

	private Integer id;
	private Integer handler;
	private Integer activityId;
	private String activityName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHandler() {
		return handler;
	}
	public void setHandler(Integer handler) {
		this.handler = handler;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
}
