<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link href="${pageContext.request.contextPath}/login/css/login.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.info{font-size: 12px;color: red;margin-left: 80px;}
.sub:HOVER {
	
}
</style>
</head>	
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a style="font-size:25px"></a>
				<a style="font-size:25px"></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#" target="_blank"></a></li>
						<li><a href="#" target="_blank"></a></li>
					</ul>
				</div>
				<h2 class="login_title" style="font-size:16px">用户登录</h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="${pageContext.request.contextPath}/login/login" method="post">
					<p>
						<label>用户名：</label>
						<input name="userName" value="" type="text" onfocus='validCheck()' oninput='validCheck()' onblur='validCheck()' style="width:140px;height:20px;" class="login_input" />
						<br/>
						<span class="info">${userName}</span>
					</p>
					<p>
						<label>密&nbsp;&nbsp;&nbsp;码：</label>
							<input name="password" type="password" onfocus='validCheck()' oninput='validCheck()' onblur='validCheck()' style="width:140px;height:20px;" class="login_input" value="" />
						<br/>
						<span class="info">${userPwdMsg}</span>
					</p>
					<p><br/><span class="info">${errorMsg}</span></p>
					<div class="login_bar" style="margin-left:10px;">
						<input id="loginBtn" class="sub" type="submit" onclick="validLogin()" value="" />
					</div>
				</form>
			</div>
			<script>
			validCheck();
			function validCheck(){
				var tempObj = document.querySelectorAll('.login_input');
				var tempState = 0;
				for(var i=0;i<tempObj.length;i++){
					if(tempObj[i].value ==''){
						tempState++;
						tempObj[i].borderColor = 'red';
					}else{
						tempObj[i].borderColor = 'black';
						tempObj[i].borderColor = ''
					}
				}
				
				validCheck();
				function validCheck(){
					var tempState=0;
					for(var i=0;i<tempObj.length;i++){
						if(tempObj[i].value ==''){
							tempState++;
							tempObj[i].borderColor = 'red';
						}else{
							tempObj[i].borderColor = 'black';
						}
					}
				}
				if(tempState>0){
					document.getElementById('loginBtn').type = 'button';
				}else{
					document.getElementById('loginBtn').type = 'submit';
					docu
				}
			}
			function validLogin(){
				var tempObj = document.querySelectorAll('.login_input');
				if(tempObj[0].value=='')alert('用户名不能为空！')
				if(tempObj[1].value=='')alert('密码不能为空');
				if(tempObj[2].value=='')alert('密码不能为空');
			}
			</script>
			<div class="login_banner"><img src="${pageContext.request.contextPath}/login/img/login_banner.jpg" /></div>
			<!-- <div class="login_main">
				<ul class="helpList">
					<li><a href="#">测试帐号：</a></li>
					<li><a href="#">默认密码：</a></li>
				</ul>  
			</div> -->
		</div>
		<div id="login_footer">
			Copyright &copy; 2020
		</div>
	</div>
</body>
</html>