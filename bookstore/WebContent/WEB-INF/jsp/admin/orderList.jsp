<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.Order"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>订单列表</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/orderList.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/topLeft.js"></script>
    

  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/admin/topLeft.jsp" %>
    <div id="errorFlashMessage"></div>
    <div class="main">
		<h2>订单列表</h2>
		<div class="manage">
			
			<div class="header">
                <ul>
                    <li class="index">序号</li>
                    <li class="id">订单ID</li>
                    <li class="order_no">订单号</li>
                    <li class="cost">总价(元)</li>
                    <li class="create_time">订单时间</li>
                </ul>
            </div>
            <div class="context">
			<% Pagination pagination = (Pagination)request.getAttribute("pagination");
               ArrayList<Order> list = (ArrayList<Order>)request.getAttribute("List");
               for (int i = 0; i < list.size(); i++ ) {
             %>
                 <div>
                     <ul>
                         <li class="index"><%= pagination.getOffset() + i+1 %></li>
                         <li class="id" ><%=list.get(i).getId() %></li>
                         <li class="order_no" >
                             <a href="<%=PathUtil.getFullPath("order/getOrderDetail?orderId=") %><%=list.get(i).getId() %>" target="_blank"><%=list.get(i).getOrderNo()%></a>
                         </li>
                         <li class="cost"><%=list.get(i).getCost() %></li>
                       	<li class="create_time"><%=StringUtil.convertDate(list.get(i).getCreateTime()) %></li>
                     </ul>
                 </div>
             <% } %>
             </div>
			
		 </div>
		 
		 <div class="pagination">
		    <span class="pre_page">
		    <%if (!pagination.isFirstPage() && pagination.getPageCount() > 1) { %>
		        <a href = "<%=PathUtil.getFullPath("order/list") %>?currentPage=<%=pagination.getCurrentPage() - 1 %>">
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
		    <a href = "<%=PathUtil.getFullPath("order/list") %>?currentPage=<%=pagination.getCurrentPage() + 1 %>">
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
    
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/orderList.js"></script>
  </body>
</html>
