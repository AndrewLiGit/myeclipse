<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.User"%>
<%@page import="com.lzjtu.bookstore.model.Book"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.util.StringUtil"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>搜索-淘书网</title>
	<link href="<%=PropertyUtil.getStaticUrl() %>/css/style.css" rel="stylesheet" type="text/css"></link>
    <link href="<%=PropertyUtil.getStaticUrl() %>/css/searchBooks.css" rel="stylesheet" type="text/css"></link>
    <link href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css" rel="stylesheet" type="text/css"></link>
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
			<input type="text" id="search" placeholder="搜索  书名、作者、出版社" value="<%=StringUtil.htmlEncode(keyword) %>"/>
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
	<br>
	<div class="main">
		<ul class="product clearfix">
		<%
			Pagination pagination = (Pagination)request.getAttribute("pagination");
			List<Book> list = (ArrayList<Book>)request.getAttribute("List");
			for(int i = 0; i < list.size(); i ++) {
		 %>
			<li>
				<dl>
					<dt>
						<a href="bookDetailById?id=<%=list.get(i).getId() %>" class="book_detail" target="_blank" >
							<img src="<%=PropertyUtil.getStaticUrl() %>/books/<%=list.get(i).getPicture() %>" />
						</a>
					</dt>
					<dd class="title">
						<a href="bookDetailById?id=<%=list.get(i).getId() %>" class="book_detail" target="_blank"><%=list.get(i).getName() %></a>
					</dd>
					<%if(list.get(i).getSpecialPrice() == 0) {%>
						<dd class="price">￥<%=list.get(i).getPrice() %></dd>
					<%} else { %>
						<dd class="price">￥<%=list.get(i).getSpecialPrice() %></dd>
					<%} %>
				</dl>
			</li>
		<%} %>
		</ul>
	</div>
	<div class="pagination">
	    <span class="pre_page">
	    <%if (!pagination.isFirstPage() && pagination.getPageCount() > 1) { %>
	        <a href = "<%=PathUtil.getFullPath("book/searchCategory") %>?currentPage=<%=pagination.getCurrentPage() - 1 %>&&category=<%=StringUtil.htmlEncode(keyword) %>">
	            <img style="cursor: pointer" src="<%=PropertyUtil.getStaticUrl() %>/images/BTN_PageLeft_20x15.png"></img>
	        </a>
	    <%} else { %>
	        <img src="<%=PropertyUtil.getStaticUrl() %>/images/BTN_PageLeft_20x15.png"></img>
	    <%} %>
	    </span>&nbsp;
	    <span class="current_page_no">第</span>&nbsp;
	    <span class="current_page"><%=pagination.getCurrentPage() %></span>&nbsp;
	    <span class="current_page_no">页</span>&nbsp;
	    <span class="next_page">
	    <%if (!pagination.isLastPage() && pagination.getPageCount() > 1) {%>
	    <a href = "<%=PathUtil.getFullPath("book/searchCategory") %>?currentPage=<%=pagination.getCurrentPage() + 1 %>&&category=<%=StringUtil.htmlEncode(keyword) %>">
	        <img style="cursor: pointer" src="<%=PropertyUtil.getStaticUrl() %>/images/BTN_PageRight_20x15.png .png"></img>
	    </a>
	    <%} else { %>
	        <img src="<%=PropertyUtil.getStaticUrl() %>/images/BTN_PageRight_20x15.png .png"></img>
	    <%} %>
	    </span>&nbsp;
	    <span class="total_page">共</span>&nbsp;
	    <span class="total_page" id="total_pages"><%=pagination.getPageCount() %></span>&nbsp;
	    <span class="total_page">页</span>&nbsp;&nbsp;
	    <span class="go_desc">跳转到第</span>
	    <input type="text" class="go_input" id="currentPage" name="currentPage" value="<%=pagination.getCurrentPage() %>"/>
	    <span class="total_page">页</span>&nbsp;
	    <!-- <div class="go_button"> --><img class="go_button" src="<%=PropertyUtil.getStaticUrl() %>/images/BTN_Go_20x15.png  .png"></img><!-- </div> -->
	</div>
	<%@include file="/WEB-INF/jsp/footer.jsp" %>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/searchByCategory.js"></script>
  </body>
</html>
