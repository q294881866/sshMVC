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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="style/js/jquery.js"></script>
	<script type="text/javascript">
		/* if(!empty($_POST['goods'])){
			for(var i=0; $i< count($_POST['goods']); $i++){
			echo $array[$i]+'<br />';
			}
		} */
	</script>
  </head>
  
  <body>
    ${object.userName }
    <h1>创建活动页面</h1>
    <form action="/sshTest/talentMarket/activity_add" method="post">
	    title：<input type="text" name="activityName" />
	         选择参加活动的商品：<br>
	    <c:forEach items="${goodsList }" var="goods">
	    <a href=""> ${goods.goodsName } </a><input type="checkbox" name="goods" value="${goods.id }"/>
	    </c:forEach><br>
	    <input type="hidden" name="activityPlanPeople" value="${object.id }"/>
	    <input type="submit" value="下一步" />
    </form>
    ps：下一步为这个活动添加策略
  </body>
</html>
