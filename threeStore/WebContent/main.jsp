<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
--el
<h4>欢迎您：  ${sessionScope.cus.username },您的地址 ${sessionScope.cus.address }</h4>
struts--ongl
<h4>欢迎您：  <s:property value="#session.cus.username"/>  ,您的地址<s:property value="#session.cus.address"/></h4>
<s:property value="customer.username"/>
<s:property value="valueStackStr"/>
<hr>
<s:debug></s:debug>
主页面
。。。
</body>
</html>