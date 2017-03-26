<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.User"%>
<%@page import="com.lzjtu.bookstore.model.Book"%>
<%@page import="com.lzjtu.bookstore.model.ShopCart"%>
<%@page import="com.lzjtu.bookstore.model.BookOrder"%>
<%@page import="com.lzjtu.bookstore.util.StringUtil"%>
<%@page import="java.math.BigDecimal"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>购物车-淘书网</title>
	<link href="<%=PropertyUtil.getStaticUrl() %>/css/style.css" rel="stylesheet" type="text/css"></link>
    <link href="<%=PropertyUtil.getStaticUrl() %>/css/shopCart.css" rel="stylesheet" type="text/css"></link>
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
	
	<div id="errorFlashMessage">
    </div>
    <%String msg = (String)session.getAttribute("msg");
	  msg = (msg == null ? "" : msg);
      String isDisplayFlashMessage = "";
      if (msg.equals("")) {
          isDisplayFlashMessage = "style='display:none'";
      } %>
    <div id="msg" <%=isDisplayFlashMessage %>>
        <%out.write(msg);
          session.removeAttribute("msg");
        %>
    </div>
    <div class="disabled_screen" id="deleteMark" style="display:none"></div>
    <div class="pop_win" id="deleteWin" style="display:none">
        <div class="close"></div>
        <div class="prompt">您确定要删除此图书？</div>
        <div id="action">
            <div class="yes">确定</div>
            <div class="action_item">取消</div>
            <input type="hidden" id="book_id" />
        </div>
    </div>
    
	<div class="main">
		<h2><strong>购物车详情</strong></h2><br>
		<div class="context">
			<div class="header">
				<ul>
		 			<li class="id">序号</li>
		 			<li class="img">图片</li>
		 			<li class="name">产品名称</li>
		 			<li class="price">价格（元）</li>
		 			<li class="amount">数量（本）</li>
		 			<li class="total">合计（元）</li>
		 			<li class="action">操作</li>
		 		</ul>
			</div>
			<%ShopCart shopCart = (ShopCart)session.getAttribute(Constants.SHOP_CART);
			  Iterator<BookOrder> iter = shopCart.getBookOrders();
			  int i = 1;
			  while(iter.hasNext()) {
				BookOrder bookOrder = (BookOrder) iter.next();
				//float price = 0;
				BigDecimal price = new BigDecimal(0);
				if(bookOrder.getBook().getIsSpecialPrice() == 0) {
					price = new BigDecimal(Float.toString(bookOrder.getBook().getSpecialPrice()));
				} else {
					price = new BigDecimal(Float.toString(bookOrder.getBook().getPrice()));
				}
				BigDecimal sum = price.multiply(new BigDecimal(Float.toString(bookOrder.getNumber())));
				int number = bookOrder.getNumber();
				String style = "";
				if(number == 1) {
					style = "style = 'visibility:hidden'";
				} else {
					style = "style = 'visibility:visible'";
				}
			 %>
		 	<div class="book_order">
		 		<ul>
		 			<li class="id"><%=i++ %></li>
		 			<li class="img"><a href="bookDetailById?id=<%=bookOrder.getBook().getId() %>"><img id="img" src="<%=PropertyUtil.getStaticUrl() %>/books/<%=bookOrder.getBook().getPicture() %>"></img></a></li>
		 			<li class="name"><a href="bookDetailById?id=<%=bookOrder.getBook().getId() %>"><%=bookOrder.getBook().getName() %></a></li>
		 			<li class="price">
		 				<%=price %>
		 			</li>
		 			<li class="amount">
		 				<span class="decrease" <%=style %>>一</span>&nbsp;
		 				<input class="number" value="<%=number %>"/>&nbsp;
		 				<span class="add">十</span>&nbsp;
		 				<input type="hidden" class="book_id" value="<%=bookOrder.getBook().getId() %>"/>
		 			</li>
		 			<li class="total" id="total"><%=sum %></li>
		 			<li class="action">
		 				<a href="#" class="delete">删除</a>
		 				<input type="hidden" class="book_id" value="<%=bookOrder.getBook().getId() %>"/>
		 			</li>
		 		</ul>
		 	</div>
			<%} %>
			<br>
			<div id="all_total">
				<span>合计：</span>&nbsp;
				<span class="all_total"><%=shopCart.getTotalPrice() %></span>&nbsp;元&nbsp;&nbsp;
			</div>
			<div id="all_action">
				<input type="button" id="go_submit_order" value="立刻结算" /> &nbsp;&nbsp;
				<input type="button" id="go_shop" value="继续购物" /> &nbsp;&nbsp;
				<input type="button" id="clear" value="清空购物车"/>&nbsp;&nbsp;
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/footer.jsp" %>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/shopCart.js"></script>
  </body>
</html>
