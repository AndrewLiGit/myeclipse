<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>三号店~注册页面</title>
</head>
<body>
<center>
	<h1>注册</h1>
	<hr>
	<form action="customer_register.action" method="post">
		用户名<input type="text" name="customer.username"/><br>
		密码<input type="text" name="customer.password"/><br>
		手机<input type="text" name="customer.telephone"/><br>
		email<input type="text" name="customer.email"/><br>
		地址<input type="text" name="customer.address"/><br>
		<input type="submit" value="注册"/><input type="reset" value="重置"/>
	
	</form>
</center>
</body>
</html>