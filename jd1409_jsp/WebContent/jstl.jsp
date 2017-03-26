<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<%@page import="com.briup.bean.Student" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jstl.jsp</title>
</head>
<body>
	
	<%
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1,"tom1",1));
		list.add(new Student(2,"tom2",2));
		list.add(new Student(3,"tom3",3));
		list.add(new Student(4,"tom4",4));
		list.add(new Student(5,"tom5",5));
		
		request.setAttribute("students", list);
		
	%>
	<table>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
		</tr>
		
		<c:forEach items="${students }" var="s">
			<tr>
				<td>${s.id }</td>
				<td>${s.name }</td>
				<td>${s.age }</td>
			</tr>
		</c:forEach>
		
	</table>
	<hr>
	<%
		Map<Long,Student> map = new HashMap<Long,Student>();
		map.put(1L, new Student(1,"tom1",1));
		map.put(2L, new Student(2,"tom2",2));
		map.put(3L, new Student(3,"tom3",3));
		map.put(4L, new Student(4,"tom4",4));
		map.put(5L, new Student(5,"tom5",5));
		request.setAttribute("map", map);
		
	%>
	<c:forEach items="${map }" var="entry">
		${entry.key } ---> ${entry.value.id } - ${entry.value.name } - ${entry.value.age }
		<br>
	</c:forEach>
	
	<hr>
	<c:out value="${students[0].name }"></c:out>
	<c:out value="${'zhangsan' }"></c:out>
	<br>
	<c:set var="num" value="100" scope="request"></c:set>
	num = ${num }
	<br>
	<c:remove var="num" scope="request"/>
	num = ${num }
	<br>
	<c:set var="num" value="50" scope="request"></c:set>
	<c:if test="${num >=80 }">
		<font color="red">优秀</font>
	</c:if>
	<hr>
	<c:set var="num" value="100" scope="request"></c:set>
	<c:choose>
		<c:when test="${num >=90 }">优</c:when>
		<c:when test="${num >=80 }">良</c:when>
		<c:when test="${num >=70 }">中</c:when>
		<c:when test="${num >=60 }">差</c:when>
		<c:otherwise>不及格</c:otherwise>
	</c:choose>
	
	
</body>
</html>