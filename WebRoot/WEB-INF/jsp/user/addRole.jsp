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
<title>添加角色</title>

</head>

<body>


	
	<h1>添加角色</h1>
<form action="${ pageContext.request.contextPath }/chen/role_addRole" method="post">
        <table border="1">
          <tr>
          <td>角色名称</td>
          <td>描述</td>
          </tr>
          <tr>
          <td>
       <input name="name" type="text"/></td>
       <td><input name="description" type="text"/></td>

          </tr>
          <tr><td>
<input  type="submit" name="submit" value="提交"/>
</td></tr>
        </table>

	</form>
</body>
</html>