<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.model.News"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>新闻详情-淘书网</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/newsDetail.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/top.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/top.jsp" %>
    <%
    	News news = (News)request.getAttribute("news");
     %>
     <div class="main">
     	<br><br>
     	<h1 class="title"><%=news.getTitle() %></h1>
     	<br>
     	<div class="content">
     		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=news.getContent() %></p>
     	</div>
    </div>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/top.js"></script>
  </body>
</html>
