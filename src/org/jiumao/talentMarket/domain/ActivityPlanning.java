package org.jiumao.talentMarket.domain;


import base.BaseBean;

public class ActivityPlanning extends BaseBean{

	private Integer id;
	private double minPrice;
	private float maxDiscount;
	private int minNums;
	private int maxNums;
	private float discount;
	private Integer parentId ;
	private boolean useChildsPlanning;
	private int step;
	private Integer activityId;
	private Integer goodsId;
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getMaxDiscount() {
		return maxDiscount;
	}
	public void setMaxDiscount(float maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	public int getMinNums() {
		return minNums;
	}
	public void setMinNums(int minNums) {
		this.minNums = minNums;
	}
	public int getMaxNums() {
		return maxNums;
	}
	public void setMaxNums(int maxNums) {
		this.maxNums = maxNums;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public boolean isUseChildsPlanning() {
		return useChildsPlanning;
	}
	public void setUseChildsPlanning(boolean useChildsPlanning) {
		this.useChildsPlanning = useChildsPlanning;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getActivityId() {
		return activityId;
	}
	
}
