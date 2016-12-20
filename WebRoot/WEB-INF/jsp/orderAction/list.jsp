<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <center><h1>订单详情</h1></center>
	<center>
		<table border="solid #000 1px"
			style="border-collapse:collapse;border:none;">
			<tr>
				<th>商品号</th>
				<th>商品名称</th>
				<th>购买商品数量</th>
				<th>所购商品价格</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${orderList }" var="order">
				<tr>
					<td><a href="/sshTest/order/order_findById?id=${order.id }">
							${ order.id}</a></td>
					<td>${ order.goodsName}</td>
					<td>${ order.goodsNumbers}</td>
					<td>${ order.price}</td>
					<td><a href="/sshTest/order/order_deleteById?id=${order.id }">
							取消订单</a></td>
				</tr>
			</c:forEach>
		</table>
	</center>


	  
  </body>
</html>
