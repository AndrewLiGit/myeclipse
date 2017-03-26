<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test.jsp</title>
</head>
<body>
	<!-- jsp脚本元素中的 声明 -->
	<!-- 声明中代码相当于在类中写成员变量或者成员方法 -->
	<%!
		private String name;
		public String getName(){
			return this.name;
		}
		public void setName(String name){
			this.name = name;
		}
	%>
	
	<!-- jsp脚本元素中的 脚本 -->
	<!-- 脚本中的代码相应于在java类中的_jspService方法里面写代码 -->
	<%
		int age = 20;
		this.setName("张三");
		System.out.println(age);
		session.setAttribute("test","hello world");
	%>
	
	<!-- jsp脚本元素中的 表达式 -->
	<!-- 表达式 中的代码相应于在java类中的_jspService方法里面out.print方法中的参数 -->
	<!-- out.print方法的作用是在类中向浏览器写数据 -->
	<%=this.getName() %>
	
</body>
</html>