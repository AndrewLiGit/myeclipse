<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.Book"%>
<%@page import="com.lzjtu.bookstore.model.BigCategory"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>编辑图书</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/bookModify.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/topLeft.js"></script>
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/admin/topLeft.jsp" %>
    
    <%
    	Book book = (Book)request.getAttribute("book");
    	List<BigCategory> list = (List<BigCategory>)request.getAttribute("list");
     %>
    <div class="main">
		<h2>编辑图书</h2>
		<form action="<%=PathUtil.getFullPath("book/save")%>" id="modify_form" method="post">
			<div class="manage">
				<input type="hidden" name="id" value="<%=book.getId() %>"/>
				<div class="name">
					<label>图书名称：</label>
					<input id="name" name="name" value="<%=StringUtil.htmlEncode(book.getName()) %>"/>
				</div>
				<div class="price">
					<label>图书原价：</label>
					<input id="price" name="price" value="<%=book.getPrice() == 0.0 ? "" : book.getPrice()%>"/>&nbsp;&nbsp;<span>元</span>
				</div>
				<div class="hot">
					<label>是否热销：</label>
					<input type="radio" name="hot" class="hot_radio" value="0"/>&nbsp;&nbsp;<b>是</b>&nbsp;&nbsp;&nbsp;&nbsp;
    				<input type="radio" name="hot" class="hot_radio" checked="checked" value="1"/>&nbsp;<b>否</b>
				</div>
				<div class="picture">
					<label>图片上传：</label>
					<input type="text" name="picture" id="picture" value="<%= book.getPicture() == null ? "" : book.getPicture()%>"/>
    				<input type="file" id="picture_file" size="40" value=""/>
				</div>
				<div class="is_special_price">
					<label>是否特价：</label>
    				<input type="radio" name="isSpecialPrice" class="isSpecialPrice_radio" value="0"/>&nbsp;&nbsp;<b>是</b>&nbsp;&nbsp;&nbsp;&nbsp;
    				<input type="radio" name="isSpecialPrice" class="isSpecialPrice_radio" checked="checked" value="1"/>&nbsp;<b>否</b>
				</div>
				<div class="special_price">
					<label>图书特价：</label>
					<input id="special_price" name="specialPrice" value="<%=book.getSpecialPrice() %>"/>&nbsp;&nbsp;<span>元</span>
				</div>
				<div class="amount">
					<label>图书数量：</label>
					<input id="amount" name="amount" value="<%=book.getAmount() == 0 ? "" : book.getAmount() %>"/>&nbsp;&nbsp;<span>本</span>
				</div>
				<%if(book.getId() != 0) { %>
				<div class="selled">
					<label>已销售量：</label>
					<input id="selled" name="selled" value="<%=book.getSelled() == 0 ? "" : book.getSelled() %>"/>&nbsp;&nbsp;<span>本</span>
				</div>
				<%} %>
				<div class="writer">
					<label>图书作者：</label>
					<input id="writer" name="writer" value="<%=book.getWriter() == null ? "" : book.getWriter() %>"/>
				</div>
				<div class="publish">
					<label>&nbsp;&nbsp;&nbsp;出版社：</label>
					<input id="publish" name="publish" value="<%=book.getPublish() == null ? "" : book.getPublish() %>"/>
				</div>
				<div class="page">
					<label>图书页数：</label>
					<input id="page" name="page" value="<%=book.getPage() == 0 ? "" : book.getPage() %>"/>&nbsp;&nbsp;<span>页</span>
				</div>
				<div class="category">
					<label>图书分类：</label>
					<select name="bigCategoryId" id="category">
						<option value="0">--请选择--</option>
						<%for(int i = 0; i < list.size(); i ++) { %>
                       		<option value="<%=list.get(i).getId() %>"><%=list.get(i).getName() %></option>
                        <%} %>
                     </select>
				</div>
				<div class="description">
					<label>图书描述：</label>
					<textarea id="description" name="description" placeholder="请在此输入对图书的描述" ><%=StringUtil.htmlEncode(book.getDescription()) %></textarea>
				</div>
			 </div>
		 </form>
	</div>
	<br>
    <div id="action">
    	<div id="modify">保存</div>
    	<div id="cancel"onclick="javascript: location.href=document.referrer;">取消</div>
    </div>
    <br>
    <input type="hidden" id="category_right" value="<%=book.getBigCategoryId() %>"/>
    <input type="hidden" id="is_special_price_right" value="<%=book.getIsSpecialPrice() %>"/>
    <input type="hidden" id="hot_right" value="<%=book.getHot() %>"/>
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/bookModify.js"></script>
  </body>
</html>
