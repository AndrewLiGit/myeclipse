<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.Notice"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>公告列表</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/noticeList.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/topLeft.js"></script>
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/admin/topLeft.jsp" %>
    <div id="errorFlashMessage"></div>
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
        <div class="prompt">您确定要删除此公告？</div>
        <div class="action">
            <div class="yes">确定</div>
            <div class="action_item">取消</div>
            <input type="hidden" id="notice_id" />
        </div>
    </div>
    <div class="main">
		<h2>公告列表</h2>
		<div class="manage">
			
			<div class="header">
                <ul>
                    <li class="index">序号</li>
                    <li class="id">公告ID</li>
                    <li class="title">标题</li>
                    <li class="content">内容</li>
                    <li class="modify">修改</li>
                    <li class="delete">删除</li>
                </ul>
            </div>
            <div class="context">
			<% Pagination pagination = (Pagination)request.getAttribute("pagination");
               List<Notice> list = (List<Notice>)request.getAttribute("list");
               for (int i = 0; i < list.size(); i++ ) {
             %>
                 <div>
                     <ul>
                         <li class="index"><%= pagination.getOffset() + i+1 %></li>
                         <li class="id" ><%=list.get(i).getId() %></li>
                         <li class="title" ><label title="<%=list.get(i).getTitle() %>"><a href="getById?id=<%=list.get(i).getId() %>" target="_blank"><%=list.get(i).getTitle() %></a></label></li>
                         <li class="content"><label title="<%=list.get(i).getContent() %>"><%=list.get(i).getContent() %></label></li>
                       	 <li class="modify"><a class="reply" href="modifyById?id=<%=list.get(i).getId() %>" target="_blank">修改</a></li>
                       	 <li class="delete">
                       	 	<a class="delete_notice">删除</a>
                       	 	<input type="hidden" class="notice_id" value="<%=list.get(i).getId() %>"/>
                       	 </li>
                     </ul>
                 </div>
             <% } %>
             </div>
			
		 </div>
		 
		 <div class="pagination">
		    <span class="pre_page">
		    <%if (!pagination.isFirstPage() && pagination.getPageCount() > 1) { %>
		        <a href = "<%=PathUtil.getFullPath("notice/list") %>?currentPage=<%=pagination.getCurrentPage() - 1 %>">
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
		    <a href = "<%=PathUtil.getFullPath("notice/list") %>?currentPage=<%=pagination.getCurrentPage() + 1 %>">
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
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/noticeList.js"></script>
  </body>
</html>
