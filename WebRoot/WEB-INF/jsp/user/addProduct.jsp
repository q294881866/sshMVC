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
<title>产品添加</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="span10 last">
		<%@ include file="menu.jsp"%>

	</div>
	<h1>产品添加</h1>

 <form action="${pageContext.request.contextPath }/chen/product_addProduct" enctype="multipart/form-data">
	  <table border="1">
		<tr>
		<td>产品名称：</td>
		<td><input type="text" name="name"/></td>
		</tr>
		<tr>
		<td>产品描述：</td>
		<td><input type="text" name="description"/></td>
		</tr>
		<tr>
		<td>产品价格：</td>
		<td><input type="text" name="price"/></td>
		</tr>
		<tr>
		<td>产品图片：</td>
		<td><input name="upload" type="file" value="浏览..."/></td>
		</tr>
		
		
		<tr><td>
		<input  type="submit" value="添加"/>
		</td></tr>
		
		</table>
		</form>
</body>
</html>