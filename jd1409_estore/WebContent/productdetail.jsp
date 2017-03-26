<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!--文件头开始-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<meta name="description" content="杰普电子商务门户">
		<title>杰普电子商务门户</title>
		<LINK href="css/main.css" rel=stylesheet>
		<script language = "JavaScript" src = "js/main.js"></script>
	</head>
	<body onLoad="MM_preloadImages('images/index_on.gif','images/reg_on.gif','images/order_on.gif','images/top/topxmas/jp_on.gif','images/top/topxmas/download_on.gif','images/top/topxmas/bbs_on.gif','images/top/topxmas/designwz_on.gif')" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="table2">
		  <tr>
		    <td align="left" width="7%" background="images/top_bg.gif"><img src="images/logo.gif" width="165" height="60"></td>
		    <td width="62%" background="images/top_bg.gif">　</td>
		    <td width="31%" background="images/top_bg.gif" align="right">
			<img src="images/top_r.gif" width="352" height="58" border="0"></td>
		  </tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td background="images/dh_bg.gif" align="left" height="12">
		      <table width="100" border="0" cellspacing="0" cellpadding="0" align="center">
		        <tr>
		          <td width="5%">　</td>
		          <td width="10%"><a href="index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1','','images/index_on.gif',1)">
					<img name="Image1" border="0" src="images/index.gif" width="90" height="36"></a></td>
		          <td width="10%"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image2','','images/reg_on.gif',1)">
					<img name="Image2" border="0" src="images/reg.gif" width="92" height="36"></a></td>
		          <td width="10%"><a href="shopcart.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image4','','images/carts_on.gif',1)">
					<img name="Image4" border="0" src="images/cart.gif" width="92" height="36"></a></td>
		          <td width="10%"><a href="OrderServlet" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image5','','images/order_on.gif',1)">
					<img name="Image5" border="0" src="images/order.gif" width="92" height="36"></a></td>
		          <td width="10%"><a href="LoginOutServlet" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image6','','images/exit_on.gif',1)">
					<img name="Image6" border="0" src="images/exit.gif" width="92" height="36"></a></td>
		        </tr>
		      </table>
		    </td>
		  </tr>
		</table>
		<table cellspacing="1" cellpadding="3" align="center" border="0" width="98%">
		<tr>
		<td width="65%"><BR>
		>> 欢迎访问 <b>杰普电子商务门户</b> </td>
		<td width="35%" align="right">

		</td></tr></table>

<!--文件体开始-->
		<table cellspacing=1 cellpadding=3 align=center class=tableBorder2>
		<tr>
		<td height=25 valign=middle>
                  <img src="images/Forum_nav.gif" align="middle">
                  <a href=index.jsp>杰普电子商务门户</a> → 产品明细
                </td>
                </tr>
		</table>
              <br>

		<table cellpadding=3 cellspacing=1 align=center class=tableborder1>
		<tr>
			<td valign=middle align=center height=25 background="images/bg2.gif" colspan="2"><p><font color="#ffffff"><b>${sessionScope.product.name }</b></font></td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="20%">【
			作&nbsp; 者 】</td>
			<td class=tablebody1 valign=middle width="80%">&nbsp;&nbsp;${sessionScope.product.writer }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="20%">【
			价&nbsp; 格 】</td>
			<td class=tablebody1 valign=middle width="80%">&nbsp;&nbsp;${sessionScope.product.baseprice }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="">【出 版 社】</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;${sessionScope.product.publish }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="">【 书&nbsp; 号
			】</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;${sessionScope.product.productid }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="">【 页&nbsp; 码
			】</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;${sessionScope.product.pages }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="">【所属类别】</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;${sessionScope.product.name }</td>
		</tr>
		<tr>
			<td class=tablebody1 align=center width="" valign="top">【 简&nbsp; 介
			】</td>
			<td class=tablebody1 valign=middle width="">${sessionScope.product.description }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="">　</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;<img border="0" src="${sessionScope.product.images }" width="127" height="180"></td>
		</tr>
		<tr>
			<td class=tablebody2 valign=middle align=center colspan="2"><a href="AddShopcartServlet?productid=${sessionScope.product.productid }">
			<img border="0" src="images/buycar.gif" width="92" height="21"></a> </td>
		</tr>
		</table>

<!--文件尾开始-->
		<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" height="28">
		  <tr>
		    <td height="17" background="images/bot_bg.gif">
		      　</td>
		  </tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
		  <tr>
		    <td bgcolor="#f1f1f6" height="53" valign="center">
			<p align="center">Copyright &copy;2015 - 2025 <a href="http://www.briup.com.cn"><b><font face="Verdana,">briup</font></b><font color=#CC0000>.com.cn</font></b></a><br>
			</td>
		  </tr>
		</table>
	</body>
</html>
