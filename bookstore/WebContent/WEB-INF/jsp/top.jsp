<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@ page import="com.lzjtu.bookstore.util.StringUtil"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>
<%@ page import="com.lzjtu.bookstore.model.User"%>

<div id="header" class="wrap">
		<div id="tool">
		<%
		User user = (User)session.getAttribute(Constants.USER);
		if(user == null) {
		%>
			<div id="welcome">
				
			    <p><a href="<%=PathUtil.getFullPath("book/index") %>">首页</a>&nbsp;&nbsp;欢迎光临淘书网，请<a href="<%=PathUtil.getFullPath("user/login") %>"><span>登录</span></a>&nbsp;<a href="<%=PathUtil.getFullPath("user/register") %>">免费注册</a></p>
			</div>
		<%} else {%>
			<div id="welcome">
			    <p><a href="<%=PathUtil.getFullPath("book/index") %>">首页</a>&nbsp;&nbsp;欢迎光临淘书网，&nbsp;<span><%=user.getUserName() %></span></p>
			</div>
		<%} %>
			<div class="help">
				<a href="<%=PathUtil.getFullPath("book/showShopCart") %>" class="shopping">购物车</a>
				<a href="<%=PathUtil.getFullPath("order/listByUserId") %>">我的订单</a>
				<a href="<%=PathUtil.getFullPath("message/list") %>">留言</a>
				<a href="<%=PathUtil.getFullPath("user/logout") %>">退出</a>
			</div>
		</div>
		<div id="errorFlashMessage">
        </div>
		<%String keyword = (String)request.getAttribute("keyword"); %>
		<div>
			<div id="logo"><img src="<%=PropertyUtil.getStaticUrl() %>/images/3.jpg" /></div>
			<input type="text" id="search" placeholder="搜索  书名、作者、出版社"  value="<%=StringUtil.htmlEncode(keyword) %>"/>
			<button type="submit" id="search_button">搜索</button>
		</div>
	</div>
	<input type="hidden" id="path" value=<%=PathUtil.getFullPath("book") %>/>
	<div id="childNav">
		<div class="wrap">
			<ul class="clearfix">
				<li class="first"><a href="#" class="category">童书</a></li>
				<li><a href="#" class="category">电子书</a></li>
				<li><a href="#" class="category">小说</a></li>
				<li><a href="#" class="category">文艺</a></li>
				<li><a href="#" class="category">青春</a></li>
				<li><a href="#" class="category">成功励志</a></li>
				<li><a href="#" class="category">生活</a></li>
				<li><a href="#" class="category">人文社科</a></li>
				<li><a href="#" class="category">经管</a></li>
				<li><a href="#" class="category">科技</a></li>
				<li><a href="#" class="category">教育</a></li>
				<li><a href="#" class="category">工具书</a></li>
				<li class="last"><a href="#" class="category">期刊</a></li>
			</ul>
		</div>
	</div>