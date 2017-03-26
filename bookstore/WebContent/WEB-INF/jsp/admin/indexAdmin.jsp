<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>
<%@ page import="com.lzjtu.bookstore.model.User"%>
<%@ page import="com.lzjtu.bookstore.util.StringUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘书网-后台管理首页</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/indexAdmin.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/indexAdmin.js"></script>
  </head>
  
  <body>
    <div id="logo"><img src="<%=PropertyUtil.getStaticUrl() %>/images/3.jpg" /></div>
    <%User user = (User)session.getAttribute(Constants.USER);
     %>
    <div id="childNav">
		<div class="welcome_wrap">
			您好，管理员&nbsp;<span><%=user.getUserName() %></span>&nbsp;&nbsp;当前时间：<span id="now_time"></span>&nbsp;，欢迎回到后台管理。&nbsp;&nbsp;<a href="<%=PathUtil.getFullPath("user/logout") %>">退出</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</div>
	
	<div id="position" class="wrap">
		您现在的位置：<a href="<%=PathUtil.getFullPath("user/index") %>">淘书网</a> &gt; 后台管理
	</div>
    
    <div id="main" class="wrap">
		<div id="menu-mng" class="lefter">
			<div class="box">
				<dl>
					<dt>用户管理</dt>
					<dd><a href="<%=PathUtil.getFullPath("user/list") %>">用户列表</a></dd>
					<dt>图书管理</dt>
					<dd><a href="<%=PathUtil.getFullPath("book/list") %>">图书列表</a></dd>
					<dd><a href="<%=PathUtil.getFullPath("book/create") %>">新增图书</a></dd>
					<dt>订单管理</dt>
					<dd><a href="<%=PathUtil.getFullPath("order/list") %>">订单列表</a></dd>
					<dt>留言管理</dt>
					<dd><a href="<%=PathUtil.getFullPath("message/listMess") %>">留言列表</a></dd>
					<dt>新闻管理</dt>
					<dd><a href="<%=PathUtil.getFullPath("news/list") %>">新闻列表</a></dd>
					<dd><a href="<%=PathUtil.getFullPath("news/create") %>">新增新闻</a></dd>
					<dt>公告管理</dt>
					<dd><a href="<%=PathUtil.getFullPath("notice/list") %>">公告列表</a></dd>
					<dd><a href="<%=PathUtil.getFullPath("notice/create") %>">新增公告</a></dd>
				</dl>
			</div>
		</div>
	    <div class="main">
			<h2>管理首页</h2>
			<div id="welcome" class="manage">
				<div class="shadow">
					<em class="corner lb"></em>
					<em class="corner rt"></em>
					<div class="box">
						<div class="msg">
							<p>欢迎回来</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
    
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
	
  </body>
</html>
