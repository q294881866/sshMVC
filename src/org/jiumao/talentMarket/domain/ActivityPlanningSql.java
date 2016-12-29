package org.jiumao.talentMarket.domain;

import jdbcUtils.core.SQLHelper;


public class ActivityPlanningSql extends SQLHelper{
	
	
	public final static String save = "INSERT INTO `activityPlanning` (`id`, `minPrice`, `maxDiscount`, `minNums`, `maxNums`, `discount`,`parentId`, `useChildsPlanning`, `step`, `activityId`,`goodsId`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
	public final static String updateById = "UPDATE `activityPlanning` SET `activityId`=? WHERE (`id`=?)";
	public final static String getByActivityId = "SELECT id,minPrice,maxDiscount,minNums,maxNums,discount,parentId,useChildsPlanning,step,activityId,goodsId  "
			+ "FROM activityPlanning WHERE activityId = ? ";
	
	public final static String getChildsById = "SELECT ap.id,ap.minPrice,ap.maxDiscount,ap.minNums,ap.maxNums,ap.discount,ap.parentId,ap.useChildsPlanning,ap.step,ap.activityId,ap.goodsId FROM activityplanning AS ap "
			+ " WHERE ap.parentId = ?";
}
