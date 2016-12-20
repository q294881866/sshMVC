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
    <form action="/sshTest/talentMarket/activityPlanning_add" method="post">
	    <input type="hidden" name="activityId" value="${activityId }"/><br>
	    定义最小价格：<input type="number" name="minPrice"/><br>
	    定义最小购买数量：<input type="number" name="minNums"/><br>
	    定义最大购买数量：<input type="number" name="maxNums"/><br>
	    定义最大折扣：<input type="number" name="maxDiscount"/><br>
	    起始折扣：<input type="number" name="discount"/><br>
	    是否给每个子商品定义规则:是：<input type="radio" name="isUseChildsDiscount" value="1" >否：<input type="radio"  name="isUseChildsDiscount" value="0"><br>
	    商品数每增加一个，折扣减多少。如加一个件减一折<input type="number" name="step" />
	    <input type="submit" value="提交" />
    </form>
  </body>
</html>
