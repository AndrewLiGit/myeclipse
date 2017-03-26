<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录-淘书网</title>
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/login.css">
	<link rel="stylesheet" type="text/css" href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css">

  </head>
  <body>
	 <%String tipMessage = (String)request.getAttribute(Constants.TIP_MESSAGE);
	     String visibility = "hidden";
	     String visUser = "hidden";
	     String visPassword = "hidden";
	     if (tipMessage != null) {
	         if (tipMessage.equals("用户名不存在。")) {
	             visUser = "visible";
	             visibility = "visible";
	         }
	         if (tipMessage.equals("密码不正确。")) {
	             visPassword = "visible";
	             visibility = "visible";
	         }
	     }
	
	     Map<String, String> errorFields = (Map<String, String>)request.getAttribute(Constants.ERROR_FIELDS);
	     if (errorFields == null) {
	         errorFields = new HashMap<String, String>();
	     }
	   %>
  	<div id="logo"><img src="<%=PropertyUtil.getStaticUrl() %>/images/3.jpg" /></div>
  	
  	<div id="login_img"><img src="<%=PropertyUtil.getStaticUrl() %>/images/login.png" /></div>
  	
  	<div id="login">
  		<form action="<%=PathUtil.getFullPath("user/login")%>" method="POST" id="loginForm">
            <div class="login_form_name">
                <img class="login_form_username_img" src="<%=PropertyUtil.getStaticUrl() %>/images/ICN_Usename_20x20.png">
                <input class="login_form_username_input" type="text" name="userName" placeholder="用户名" id="userName" />
                <input type="hidden" name="go" value="<%=request.getAttribute("go") == null ? "" : request.getAttribute("go") %>"/>
                <input type="hidden" name="queryString" id="queryString"/>
            </div>
            <img class="login_form_username_wrong" id="username_wrong" style="visibility: <%=visUser %>;" alt="wrong" src="<%=PropertyUtil.getStaticUrl() %>/images/ICN_Client_Login_Wrong_20X20.png">

            <div class="login_form_password">
                <img class="login_form_password_img" alt="username" src="<%=PropertyUtil.getStaticUrl() %>/images/ICN_Password_20x15.png">
                <input class="login_form_password_input" type="password" name="password" placeholder="密码" id="password" />
            </div>
            <img id="login_form_password_wrong" style="visibility: <%=visPassword %>;" alt="wrong" src="<%=PropertyUtil.getStaticUrl() %>/images/ICN_Client_Login_Wrong_20X20.png">

            <div id="error_div" style="visibility: <%=visibility %>;">
                <label id = "error" class = "errorMessage" >
                    <%=tipMessage %>
                </label>
            </div>

            <input class="login_form_button" type="button" value="登&nbsp;&nbsp;&nbsp;&nbsp;录"/>
            <div class="login_form_register">
               <!-- <a class="forgot_password" href="" >忘记密码</a> -->
               <a class="register" href="<%=PathUtil.getFullPath("user/register") %>">立即注册</a>
            </div>
        </form>
  	</div>
  	
  	<%@include file="/WEB-INF/jsp/footer.jsp" %>
  	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/login.js"></script>
  </body>
</html>
