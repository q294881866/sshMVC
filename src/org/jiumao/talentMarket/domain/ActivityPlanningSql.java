package org.jiumao.talentMarket.domain;

import jdbcUtils.QueryHelper;


public class ActivityPlanningSql extends QueryHelper{
	
	
	public static String save = "INSERT INTO `activityPlanning` (`id`, `minPrice`, `maxDiscount`, `minNums`, `maxNums`, `discount`,`parentId`, `useChildsPlanning`, `step`, `activityId`,`goodsId`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
	public static String updateById = "UPDATE `activityPlanning` SET `activityId`=? WHERE (`id`=?)";
	public static String getByActivityId = "SELECT id,minPrice,maxDiscount,minNums,maxNums,discount,parentId,useChildsPlanning,step,activityId,goodsId  "
			+ "FROM activityPlanning WHERE activityId = ? ";
	
	public static String getChildsById = "SELECT ap.id,ap.minPrice,ap.maxDiscount,ap.minNums,ap.maxNums,ap.discount,ap.parentId,ap.useChildsPlanning,ap.step,ap.activityId,ap.goodsId FROM activityplanning AS ap "
			+ " WHERE ap.parentId = ?";
}
