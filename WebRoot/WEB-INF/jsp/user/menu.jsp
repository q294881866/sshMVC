<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="menu" >
	<div class="div1">
		 <c:if test="${existUser == null }">
			<div class="class1">
			  <a href="${pageContext.request.contextPath }/chen/user_loginUI">登录</a>
  
				
			</div>
			<div class="class1">
  <a  href="${pageContext.request.contextPath }/chen/user_registerUI">注册</a>
			</div>
		
</c:if>

		
  <c:if test="${existUser!=null }">
			<div class="class1">
				<a href="${ pageContext.request.contextPath }/chen/user_quit.action">退出</a>
			</div>
			<div class="class1">
				<a>个人中心</a>
			</div>
		
				
                      欢迎您！${existUser.name}
           </c:if>
				
			</div>
		
	</div>
	<div class="div2">
		<div class="class2">
			<a
				href="${pageContext.request.contextPath }/chen/role_listRoleUI">角色管理</a>
		</div>
		<div class="class2">
			<a
				href="${pageContext.request.contextPath }/chen/activity_listactivityUI?page=1">活动管理</a>
		</div>
		<div class="class2">
			<a
				href="${pageContext.request.contextPath }/chen/product_listUI?page=1">产品管理</a>
		</div>
		<div class="class2">
			<a href="${pageContext.request.contextPath }/chen/user_getAll">用户管理</a>
		</div>
	</div>
</div>

