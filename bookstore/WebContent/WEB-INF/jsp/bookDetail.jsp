<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.User"%>
<%@page import="com.lzjtu.bookstore.model.Book"%>
<%@page import="com.lzjtu.bookstore.util.StringUtil"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书详情-淘书网</title>
	<link href="<%=PropertyUtil.getStaticUrl() %>/css/style.css" rel="stylesheet" type="text/css"></link>
    <link href="<%=PropertyUtil.getStaticUrl() %>/css/bookDetail.css" rel="stylesheet" type="text/css"></link>
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
	<%String msg = (String)request.getAttribute("msg");
	  msg = (msg == null ? "" : msg);
      String isDisplayFlashMessage = "";
      if (msg.equals("")) {
          isDisplayFlashMessage = "style='display:none'";
      } %>
    <div id="msg" <%=isDisplayFlashMessage %>>
        <%out.write(msg);
          request.removeAttribute("msg");
        %>
    </div>
	<div class="main">
		<%Book book = (Book)request.getAttribute("book"); %>
		<div class="infos">
			<div class="picture"><img src="<%=PropertyUtil.getStaticUrl() %>/books/<%=book.getPicture() %>" /></div>
			<div class="buy">
				<div class="name">
					<label class="book_name"><%=book.getName() %></label>
				</div>
				
				<br>
				<%if(book.getIsSpecialPrice() == 0) { %>
					<p>&nbsp;原&nbsp;价：&nbsp;<span class="price"><%=book.getPrice() %></span>&nbsp;元</p><br>
					<p>&nbsp;促销价：&nbsp;<span class="special_price"><%=book.getSpecialPrice() %></span>&nbsp;元</p><br>
				<%} else {%>
					<p>&nbsp;原&nbsp;价：&nbsp;<span class="special_price"><%=book.getPrice() %></span>&nbsp;元</p><br>
				<%} %>
				<p>&nbsp;库&nbsp;存：&nbsp;<%=book.getAmount() %>&nbsp;本</p><br>
				<p>&nbsp;已&nbsp;售：&nbsp;<%=book.getSelled() %>&nbsp;本</p><br>
				<div class="button">
					<div id="shopping">
						<a href="nowBuy?id=<%=book.getId() %>">立即购买</a>
					</div>
					<div id="shop_cart">
						<a href="addBookToShopCart?id=<%=book.getId() %>" rel="nofollow">加入购物车</a>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<br>
		<div class="introduce">
			<h2><strong>商品详情</strong></h2>
			<div class="text">
				<ul>
					<li>
						<label>书&nbsp;&nbsp;名:&nbsp;</label>
						<span><%=book.getName() %></span>
					</li>
					<li>
						<label>定&nbsp;&nbsp;价:&nbsp;</label>
						<span><%=book.getPrice() %>&nbsp;元</span>
					</li>
					<li>
						<label>库&nbsp;&nbsp;存:&nbsp;</label>
						<span><%=book.getAmount() %>&nbsp;本</span>
					</li>
					<li>
						<label>已&nbsp;&nbsp;售:&nbsp;</label>
						<span><%=book.getSelled() %>&nbsp;本</span>
					</li>
					<li>
						<label>作&nbsp;&nbsp;者:&nbsp;</label>
						<span><%=book.getWriter() %></span>
					</li>
					<li>
						<label>出版社:&nbsp;</label>
						<span><%=book.getPublish() %></span>
					</li>
					<li>
						<label>页&nbsp;&nbsp;数:&nbsp;</label>
						<span><%=book.getPage() %>&nbsp;页</span>
					</li>
					<li>
						<label>描&nbsp;&nbsp;述:&nbsp;</label>
						<span><%=book.getDescription() %></span>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	
	<%@include file="/WEB-INF/jsp/footer.jsp" %>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/bookDetail.js"></script>
  </body>
</html>
