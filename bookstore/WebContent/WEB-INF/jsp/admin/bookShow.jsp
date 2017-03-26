<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.Book"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>图书详情</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/bookShow.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/topLeft.js"></script>
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/admin/topLeft.jsp" %>
    
    <div class="disabled_screen" id="deleteMark" style="display:none"></div>
    <div class="pop_win" id="deleteWin" style="display:none">
        <div class="close"></div>
        <div class="prompt">您确定要删除此图书？</div>
        <div class="action">
            <div class="yes">确定</div>
            <div class="action_item">取消</div>
        </div>
    </div>
    
    <%
    	Book book = (Book)request.getAttribute("book");
     %>
    <div class="main">
		<h2>图书详情</h2>
		<div class="infos">
			<div class="picture"><img src="<%=PropertyUtil.getStaticUrl() %>/books/<%=book.getPicture() %>" /></div>
			<div class="buy">
				<div class="name">
					<label class="book_name" title="<%=book.getName() %>"><%=book.getName() %></label>
				</div>
				
				<br>
				<%if(book.getIsSpecialPrice() == 0) { %>
					<p>&nbsp;原&nbsp;价：&nbsp;<span class="price"><%=book.getPrice() %></span>&nbsp;元</p>
					<p>&nbsp;促销价：&nbsp;<span class="special_price"><%=book.getSpecialPrice() %></span>&nbsp;元</p>
				<%} else {%>
					<p>&nbsp;原&nbsp;价：&nbsp;<span class="special_price"><%=book.getPrice() %></span>&nbsp;元</p>
				<%} %>
				<p>&nbsp;库&nbsp;&nbsp;存：&nbsp;<%=book.getAmount() %>&nbsp;本</p>
				<p>&nbsp;已&nbsp;&nbsp;售：&nbsp;<%=book.getSelled() %>&nbsp;本</p>
				<p>&nbsp;作&nbsp;&nbsp;者：&nbsp;<%=book.getWriter() %>&nbsp;</p>
				<p>&nbsp;出版社：&nbsp;<%=book.getPublish() %>&nbsp;</p>
				<p>&nbsp;页&nbsp;&nbsp;数：&nbsp;<%=book.getPage() %>&nbsp;页</p>
				<p>&nbsp;创建时间：&nbsp;<%=StringUtil.convertDate(book.getCreateTime()) %>&nbsp;</p>
				<p>&nbsp;最后一次更改时间：&nbsp;<%=StringUtil.convertDate(book.getLastUpdateTime()) %>&nbsp;</p>
				<p>&nbsp;分&nbsp;&nbsp;类：&nbsp;<%=book.getPage() %>&nbsp;类</p>
				<%if(book.getHot() == 0) { %>
					<p>&nbsp;是否热销：&nbsp;是&nbsp;</p>
				<%} else { %>
					<p>&nbsp;是否热销：&nbsp;否&nbsp;</p>
				<%} %>
			</div>
		</div><br>
		<div class="text">
			<label>描&nbsp;&nbsp;述:&nbsp;</label>
			<span><%=book.getDescription() %></span>
		</div>
	</div>
	<br>
    <div id="action">
    	<input type="hidden" id="book_id" value="<%=book.getId() %>"/>
    	<div id="modify">修改</div>
    	<div id="delete">删除</div>
    </div>
    <br>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/bookShow.js"></script>
  </body>
</html>
