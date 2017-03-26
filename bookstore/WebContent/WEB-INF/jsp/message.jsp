<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.model.Message"%>
<%@ page import="com.lzjtu.bookstore.model.Pagination"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>留言-淘书网</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/top.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/message.css">
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/top.jsp" %>
    
      <div class="disabled_screen" id="deleteMark" style="display:none">
      </div>
      <div class="pop_win" id="deleteWin" style="display:none">
          <div class="close"></div>
          <div class="prompt">请留言：</div>
          <textarea class="i_message" ></textarea>
          <div class="action">
              <div class="yes">留言</div>
              <div class="action_item">取消</div>
          </div>
      </div>
      <input id="text" type="hidden"/>
     <div class="main">
	     <br>
	     <h2>
	     	<strong>留言板</strong>
	     	<label class="mess">我要留言</label>
	     </h2>
	     <div class="header">
	     		<ul>
	     		  <li class="no">序号</li>
	     		  <li class="content">留言内容</li>
	     		  <li class="user_name">昵称</li>
	     		  <li class="create_time">留言时间</li>
	     		</ul>
	     	</div>
	     <%
	     	List<Message> list = (List<Message>)request.getAttribute("list");
	     	Pagination pagination = (Pagination)request.getAttribute("pagination");
	     	for(int i = 0; i < list.size(); i ++) {
	      %>
	     	<div class="message">
	     		<ul>
	     		  <li class="no"><%=i + 1 %></li>
	     		  <li class="content"><label title="<%=list.get(i).getContent() %>"><%=list.get(i).getContent() %></label></li>
	     		  <li class="user_name"><%=list.get(i).getUserName() %></li>
	     		  <li class="create_time"><%=StringUtil.convertDate(list.get(i).getCreateTime()) %></li>
	     		</ul>
	     	</div>
	     <%} %>
    </div>
    
    <div class="pagination">
	    <span class="pre_page">
	    <%if (!pagination.isFirstPage() && pagination.getPageCount() > 1) { %>
	        <a href = "<%=PathUtil.getFullPath("message/list") %>?currentPage=<%=pagination.getCurrentPage() - 1 %>">
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
	    <a href = "<%=PathUtil.getFullPath("message/list") %>?currentPage=<%=pagination.getCurrentPage() + 1 %>">
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
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/top.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/message.js"></script>
  </body>
</html>
