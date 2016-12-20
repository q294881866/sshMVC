<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员注册</title>

<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<script>
	function checkForm(){
		// 校验用户名:
		// 获得用户名文本框的值:
		var username = document.getElementById("username").value;
		if(username == null || username == ''){
			alert("用户名不能为空!");
			return false;
		}
		// 校验密码:
		// 获得密码框的值:
		var password = document.getElementById("password").value;
		if(password == null || password == ''){
			alert("密码不能为空!");
			return false;
		}
		// 校验确认密码:
		var repassword = document.getElementById("repassword").value;
		if(repassword != password){
			alert("两次密码输入不一致!");
			return false;
		}
	}
	
	
	function checkUsername(){
		// 获得文件框值:
		var username = document.getElementById("username").value;
		// 1.创建异步交互对象
		var xhr = createXmlHttp();
		// 2.设置监听
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					document.getElementById("span1").innerHTML = xhr.responseText;
				}
			}
		}
		// 3.打开连接
		xhr.open("GET","${pageContext.request.contextPath}/chen/user_findByName?time="+new Date().getTime()+"&username="+username,true);
		// 4.发送
		xhr.send(null);
	}
	
	function createXmlHttp(){
		   var xmlHttp;
		   try{ // Firefox, Opera 8.0+, Safari
		        xmlHttp=new XMLHttpRequest();
		    }
		    catch (e){
			   try{// Internet Explorer
			         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			      }
			    catch (e){
			      try{
			         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			      }
			      catch (e){}
			      }
		    }

			return xmlHttp;
		 }
	
	function change(){
		var img1 = document.getElementById("checkImg");
		img1.src="${pageContext.request.contextPath}/chen/check_check?"+new Date().getTime();
	}
</script>
</head>
<body>
	<div class="register" align="center">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>会员注册</strong>USER REGISTER
					</div>
					<div>
						<s:actionerror />
					</div>
					<form id="registerForm"
						action="${ pageContext.request.contextPath }/chen/user_regist"
						method="post" novalidate="novalidate"
						onsubmit="return checkForm();">
						<table>
							<tbody>
								<tr>
									<th><span class="requiredField"></span>用户名:</th>
									<td><input type="text" id="username" name="username"
										class="text" maxlength="20" onblur="checkUsername()" /> <span
										id="span1"></span></td>
								</tr>
								<tr>
									<th><span class="requiredField"></span>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20" autocomplete="off" /> <span><s:fielderror
												fieldName="password" /></span></td>
								</tr>
								<tr>
									<th><span class="requiredField"></span>确认密码:</th>
									<td><input id="repassword" type="password"
										name="repassword" class="text" maxlength="20"
										autocomplete="off" /></td>
								</tr>
								<tr>
									<th><span class="requiredField"></span>E-mail:</th>
									<td><input type="text" id="email" name="email"
										 maxlength="200"> <span><s:fielderror
													fieldName="email" /></span></td>
								</tr>
								<tr>
									<th>姓名:</th>
									<td><input type="text" name="name" 
										maxlength="200" /> <span><s:fielderror fieldName="name" /></span>
									</td>
								</tr>

								<tr>
									<th>电话:</th>
									<td><input type="text" name="phone" /></td>
								</tr>

								<tr>
									<th>地址:</th>
									<td><input type="text" name="addr" 
										maxlength="200" /> <span><s:fielderror fieldName="addr" /></span>
									</td>
								</tr>
								<tr>
									<th><span class="requiredField"></span>验证码:</th>
									<td><span class="fieldSet"> <input type="text"
											id="checkcode" name="checkcode" class="text captcha"
											maxlength="4" autocomplete="off"><img id="checkImg"
												class="captchaImage"
												src="${pageContext.request.contextPath}/chen/check_check"
												onclick="change()" title="点击更换验证码"></span></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="注册"></td>
								</tr>
                                <tr>
                                <th>&nbsp;</th>
                                 <td>已有账号，马上去登录！<a href="${pageContext.request.contextPath }/chen/user_loginUI">登录</a></td>
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