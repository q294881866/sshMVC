<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
 function oncheck(){
	 alert("确认删除！");
 }
 
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>角色管理</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="span10 last">
		<%@ include file="menu.jsp"%>

	</div>
	<div align="center">
	<h1>用户管理</h1>
</div>
	<div>
		<table border="1" align="center">
			<tr>
				<td>用户名</td>
				<td>真实姓名</td>
				<td>密码</td>
				<td>电话</td>
				<td>邮箱</td>
				<td>地址</td>
				<td>删除</td>
				<td>角色设置</td>
			</tr>
			<c:forEach var="c" items="${list}">
			 <tr>
			 <td><c:out value="${c.username }" escapeXml="true"/></td>
	    		<td><c:out value="${c.name }" escapeXml="true"/></td>
	    		<td><c:out value="${c.password }" escapeXml="true"/></td>
	    		<td><c:out value="${c.phone }" escapeXml="true"/></td>
	    		<td><c:out value="${c.email }" escapeXml="true"/></td>
	    		<td><c:out value="${c.addr}" escapeXml="true"/></td>
	    
			 <td><a href="${pageContext.request.contextPath }/chen/user_delete?userid=${c.uid}"><input type="submit" value="删除" onclick="return oncheck();"/></a></td>
			 <td><a href="${pageContext.request.contextPath }/chen/role_setRoleForUserUI?userid=${c.uid}"><input type="submit" value="角色设置"/></a></td>
			
				 </tr>
		
		</c:forEach>
		</table>
	</div>
	

</body>
</html>