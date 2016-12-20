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
	
	<h1 align="center">角色管理</h1>

	<div>
		<table border="1" align="center">
			<tr>
				<td>角色名称</td>
				<td>描述</td>
				<td>删除</td>
				<td>修改</td>
				<td>权限设置</td>

			</tr>

		
		<c:forEach var="c" items="${list}">
		<tr>
		 <td><c:out value="${c.name }" escapeXml="true"/></td>
		 <td><c:out value="${c.description }" escapeXml="true"/></td>
	 <td><a href="${pageContext.request.contextPath }/chen/role_delete?roleid=${c.id}"><input type="submit" value="删除" onclick="return oncheck();"/></a></td>
	 <td><a href="${pageContext.request.contextPath }/chen/role_updateUI?roleid=${c.id}"><input type="submit" value="修改" /></a></td>
			 <td><a href="${pageContext.request.contextPath }/chen/privilege_listPrivilegeUI?roleid=${c.id}"><input type="button" value="权限设置"/></a></td>
			
		</tr>
		</c:forEach>
		</table>
	</div>
	<div align="center" >
	<a href="${pageContext.request.contextPath }/chen/role_addRoleUI">添加角色</a>
</div>

</body>
</html>