<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@page import="com.lzjtu.bookstore.model.Pagination"%>
<%@page import="com.lzjtu.bookstore.model.ContactInfo"%>
<%@page import="com.lzjtu.bookstore.model.User"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户详情</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/topLeft.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/userDetail.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/topLeft.js"></script>
  </head>
  
  <body>
    <%@include file="/WEB-INF/jsp/admin/topLeft.jsp" %>
    <%
    	ContactInfo contactInfo = (ContactInfo)request.getAttribute("contactInfo");
    	User users = (User)request.getAttribute("users");
     %>
    <div class="main">
		<h2>用户详情</h2>
		<div class="detail">
			<ul>
	          <li>
	              <label class="left">用户ID&nbsp;:&nbsp;&nbsp;</label>
	              <label class="right"><%=users.getId() %></label>
	          </li>
	          <li>
	              <label class="left">用户名&nbsp;:&nbsp;&nbsp;</label>
	              <label class="right"><%=users.getUserName() %></label>
	          </li>
	          <li>
	              <label class="left">密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;:&nbsp;&nbsp;</label>
	              <label class="right"><%=users.getPassword() %></label>
	          </li>
	          <li>
	              <label class="left">性&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;:&nbsp;&nbsp;</label>
	              <%if(contactInfo.getGender() == 0) { %>
	              <label class="right">男</label>
	              <%} else { %>
	              <label class="right">女</label>
	              <%} %>
	          </li>
	          <li>
	              <label class="left">生&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;:&nbsp;&nbsp;</label>
	              <label class="right"><%=StringUtil.convertDate(contactInfo.getBirthday()) %></label>
	          </li>
	          <li>
	              <label class="left">邮&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;:&nbsp;&nbsp;</label>
	              <label class="right"><%=contactInfo.getEmail() %></label>
	          </li>
	          <li>
	              <label class="left">身份证号&nbsp;:&nbsp;&nbsp;</label>
	              <label class="right"><%=contactInfo.getIdCard() %></label>
	          </li>
	          <li>
	              <label class="left">手机号&nbsp;:&nbsp;&nbsp;</label>
	              <label class="right"><%=contactInfo.getMobile() %></label>
	          </li>
	          
	          <li>
	              <label class="left">真实姓名&nbsp;:&nbsp;&nbsp;</label>
	              <label class="right"><%=contactInfo.getRightName() %></label>
	          </li>
	          <li>
	              <label class="left">地&nbsp;&nbsp;&nbsp;&nbsp;址&nbsp;:&nbsp;&nbsp;</label>
	              <label class="right"><%=contactInfo.getAddress() %></label>
	          </li>
	      </ul>
		</div>
	</div>
    
    <%@include file="/WEB-INF/jsp/footer.jsp" %>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/userList.js"></script>
  </body>
</html>
