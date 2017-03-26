<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lzjtu.bookstore.util.PropertyUtil"%>
<%@ page import="com.lzjtu.bookstore.util.PathUtil"%>
<%@ page import="com.lzjtu.bookstore.util.StringUtil"%>
<%@ page import="com.lzjtu.bookstore.Constants"%>

<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户注册-淘书网</title>
	<link href="<%=PropertyUtil.getStaticUrl() %>/css/style.css" rel="stylesheet" type="text/css"></link>
    <link href="<%=PropertyUtil.getStaticUrl() %>/css/register.css" rel="stylesheet" type="text/css"></link>
    <link href="<%=PropertyUtil.getStaticUrl() %>/css/footer.css" rel="stylesheet" type="text/css"></link>
  </head>
  
  <body>
    
    <div id="header" class="wrap">
		<div id="tool">
			<div id="welcome">
			    <p><a href="<%=PathUtil.getFullPath("book/index") %>">首页</a>&nbsp;&nbsp;欢迎光临淘书网，请<a href="<%=PathUtil.getFullPath("user/login") %>"><span>登录</span></a>&nbsp;<a href="<%=PathUtil.getFullPath("user/register") %>">免费注册</a></p>
			</div>
			<div class="help">
				<a href="<%=PathUtil.getFullPath("book/showShopCart") %>" class="shopping">购物车</a>
				<a href="<%=PathUtil.getFullPath("order/listByUserId") %>">我的订单</a>
				<a href="<%=PathUtil.getFullPath("message/list") %>">留言</a>
				<a href="<%=PathUtil.getFullPath("user/logout") %>">退出</a>
			</div>
		</div>
		<div id="errorFlashMessage">
        </div>
		<%String keyword = (String)request.getAttribute("keyword"); %>
		<div>
			<div id="logo"><img src="<%=PropertyUtil.getStaticUrl() %>/images/3.jpg" /></div>
			<input type="text" id="search" placeholder="搜索  书名、作者、出版社" value="<%=StringUtil.htmlEncode(keyword) %>"/>
			<button type="submit" id="search_button">搜索</button>
		</div>
	</div>
	
	<div id="childNav">
		<div class="wrap">
			<ul class="clearfix">
				<li class="first"><a href="#" class="category">童书</a></li>
				<li><a href="#" class="category">电子书</a></li>
				<li><a href="#" class="category">小说</a></li>
				<li><a href="#" class="category">文艺</a></li>
				<li><a href="#" class="category">青春</a></li>
				<li><a href="#" class="category">成功励志</a></li>
				<li><a href="#" class="category">生活</a></li>
				<li><a href="#" class="category">人文社科</a></li>
				<li><a href="#" class="category">经管</a></li>
				<li><a href="#" class="category">科技</a></li>
				<li><a href="#" class="category">教育</a></li>
				<li><a href="#" class="category">工具书</a></li>
				<li class="last"><a href="#" class="category">期刊</a></li>
			</ul>
		</div>
	</div>
	<br>
	<form method="POST" name="reg" id="register_form" action="<%=PathUtil.getFullPath("user/save")%>">
	<table id="table" align="center">
		<tr>
			<td colspan="2" align="center" background="<%=PropertyUtil.getStaticUrl() %>/images/bg2.gif">
			<font color="#ffffff"><b>用户注册</b></font></td>
		</tr>
		<tr>
			<td width="40%" class="table_left"><b>用户名</b>：<br>
			注册用户名长度限制为6－12字节</td>
			<td width="60%" class="table_right">
				<input maxLength="12" size="32" id="user_name" name="userName">
				<font color="#FF0000">*</font>
			</td>
		</tr>
		<tr>
			<td width="40%" class="table_left"><b>密码(至少6位，至多12位)</b>：<br>
			请输入密码，区分大小写。<br>
			</td>
			<td width="60%" class="table_right">
				<input type="password" maxLength="12" size="32" id="password" name="password">
				<font color="#FF0000">*</font>
			</td>
		</tr>
		<tr>
			<td width="40%" class="table_left"><b>密码(至少6位，至多12位)</b>：<br>
			请再输一遍确认</td>
			<td class="table_right">
				<input type="password" maxLength="12" size="32" id="password2" name="password2">
				<font color="#FF0000">*</font>
			</td>
		</tr>
		
		<tr>
			<td class="table_left"><b>Email地址</b>：<br>
			请输入有效的邮件地址</td>
			<td class="table_right">
				<input maxLength="50" size="32" maxlength="32" id="email" name="email" >
				<font color="#FF0000">*</font>
			</td>
		</tr>
		<tr>
			<td class="table_left"><b>手机</b>：</td>
			<td class="table_right">
				<input type="text" size="32" maxlength="16" name="mobile" >
			</td>
		</tr>
		
		<tr>
			<td class="table_left"><b>身份证号</b>：</td>
			<td class="table_right">
			<input type="text" size="32" maxlength="18" name="idCard">
			</td>
		</tr>

		<tr>
			<td class="table_left"><b>性别</b>：</td>
			<td class="table_right">
				<input type="radio" name="gender" value="0" checked="checked"/>&nbsp;&nbsp;<b>男</b>&nbsp;&nbsp;&nbsp;&nbsp;
    			<input type="radio" name="gender" value="1"/>&nbsp;<b>女</b>
			</td>
		</tr>
		<tr>
			<td class="table_left"><b>所在地区</b>：</td>
			<td class="table_right">
			<select name="country" >
				<option value="中国">中国</option>
				<option value="中国香港">中国香港</option>
				<option value="中国台湾">中国台湾</option>
			</select>
			<select name="province" >
                <option value="安徽" selected>安徽</option>

                <option value="北京" >北京</option>

                <option value="重庆" >重庆</option>

                <option value="福建" >福建</option>

                <option value="广东" >广东</option>

                <option value="甘肃" >甘肃</option>

                <option value="广西" >广西</option>

                <option value="贵州" >贵州</option>

                <option value="河南" >河南</option>

                <option value="湖北" >湖北</option>

                <option value="河北" >河北</option>

                <option value="海南" >海南</option>

                <option value="香港" >香港</option>

                <option value="黑龙江" >黑龙江</option>

                <option value="湖南" >湖南</option>

                <option value="吉林" >吉林</option>

                <option value="江苏" >江苏</option>

                <option value="江西" >江西</option>

                <option value="辽宁" >辽宁</option>

                <option value="澳门" >澳门</option>

                <option value="内蒙古" >内蒙古</option>

                <option value="宁夏" >宁夏</option>

                <option value="青海" >青海</option>

                <option value="四川" >四川</option>

                <option value="山东" >山东</option>

                <option value="上海" >上海</option>

                <option value="陕西" >陕西</option>

                <option value="山西" >山西</option>

                <option value="天津" >天津</option>

                <option value="台湾" >台湾</option>

                <option value="新疆" >新疆</option>

                <option value="西藏" >西藏</option>

                <option value="云南" >云南</option>

                <option value="浙江" >浙江</option>

                <option value="其它" >其它</option>
			</select>省
			<input type="text" size="8" name="city" >市/县</td>
		</tr>
		<tr>
			<td class="table_left"><b>联系地址</b>：</td>
			<td class="table_right">
				<input type="text" size="48" maxlength="32" name="street" >
				<input type="hidden" name="address" >
			</td>
		</tr>
		<tr>
			<td class="table_left"><b>出生年月</b>：</td>
			<td class="table_right">
			<input type="date" name="birthday" id="birthday" >
			</td>
		</tr>
		
		<tr>
			<td class="table_left"><b>真实姓名</b>：</td>
			<td class="table_right">
			<input type="text" size="32" maxlength="16" name="rightName" >
			</td>
		</tr>
		
		<!-- <tr>
			<td class="table_left"><b>密码提示问题</b>：</td>
			<td class="table_right">
			<input type="text" size="32" maxlength="16" name="question" >
			</td>
		</tr>
		<tr>
			<td class="table_left"><b>密码提示答案</b>：</td>
			<td class="table_right">
			<input type="text" size="32" maxlength="16" name="answer" >
			</td>
		</tr> -->
		
		<tr>
			<td class="table_register" valign="middle" colspan="2" align="center">
			<input type="button" id="register" value="注 册">&nbsp;&nbsp;&nbsp;
			<input type="reset" id="reset" name="reset" value="清 除"></td>
		</tr>
	</table>
	</form>
	<input type="hidden" id="path" value="<%=PathUtil.getFullPath("book") %>"/>
	<%@include file="/WEB-INF/jsp/footer.jsp" %>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl() %>/js/register.js"></script>
  </body>
</html>
