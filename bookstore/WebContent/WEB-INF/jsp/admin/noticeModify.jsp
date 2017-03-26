<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.Notice"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>编辑公告</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/noticeModify.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/topLeft.js"></script>
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/admin/topLeft.jsp" %>
    
    <%
    	Notice notice = (Notice)request.getAttribute("notice");
     %>
    <div class="main">
		<h2>编辑公告</h2>
		<form action="<%=PathUtil.getFullPath("notice/save")%>" id="modify_form" method="post">
			<div class="manage">
				<br>
		     	<input class="id" name="id" type="hidden"  value="<%=notice.getId() %>" />
		     	<input class="title" name="title" placeholder="请在此输入公告标题"  value="<%=StringUtil.htmlEncode(notice.getTitle()) %>" />
		     	<br>
		     	<textarea class="content" name="content" placeholder="请在此输入公告内容" ><%=StringUtil.htmlEncode(notice.getContent()) %></textarea>
			 </div>
		 </form>
	</div>
	<br>
    <div id="action">
    	<div id="modify">保存</div>
    	<div id="cancel" onclick="javascript:location.href=document.referrer;">取消</div>
    </div>
    <br>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/noticeModify.js"></script>
  </body>
</html>
