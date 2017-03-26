<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.Message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>留言列表</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/messageList.css">
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
        <div class="prompt">请回复：</div>
        <textarea class="i_message" ></textarea>
        <div class="action">
            <div class="yes" id="reply_message">回复</div>
            <div class="action_item">取消</div>
        </div>
    </div>
    <div class="pop_win" id="deleteMessWin" style="display:none">
        <div class="close"></div>
        <div class="prompt_Mess">您确定要删除此留言？</div>
        <div class="action">
            <div class="yes" id="delete_message">确定</div>
            <div class="action_item">取消</div>
            <input type="hidden" id="message_id" />
        </div>
    </div>
    <div class="main">
		<h2>留言列表</h2>
		<div class="manage">
			
			<div class="header">
                <ul>
                    <li class="index">序号</li>
                    <li class="content">留言内容</li>
                    <li class="user_name">昵称</li>
                    <li class="create_time">留言时间</li>
                    <li class="write_back">回复</li>
                    <li class="delete">删除</li>
                </ul>
            </div>
            <div class="context">
			<% Pagination pagination = (Pagination)request.getAttribute("pagination");
               List<Message> list = (List<Message>)request.getAttribute("list");
               for (int i = 0; i < list.size(); i++ ) {
             %>
                 <div>
                     <ul>
                         <li class="index"><%= pagination.getOffset() + i+1 %></li>
                         <li class="content" ><label title="<%=list.get(i).getContent() %>"><%=list.get(i).getContent() %></label></li>
                         <li class="user_name" ><%=list.get(i).getUserName() %></li>
                         <li class="create_time"><%=StringUtil.convertDate(list.get(i).getCreateTime()) %></li>
                       	 <li class="write_back"><a class="reply">回复</a></li>
                       	 <li class="delete">
                       	 	<a class="delete_mess">删除</a>
                       	 	<input type="hidden" class="mess_id" value="<%=list.get(i).getId() %>"/>
                       	 </li>
                     </ul>
                 </div>
             <% } %>
             </div>
			
		 </div>
		 
		 <div class="pagination">
		    <span class="pre_page">
		    <%if (!pagination.isFirstPage() && pagination.getPageCount() > 1) { %>
		        <a href = "<%=PathUtil.getFullPath("message/listMess") %>?currentPage=<%=pagination.getCurrentPage() - 1 %>">
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
		    <a href = "<%=PathUtil.getFullPath("message/listMess") %>?currentPage=<%=pagination.getCurrentPage() + 1 %>">
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
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/messageList.js"></script>
  </body>
</html>
