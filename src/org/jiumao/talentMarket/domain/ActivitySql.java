package org.jiumao.talentMarket.domain;

import jdbcUtils.core.SQLHelper;


public class ActivitySql extends SQLHelper{
 
	public static String save = "INSERT INTO `activity` (`id`,`activityName`, `goodsIds`, `activityPlanPeople`, `state`,`price`) VALUES (?,?, ?, ?, ?,?)";
	public static String submit = "UPDATE `activity` SET `state`=1 WHERE (`id`=?)";
	public static String getSubmitActivitys = "SELECT  id, activityName, goodsIds, activityPlanPeople, state, price FROM activity  WHERE state = 1 LIMIT ?, ?";
	public static String findAll = "SELECT  id, activityName, goodsIds, activityPlanPeople, state, price FROM activity  LIMIT ?, ?";
	public static String getById = "SELECT	a.id,	a.activityName,	a.goodsIds,	a.activityPlanPeople,	a.state,	a.price FROM 	activity AS a WHERE 	a.id = ?";
//	public static String getSubmitActivitys = "SELECT  id, activityName, goodsIds, activityPlanPeople, state, price FROM activity AS a WHERE state = TRUE LIMIT ?, ?";
//	public static String findAll = "SELECT  id, activityName, goodsIds, activityPlanPeople, state, price FROM activity AS a LIMIT ?, ?";
}
