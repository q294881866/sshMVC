<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>角色修改</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="span10 last">
		<%@ include file="menu.jsp"%>

	</div>
          <h1>角色修改</h1>
	<form action="${pageContext.request.contextPath }/chen/role_update" method="post">
	<input type="hidden" name="id" value="${role.id }"/>
		<table border="1" align="center">
			<tr>
				<td>角色名称：</td>
				<td><input name="name" type="text"
					value="${role.name}" /></td>
			</tr>
			<tr>
				<td>描述：</td>
				<td><input name="description" type="text"
					value=" ${role.description}" /></td>
			</tr>
            <tr>
            <td>
              <input type="submit" value="修改" />
            </td>
            </tr>
			
			</table>
		</form>
		
</body>
</html>