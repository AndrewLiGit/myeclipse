<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//结果   /jd1409_jsp 
	String path = request.getContextPath();
	//结果  http://127.0.0.1:8003/jd1409_jsp/
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>path.jsp</title>
</head>
<body>
	
	<h1>这里是path.jsp</h1>
	<a href="login.html">点击访问login.html</a>
	
</body>
</html>