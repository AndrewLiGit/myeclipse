<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register.jsp</title>
</head>
<body>
	
	<h1>学生用户注册的页面</h1>
	
	<form action="StudentRegisterServlet" method="post">
		用户名: <input type="text" name="username" /><br>
		年龄: <input type="text" name="age" /><br>
		<input type="submit" value="提交" />
	</form>
	
</body>
</html>