<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>权限设置</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">

    </script>
</head>

<body>


	<div class="span10 last">
		<%@ include file="menu.jsp"%>

	</div>
	<h1>
	权限设置
	</h1>
<form action="${ pageContext.request.contextPath }/chen/privilege_setPrivilegeForRole" method="post">
	<table border="1" align="center">

         	<c:forEach var="c" items="${list}">
         	<tr>
         <td>	<c:out value="${c.name }"></c:out>
         	<input type="checkbox" name="privilegeIds" value="${c.id }"/></td>
		</tr>
         	</c:forEach>
		
	<tr><td><input type="submit" name="submit" value="提交"/></td></tr> 
		</table>
 
</form>
</body>
</html>