<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.News"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>编辑新闻</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/newsModify.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/topLeft.js"></script>
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/admin/topLeft.jsp" %>
    
    <%
    	News news = (News)request.getAttribute("news");
     %>
    <div class="main">
		<h2>编辑新闻</h2>
		<form action="<%=PathUtil.getFullPath("news/save")%>" id="modify_form" method="post">
			<div class="manage">
				<br>
		     	<input class="id" name="id" type="hidden"  value="<%=news.getId() %>" />
		     	<input class="title" name="title" placeholder="请在此输入新闻标题"  value="<%=StringUtil.htmlEncode(news.getTitle()) %>" />
		     	<br>
		     	<textarea class="content" name="content" placeholder="请在此输入新闻内容" ><%=StringUtil.htmlEncode(news.getContent()) %></textarea>
			 </div>
		 </form>
	</div>
	<br>
    <div id="action">
    	<div id="modify">保存</div>
    	<div id="cancel"onclick="javascript: location.href=document.referrer;">取消</div>
    </div>
    <br>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/newsModify.js"></script>
  </body>
</html>
