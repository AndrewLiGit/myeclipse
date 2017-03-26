<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.User"%>
<%@page import="com.lzjtu.bookstore.model.Book"%>
<%@page import="com.lzjtu.bookstore.model.BookOrder"%>
<%@page import="com.lzjtu.bookstore.model.ShopCart"%>
<%@page import="com.lzjtu.bookstore.model.PayWay"%>
<%@page import="com.lzjtu.bookstore.util.StringUtil"%>
<%@page import="com.lzjtu.bookstore.model.ContactInfo"%>
<%@page import="java.math.BigDecimal"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>确认订单-淘书网</title>
	<link href="<%=PropertyUtil.getStaticUrl() %>/css/style.css" rel="stylesheet" type="text/css"></link>
    <link href="<%=PropertyUtil.getStaticUrl() %>/css/confirmOrder.css" rel="stylesheet" type="text/css"></link>
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
    <div class="main">
    	<h2><strong>收货人基本信息</strong></h2><br>
		<div class="user">
			<div class="user_information_header">
				<ul>
		 			<li class="user_name">收货人</li>
		 			<li class="address">收货人详细地址</li>
		 			<li class="mobile">收货人联系方式</li>
		 			<li class="email">EMail</li>
		 			<!-- <li class="action">操作</li> -->
		 		</ul>
			</div>
			<%ContactInfo contactInfo = (ContactInfo)request.getAttribute("contactInfo");
			 %>
		 	<div class="user_information">
		 		<ul>
		 			<li class="user_name"><%=contactInfo.getUserName() %></li>
		 			<li class="address"><label id="address" title="<%=contactInfo.getAddress() %>"><%=contactInfo.getAddress() %></label></li>
		 			<li class="mobile"><%=contactInfo.getMobile() %></li>
		 			<li class="email"><%=contactInfo.getEmail() %></li>
		 			<%-- <li class="action">
		 				<a id="update_information" href="deleteBookOrder?id=<%=contactInfo.getId() %>">修改基本信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
		 			</li> --%>
		 		</ul>
		 	</div>
		</div>
		<br><br>
    	<div class="spacer clear"></div>
    	
    	<h2><strong>确认订单信息</strong></h2><br>
		<div class="book_order">
			<div class="header">
				<ul>
					<li class="id">序号</li>
					<li class="img">图片</li>
		 			<li class="name">产品名称</li>
		 			<li class="price">价格（元）</li>
		 			<li class="amount">数量（本）</li>
		 			<li class="total">合计（元）</li>
		 		</ul>
			</div>
			<%
			  List<PayWay> payWayList = (ArrayList)request.getAttribute("payWayList");
			  ShopCart shopCart = (ShopCart)session.getAttribute(Constants.SHOP_CART);
			  Iterator<BookOrder> iter = shopCart.getBookOrders();
			  int i = 1;
			  while(iter.hasNext()) {
				BookOrder bookOrder = (BookOrder) iter.next();
				BigDecimal price = new BigDecimal(0);
				if(bookOrder.getBook().getIsSpecialPrice() == 0) {
					price = new BigDecimal(Float.toString(bookOrder.getBook().getSpecialPrice()));
				} else {
					price = new BigDecimal(Float.toString(bookOrder.getBook().getPrice()));
				}
				BigDecimal sum = price.multiply(new BigDecimal(Float.toString(bookOrder.getNumber())));
			 %>
		 	<div class="book_order_information">
		 		<ul>
		 			<li class="id"><%=i++ %></li>
		 			<li class="img"><a href="bookDetailById?id=<%=bookOrder.getBook().getId() %>"><img id="img" src="<%=PropertyUtil.getStaticUrl() %>/books/<%=bookOrder.getBook().getPicture() %>"></img></a></li>
		 			<li class="name"><a href="bookDetailById?id=<%=bookOrder.getBook().getId() %>"><%=bookOrder.getBook().getName() %></a></li>
		 			<li class="price">
		 				<%=price %>
		 			</li>
		 			<li class="amount">
		 				<input class="number" readonly="readonly" value="<%=bookOrder.getNumber() %>"/>&nbsp;
		 			</li>
		 			<li class="total" id="total"><%=sum %></li>
		 		</ul>
		 	</div>
			<%} %>
			<br>
			<div id="all_total">
				<span>实付款：</span>&nbsp;
				<span class="all_total"><%=shopCart.getTotalPrice() %></span>&nbsp;元&nbsp;&nbsp;
			</div>
			<form action="<%=PathUtil.getFullPath("order/save")%>" id="order_form" method="post">
				<div id="pay_way">
					<span>付款方式：</span>&nbsp;
					<select name="payStyleId">
						<%for(int j = 0; j < payWayList.size(); j ++) { %>
		                  	<option value="<%=payWayList.get(j).getId() %>"><%=payWayList.get(j).getPayStyle() %></option>
		                <%} %>
	                </select>
				</div>
				<div id="submit">
					<input type="button" id="submit_order" value="提交订单" />
				</div>
			</form>
		</div>
    </div>
    
    
    
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/confirmOrder.js"></script>
  </body>
</html>
