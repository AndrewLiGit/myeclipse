<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.ContactInfo"%>
<%@page import="com.lzjtu.bookstore.model.User"%>
<%@page import="com.lzjtu.bookstore.model.BookOrder"%>
<%@page import="com.lzjtu.bookstore.model.Book"%>
<%@page import="com.lzjtu.bookstore.model.Order"%>
<%@page import="java.math.BigDecimal"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>订单详情</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/orderDetail.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/topLeft.js"></script>
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/admin/topLeft.jsp" %>
    <%
    	Order order = (Order)request.getAttribute("order");
     %>
    <div class="main">
		<h2>订单详情</h2>
		<br>
		<div id="order">
            <ul>
                <li class="id" >订单ID：<%=order.getId() %></li>
                <li class="order_no" >订单号：<%=order.getOrderNo()%></li>
                <li class="cost">订单总价：￥<%=order.getCost() %></li>
              	<li class="create_time">订单时间：<%=StringUtil.convertDate(order.getCreateTime()) %></li>
            </ul>
        </div>
		    
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
			  List<BookOrder> list = (ArrayList<BookOrder>)request.getAttribute("List");
			  Map<Integer, Book> map = (HashMap<Integer, Book>)request.getAttribute("map");
			  Iterator<BookOrder> iter = list.iterator();
			  Set<Integer> key = map.keySet();
			  /* Iterator<Book> iterBook = map.values().iterator(); */
			  int i = 1;
			  while(iter.hasNext()) {
				BookOrder bookOrder = (BookOrder) iter.next();
				for(Integer k:key) {
					if(bookOrder.getBookId() == k){
						BigDecimal price = new BigDecimal(0);
						if(map.get(k).getIsSpecialPrice() == 0) {
							price = new BigDecimal(Float.toString(map.get(k).getSpecialPrice()));
						} else {
							price = new BigDecimal(Float.toString(map.get(k).getPrice()));
						}
						BigDecimal sum = price.multiply(new BigDecimal(Float.toString(bookOrder.getNumber())));
						 %>
					 	<div class="book_order_information">
					 		<ul>
					 			<li class="id"><%=i++ %></li>
					 			<li class="img"><img id="img" src="<%=PropertyUtil.getStaticUrl() %>/books/<%=map.get(k).getPicture() %>"></img></li>
					 			<li class="name"><%=map.get(k).getName() %></li>
					 			<li class="price">
					 				<%=price %>
					 			</li>
					 			<li class="amount">
					 				<input class="number" readonly="readonly" value="<%=bookOrder.getNumber() %>"/>&nbsp;
					 			</li>
					 			<li class="total" id="total"><%=sum %></li>
					 		</ul>
					 	</div>
						<%
					}
				}
			} %>
	   </div>
	   <br>
	  </div>
    
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
  </body>
</html>
