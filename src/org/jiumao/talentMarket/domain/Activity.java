package org.jiumao.talentMarket.domain;


import base.BaseBean;

public class Activity extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4670077284453319341L;
	private Integer id;
	private String activityName;
	private String goodsIds;
	private Integer activityPlanPeople;
	private boolean state;
	private double price;//套餐价格
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}
	public Integer getActivityPlanPeople() {
		return activityPlanPeople;
	}
	public void setActivityPlanPeople(Integer activityPlanPeople) {
		this.activityPlanPeople = activityPlanPeople;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

}
