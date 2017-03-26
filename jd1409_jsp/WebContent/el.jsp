<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.briup.bean.Student" %>
<%@ page import="com.briup.bean.Address" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>el.jsp</title>
</head>
<body>
	<h1>这里是el.jsp</h1>
	<%=request.getParameter("name") %>
	<br>
	${param.name }
	<hr>
	<% 
		pageContext.setAttribute("name1","tom1");	
		request.setAttribute("name2","tom2");	
		session.setAttribute("name3","tom3");	
		application.setAttribute("name4","tom4");	
	%>
	<%=pageContext.getAttribute("name1") %>
	<%=request.getAttribute("name2") %>
	<%=session.getAttribute("name3") %>
	<%=application.getAttribute("name4") %>
	<br>
	${pageScope.name1 }
	${requestScope.name2 }
	${sessionScope.name3 }
	${applicationScope.name4 }
	<br>
	${name1 }
	${name2 }
	${name3 }
	${name4 }
	<hr>
	<% 
		//pageContext.setAttribute("name1","tom1");	
		request.setAttribute("age",20);	
		session.setAttribute("age",30);	
		//application.setAttribute("name4","tom4");	
	%>
	${age }
	<hr>
	<% 
		Student stu = new Student(1,"zhangsan",20);
		Address add = new Address();
		add.setCity("shanghai");
		stu.setAddress(add);
		request.setAttribute("student",stu);	
	%>
	<%
		Student s = (Student)request.getAttribute("student");
	%>
	<%=s.getId() %>
	<%=s.getName() %>
	<%=s.getAge() %>
	<br>
	${student.id }
	${student.name }
	${student.age }
	${student.score }
	${student.address.city }
	<hr>
	${name }
	${"name" }
	${1+1 }
	${(1+2)*3-4+5*3 }
	${1<3 }
	${empty "" }
	${empty "hello" }
	<br>
	${param.num > 50 }
	<hr>
	<%
		String[] str = {"hello","world"};
	
		List<String> list = new ArrayList<String>();
		list.add("zhangsan");
		list.add("lisi");
		list.add("red");
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("a",100);
		map.put("b",200);
		map.put("c",300);
		
		request.setAttribute("str",str);
		request.setAttribute("list",list);
		request.setAttribute("map",map);
		
	%>
	${str[0] }
	${list[0] }
	${map["c"] }
	
	<font color="${list[2] }">你好</font>
</body>
</html>