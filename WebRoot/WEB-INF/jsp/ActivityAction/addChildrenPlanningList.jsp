<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
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
    <h1>添加活动策划页面</h1>
    <c:forEach items="${goodsList }" var="goods">
	    <a href="/sshTest/talentMarket/activityPlanning_addChildrenPlanningUI?goodsId=${goods.id }"> ${goods.goodsName } </a>
	</c:forEach><br>
  </body>
</html>
