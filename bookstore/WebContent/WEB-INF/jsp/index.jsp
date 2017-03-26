<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.util.StringUtil"%>
<%@ page import="com.lzjtu.bookstore.model.Book"%>
<%@ page import="com.lzjtu.bookstore.model.Notice"%>
<%@ page import="com.lzjtu.bookstore.model.News"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>
<%@page import="com.lzjtu.bookstore.model.User"%>

<!DOCTYPE HTML>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>淘书网-首页</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
  </head>
  <body>
  
    <div id="header" class="wrap">
		<div id="tool">
		<%
		User user = (User)session.getAttribute(Constants.USER);
		if(user == null) {
		%>
			<div id="welcome">
				
			    <p><a href="index">首页</a>&nbsp;&nbsp;欢迎光临淘书网，请<a href="<%=PathUtil.getFullPath("user/login") %>"><span>登录</span></a>&nbsp;<a href="<%=PathUtil.getFullPath("user/register") %>">免费注册</a></p>
			</div>
		<%} else {%>
			<div id="welcome">
			    <p><a href="index">首页</a>&nbsp;&nbsp;欢迎光临淘书网，&nbsp;<span><%=user.getUserName() %></span></p>
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
	
	<div id="main" class="wrap">
		<div class="lefter">
			<div class="box">
				<h2>图书分类</h2>
				<!--自定义列表以 <dl> 标签开始。每个自定义列表项以 <dt> 开始。每个自定义列表项的定义以 <dd> 开始-->
				<dl>
					<dd><a href="#" class="category">童书</a></dd>
					<dd><a href="#" class="category">电子书</a></dd>
					<dd><a href="#" class="category">小说</a></dd>
					<dd><a href="#" class="category">文艺</a></dd>
					<dd><a href="#" class="category">青春</a></dd>
					<dd><a href="#" class="category">成功励志</a></dd>
					<dd><a href="#" class="category">生活</a></dd>
					<dd><a href="#" class="category">人文社科</a></dd>
					<dd><a href="#" class="category">经管</a></dd>
					<dd><a href="#" class="category">科技</a></dd>
					<dd><a href="#" class="category">教育</a></dd>
					<dd><a href="#" class="category">工具书</a></dd>
					<dd><a href="#" class="category">期刊</a></dd>
				</dl>
			</div>
		</div>
		
		<div class="main">
		  <div class="price-off">
			<h2>今日特价</h2>
			<ul class="product clearfix">
			<%
				List<Book> specialPriceList = (ArrayList<Book>)request.getAttribute("specialList");
				for(int i = 0; i < specialPriceList.size(); i ++) {
			 %>
				<li>
					<dl>
						<dt>
							<a href="bookDetailById?id=<%=specialPriceList.get(i).getId() %>" class="book_detail" target="_blank">
								<img src="<%=PropertyUtil.getStaticUrl() %>/books/<%=specialPriceList.get(i).getPicture() %>" />
							</a>
						</dt>
						<dd class="title"><a href="bookDetailById?id=<%=specialPriceList.get(i).getId() %>" class="book_detail" target="_blank"><%=specialPriceList.get(i).getName() %></a></dd>
						<dd class="price">￥<%=specialPriceList.get(i).getSpecialPrice() %></dd>
					</dl>
				</li>
			<%} %>
			</ul>
		</div>
		
		<div class="side">
			<div class="news-list">
				<h4>最新公告</h4>
				<ul>
					<marquee scrollamount="2" scrollDelay="10"  onmouseover="this.stop()" onmouseout="this.start()"  direction="up">
						<%
						List<Notice> noticeList = (ArrayList<Notice>)request.getAttribute("noticeList");
						for(int i = 0; i < noticeList.size(); i ++) {
					 	%>
					 	
							<li>
								<a href="<%=PathUtil.getFullPath("notice/getNoticeById") %>?id=<%=noticeList.get(i).getId() %>" target="_blank"><%=noticeList.get(i).getTitle() %></a>
								<input type="hidden" id="notice_id" value="<%=noticeList.get(i).getId() %>"/>
							</li>
						<%} %>
					</marquee>
				</ul>
			</div>
			<div class="spacer"></div>
			<div class="news-list">
				<h4>新闻动态</h4>
				<ul>
					<marquee scrollamount="2" scrollDelay="10"  onmouseover="this.stop()" onmouseout="this.start()"  direction="up" >
					<%
					List<News> newsList = (ArrayList<News>)request.getAttribute("newsList");
					for(int i = 0; i < newsList.size(); i ++) {
				 	%>
						<li>
							<a href="<%=PathUtil.getFullPath("news/getNewsById") %>?id=<%=newsList.get(i).getId() %>" target="_blank"><%=newsList.get(i).getTitle() %></a>
							<input type="hidden" id="news_id" value="<%=newsList.get(i).getId() %>"/>
						</li>
					<%} %>
					</marquee>
				</ul>
			</div>
		</div>
		<div class="spacer clear"></div>
		<div class="hot">
			<h2>热卖推荐</h2>
			<ul class="product clearfix">
			<%
				List<Book> hotList = (ArrayList<Book>)request.getAttribute("hotList");
				for(int i = 0; i < hotList.size(); i ++) {
			 %>
				<li>
					<dl>
						<dt>
							<a href="bookDetailById?id=<%=hotList.get(i).getId() %>" class="book_detail" target="_blank">
								<img src="<%=PropertyUtil.getStaticUrl() %>/books/<%=hotList.get(i).getPicture() %>" />
							</a>
						</dt>
						<dd class="title">
							<a href="bookDetailById?id=<%=hotList.get(i).getId() %>" class="book_detail" target="_blank"><%=hotList.get(i).getName() %></a>
						</dd>
						<%if(hotList.get(i).getSpecialPrice() == 0) {%>
							<dd class="price">￥<%=hotList.get(i).getPrice() %></dd>
						<%} else { %>
							<dd class="price">￥<%=hotList.get(i).getSpecialPrice() %></dd>
						<%} %>
					</dl>
				</li>
				<%} %>
			</ul>
		</div>
	  </div>
	  <div class="clear"></div>
	</div>
	<%@include file="/WEB-INF/jsp/footer.jsp" %>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/index.js"></script>
  </body>
</html>
