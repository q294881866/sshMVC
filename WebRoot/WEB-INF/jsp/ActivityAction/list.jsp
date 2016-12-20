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
     
     
     <table>
     	<tr><th>活动名称</th> <th>状态</th> <th>操作</th> <th>策划人</th>  </tr>
     	<c:forEach items="${activityList }" var="activity">
     	<tr>
			<c:choose>

				<c:when test="${activity.state}">

					<td><a href="/sshTest/talentMarket/activity_detail?id=${activity.id }&readonly=readonly"> ${activity.activityName }</a></td>
					<td>已经审核</td>
					<td>删除活动</td>
				</c:when>

				
				<c:otherwise>

					<td><a href="/sshTest/talentMarket/activity_detail?id=${activity.id }&readonly="> ${activity.activityName }</a></td>
					<td> 未通过审核</td>
					<td><a href="/sshTest/talentMarket/activity_postActivity?activityId=${activity.id }">提交审核</a></td>
				</c:otherwise>

			</c:choose>

     	 <td> ${activity.activityPlanPeople }</td>
     	 
     	 </tr>
	    </c:forEach>
     </table>
	  
  </body>
</html>
