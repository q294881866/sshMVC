<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0080)http://localhost:8080/mango/login.jhtml?redirectUrl=%2Fmango%2Fcart%2Flist.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员登录</title>


<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />

</head>
<body>

	<div align="center">
		<div class="span12"></div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
                   ${message }
					</div>
					<div>
					
					</div>
					<form id="loginForm"
						action="${ pageContext.request.contextPath }/chen/user_login" method="post">
						<table>
							<tbody>
								<tr>
									<th>用户名:</th>
									<td><input type="text" id="username" name="username"
										class="text" maxlength="20"></td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20" autocomplete="off"></td>
								</tr>


								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="登 录"></td>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>还没有注册账号？</dt>
											<dd>
												快快注册！ <a
													href="${ pageContext.request.contextPath }/chen/user_registerUI">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>