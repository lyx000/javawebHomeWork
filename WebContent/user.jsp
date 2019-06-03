<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.M.UserDAOIO"%>
<%@page import="com.M.DataBase"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.M.User"%>
<%
	User user = new User();
	user.setName(request.getParameter("name"));
	String name = user.getName();
	//System.out.println(name);
	Statement st = null;
	Statement st1 = null;
	Connection con = null;
	ResultSet rs = null;
	ResultSet rs1 = null;
	DataBase db = new DataBase();
	con = db.ConnectionSQL();
	st = con.createStatement();
	st1 = con.createStatement();
	String sql = "select * from user";
	rs = st.executeQuery(sql);
	String sql1 = "select * from talk where nameB='"+name+"'";
	rs1 = st1.executeQuery(sql1);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="css/user.css"/>
		<script src="js/jquery-3.3.1.js"></script>
		<script src="js/jquery-3.3.1.min.js"></script>
		<script src="js/jquery.validate.min.js"></script>
		<script type="text/javascript">
		//动画效果
		$(document).ready(function(){
			$("#rpwdshow").hide();
			$("#List").hide();
			$("#Action").hide();
		})
		$(document).ready(function(){
			$("#rpass").click(function(){
				$("#rpwdshow").show();
				$("#List").hide();
				$("#Action").hide();
			})
		})
		$(document).ready(function(){
			$("#ShowList").click(function(){
				$("#List").show();
				$("#Action").hide();
				$("#rpwdshow").hide();
			})
		})
		$(document).ready(function(){
			$("#ActionList").click(function(){
				$("#Action").show();
				$("#List").hide();
				$("#rpwdshow").hide();
			})
		})
		//验证
		$().ready(function(){
			$("#form-mid").validate({
				rules:{
					pwd:{
						required:true,
					},
					npwd:{
						required:true,	
					},
					rpwd:{
						required:true,
						equalTo: "#npwd",
					},
				},
				messages:{
					pwd:"密码不能为空",
					npwd:"密码不能为空",
					rpwd:{
						required:"不能为空",
						equalTo:"密码不一致",
					},
				},
			})
		})
		</script>
		<title>在线留言系统</title>
	</head>
	<body>
		<div class="head">
			<div class="letf">
				<p><% out.println(name); %>欢迎您</p>
			</div>
			<div class="right">
				<a id="rpass" href="#">重置密码</a>
				<a href="index.html">退出登录</a>
			</div>
		</div>
		<div id="ShowList">
			<button>查看留言</button>
		</div>
		<div id="ActionList">
			<button>发送留言</button>
		</div>
		<div id="rpwdshow" style="background-image: url(img/background.jpg);background-size: 100% 100%;">
			<form action="UpdatePass" method="post" id="form-mid">
				用户名：<input type="text" name="name"><br>
				旧密码：<input type="password" name="pwd"/><br>
				新密码：<input type="password" name="npwd" id="npwd"/><br>
				重复密码：<input type="password" name="rpwd"/><br>
				<input type="submit" name="submit" value="修改密码"/>
			</form>
		</div>
		<div id="List">
			<%
				while(rs1.next()){
					out.println("<div class='title'>"+rs1.getString("nameA")+"</div>");
					out.println("<div class='Talk'>"+rs1.getString("talk")+"</div>");
					out.println("<div class='time'>"+rs1.getString("time")+"</div>");
					out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
				}
			%>
		</div>
		<div id="Action" style="background-image: url(img/background.jpg);background-size: 100% 100%;">			
			<form action="AddT" method="post" id="form-mid1">
				<div class=select>发送方：<input type="text" name=nameA value=<% out.println(name);%>></div>
				<div class="select">
					接收方：<select name="select">
					<% 
						while(rs.next()){
							out.println("<option value="+rs.getString("name")+">"+rs.getString("name")+"</option>");
						}
					%>
				</select>
				</div>
				<textarea name="talk" id="talk"></textarea><br>
				<input type="submit" name="submit" value="提交" style="margin-left: 35%;"/>
			</form>
		</div>
	</body>
</html>