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
<title>产品管理</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="span10 last">
		<%@ include file="menu.jsp"%>

	</div>
	<div align="center">
	  <h1>产品管理</h1>
	</div>

	<div>
		<table border="1" align="center">
			<tr>
				<td>产品名称</td>
				<td>产品价格</td>
				<td>产品图片</td>
				<td>产品描述</td>
				<td>产品上传日期</td>
				<td>修改</td>
				<td>删除</td>

			</tr>

			<c:forEach var="p" items="${page.list}}"></c:forEach>
				<tr>
					<td><s:property value="#p.name" /></td>
					<td>
					<s:property value="#p.price" />
					</td>
					<td><img src="${pageContext.request.contextPath }<s:property value="#p.picture" />" alt="" width="200" height="200" /></td>
					<td><s:property value="#p.description" /></td>
					<td><s:property value="#p.date" /></td>
					<td><a
						href="${pageContext.request.contextPath }/product_updateUI?productId=<s:property  value="#p.id"/>">修改</a></td>

					<td><a
						href="${pageContext.request.contextPath }/product_delete?productId=<s:property  value="#p.id"/>"
						onclick="return oncheck();">删除</a></td>
					
			
			</tr>
		
		</table>
	      <div class="pagination" align="center">
			<span>第 <s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/> 页</span>
		
			<s:if test="pageBean.page != 1">
				<a href="${ pageContext.request.contextPath }/product_listUI.action?page=1">首页</a>
				<a href="${ pageContext.request.contextPath }/product_listUI.action?page=<s:property value="pageBean.page-1"/>" >上一页</a>
			</s:if>
			
			<s:iterator var="i" begin="1" end="pageBean.totalPage">
				<s:if test="pageBean.page != #i">
					<a href="${ pageContext.request.contextPath }/product_listUI.action?page=<s:property value="#i"/>"><s:property value="#i"/></a>
				</s:if>
				<s:else>
					<span style="color='red'"><s:property value="#i"/></span>
				</s:else>
			</s:iterator>
			
			<s:if test="pageBean.page != pageBean.totalPage">	
				<a class="nextPage" href="${ pageContext.request.contextPath }/product_listUI.action?page=<s:property value="pageBean.page+1"/>">下一页</a>
				<a class="lastPage" href="${ pageContext.request.contextPath }/product_listUI.action?page=<s:property value="pageBean.totalPage"/>">最后一页</a>
			</s:if>
	
		
	
	<a href="${pageContext.request.contextPath }/chen/product_addProductUI">添加产品</a>
</div>
</body>
</html>