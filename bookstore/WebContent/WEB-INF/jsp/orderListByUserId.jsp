<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.User"%>
<%@page import="com.lzjtu.bookstore.model.Book"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.Order"%>
<%@page import="com.lzjtu.bookstore.util.StringUtil"%>
<%@page import="java.math.BigDecimal"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订单列表-淘书网</title>
	<link href="<%=PropertyUtil.getStaticUrl() %>/css/style.css" rel="stylesheet" type="text/css"></link>
	<link href="<%=PropertyUtil.getStaticUrl() %>/css/top.css" rel="stylesheet" type="text/css"></link>
    <link href="<%=PropertyUtil.getStaticUrl() %>/css/orderListByUserId.css" rel="stylesheet" type="text/css"></link>
    <link href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css" rel="stylesheet" type="text/css"></link>
  </head>
  
  <body>
	<%@include file="/WEB-INF/jsp/top.jsp" %>
	<br>
	<div id="errorFlashMessage">
    </div>
	<div class="main">
		<h2><strong>订单列表</strong></h2><br>
		<div class="context">
			<div class="header">
				<ul>
		 			<li class="index">序号</li>
                    <li class="order_no">订单号</li>
                    <li class="cost">总价(元)</li>
                    <li class="pay_style">付款方式</li>
                    <li class="order_status">订单状态</li>
                    <li class="create_time">订单时间</li>
		 		</ul>
			</div>
			<% Pagination pagination = (Pagination)request.getAttribute("pagination");
               ArrayList<Order> list = (ArrayList<Order>)request.getAttribute("List");
               for (int i = 0; i < list.size(); i++ ) {
             %>
		 	<div class="book_order">
		 		<ul>
		 			<li class="index"><%= pagination.getOffset() + i+1 %></li>
                     <li class="order_no" >
                         <a href="<%=PathUtil.getFullPath("order/getOrderBookDetail?orderId=") %><%=list.get(i).getId() %>" target="_blank"><%=list.get(i).getOrderNo()%></a>
                     </li>
                     <li class="cost"><%=list.get(i).getCost() %></li>
                     <%if(list.get(i).getPayStyleId() == 1) { %>
                     	<li class="pay_style">货到付款</li>
                     <%} else if(list.get(i).getPayStyleId() == 2) { %>
                     	<li class="pay_style">银行卡支付</li>
                     <%} else if(list.get(i).getPayStyleId() == 3) { %>
                     	<li class="pay_style">支付宝支付</li>
                     <%}
                       if(list.get(i).getOrderStatus() == 1) {
                      %>
                	 	<li class="order_status">待审核</li>
                	 <%} else if(list.get(i).getOrderStatus() == 2) { %>
                	 	<li class="order_status">卖家发货</li>
                	 <%} else if(list.get(i).getOrderStatus() == 3) { %>
                	 	<li class="order_status">卖家收货</li>
                	 <%} %>
                   	 <li class="create_time"><%=StringUtil.convertDate(list.get(i).getCreateTime()) %></li>
		 		</ul>
		 	</div>
			<%} %>
			<br>
			
			<div class="pagination">
			    <span class="pre_page">
			    <%if (!pagination.isFirstPage() && pagination.getPageCount() > 1) { %>
			        <a href = "<%=PathUtil.getFullPath("order/listByUserId") %>?currentPage=<%=pagination.getCurrentPage() - 1 %>">
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
			    <a href = "<%=PathUtil.getFullPath("order/listByUserId") %>?currentPage=<%=pagination.getCurrentPage() + 1 %>">
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
			
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/footer.jsp" %>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/orderListByUserId.js"></script>
  </body>
</html>
