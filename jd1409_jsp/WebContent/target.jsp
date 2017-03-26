<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>target.jsp</title>
</head>
<body>
	<h1>这里是target.jsp页面</h1>
	<%=request.getParameter("age") %>
	<br>
	<%=request.getParameter("username") %>
</body>
</html>