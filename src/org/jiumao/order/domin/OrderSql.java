package org.jiumao.order.domin;

import jdbcUtils.SqlUtil;

public class OrderSql extends SqlUtil{
 public static String getById="SELECT o.id, o.goodsName, o.goodsIds, o.price, o.goodsNumbers, o.goodsPrice FROM orderlist AS o WHERE id=?";
 public static String deleteById="DELETE FORM order WHERE id=?";
 public static String findAll="SELECT o.id, o.goodsName, o.goodsIds, o.price, o.goodsNumbers, o.goodsPrice FROM orderlist AS o LIMIT ?, ?";


}
