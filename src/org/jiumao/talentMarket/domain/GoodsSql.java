package org.jiumao.talentMarket.domain;

import jdbcUtils.core.SQLHelper;


public class GoodsSql extends SQLHelper{
	
	
	public static String findAll = "SELECT * FROM `goods` LIMIT ?, ?";
	public static String getById = "SELECT goods.id,goods.goodsName,goods.price,goods.type FROM goods WHERE goods.id = ?";
	public static String findAllId = "SELECT 	g.id FROM 	goods AS g";
	
	
}
