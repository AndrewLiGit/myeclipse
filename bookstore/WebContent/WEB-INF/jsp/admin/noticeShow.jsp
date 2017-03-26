<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.Notice"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>公告详情</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/noticeShow.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/topLeft.js"></script>
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/admin/topLeft.jsp" %>
    
    <div class="disabled_screen" id="deleteMark" style="display:none"></div>
    <div class="pop_win" id="deleteWin" style="display:none">
        <div class="close"></div>
        <div class="prompt">您确定要删除此公告？</div>
        <div class="action">
            <div class="yes">确定</div>
            <div class="action_item">取消</div>
            <input type="hidden" class="notice_id" />
        </div>
    </div>
    
    <%
    	Notice notice = (Notice)request.getAttribute("notice");
     %>
    <div class="main">
		<h2>公告详情</h2>
		<div class="manage">
			<br>
	     	<h1 class="title"><%=notice.getTitle() %></h1>
	     	<br>
	     	<div class="content">
	     		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=notice.getContent() %></p>
	     	</div>
		 </div>
	</div>
	<br>
    <div id="action">
    	<input type="hidden" id="notice_id" value="<%=notice.getId() %>"/>
    	<div id="modify">修改</div>
    	<div id="delete">删除</div>
    </div>
    <br>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/noticeShow.js"></script>
  </body>
</html>
