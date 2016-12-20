<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
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
     <h1>活动页面</h1>
    <form action="/sshTest/talentMarket/activity_buy" method="post">
	    title：${model.activityName },活动价${model.price }
	         选择参加活动的商品：<br>
	         
	
	   <c:forEach items="${goodsList }" var="goods">
		    <br>${goods.goodsName }<input type="checkbox" name="goods" value="${goods.id }"/> 
		    <input name="price" readonly="readonly" value="${goods.price }"/>
	    	数量：<input type="number" name="number" max="50" min="1" >
	    </c:forEach><br>
	    <input type="hidden" name="activityId" value="${model.id }"/>
	    <input type="submit" value="购买" />
    </form>
  </body>
</html>
