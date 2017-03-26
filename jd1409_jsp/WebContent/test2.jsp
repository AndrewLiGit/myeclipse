<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.briup.bean.Student" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test2.jsp</title>
</head>
<body>
	
	<jsp:useBean id="s" class="com.briup.bean.Student" scope="request"></jsp:useBean>
	
	<jsp:setProperty property="name" value="lisi" name="s" />
	
	<jsp:getProperty property="name" name="s"/>
	
	<%--
	<jsp:forward page="target.jsp?age=20"></jsp:forward>
	
	<jsp:forward page="target.jsp">
		<jsp:param value="zhangsan" name="username"/>
	</jsp:forward>
	 --%>
	 
	 <jsp:element name="font">
	 	<jsp:attribute name="color">red</jsp:attribute>
	 	<jsp:body>hello world</jsp:body>
	 </jsp:element>
	 <%
	 	out.println("<font color='blue'>hello world</font>");
	 %>
	 
			 
	 <jsp:include page="foot.jsp"></jsp:include>
	 
	
</body>
</html>