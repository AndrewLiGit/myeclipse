<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.briup.bean.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test3.jsp</title>
</head>
<body>
	
	<%
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1,"tom1",21));
		list.add(new Student(2,"tom2",22));
		list.add(new Student(3,"tom3",23));
		list.add(new Student(4,"tom4",24));
		list.add(new Student(5,"tom5",25));
		request.setAttribute("students",list);
	%>
	
	<table>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
		</tr>
		
		<%
			List<Student> stus = (List)request.getAttribute("students");
			for(Student s:stus){
				
				long id = s.getId();
				String name= s.getName();
				int age = s.getAge();
		%>	
				<tr>
					<td><%=id %></td>
					<td><%=name %></td>
					<td><%=age %></td>
				</tr>
		<%	
			}
		%>
		
	</table>
	
	<hr>
	
	<table>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
		</tr>
		
		<%
			List<Student> stus2 = (List)request.getAttribute("students");
			for(Student s:stus2){
				
				long id = s.getId();
				String name= s.getName();
				int age = s.getAge();
				
				out.println("<tr>");
					out.println("<td>"+id+"</td>");
					out.println("<td>"+name+"</td>");
					out.println("<td>"+age+"</td>");
				out.println("</tr>");
			}
		%>
		
	</table>
	
</body>
</html>