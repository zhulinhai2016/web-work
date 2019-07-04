<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
 <script  src="../jquery-1.8.3.js"></script><!-- 引入jquery框架 -->
<script type="text/javascript">


function register(){
	//验证手机号码的正则表达式
	var myreg =  /[a-zA-Z0-9_]{3,16}/; 
	//校验用户名是否为空
	if($("#regname").val()==""){
		 document.getElementById("name").style.display="inline";
	 	 document.getElementById("name").innerHTML="输入不能为空";
	}else if(!myreg.test($("#regname").val())){//校验用戶名是否合法
		document.getElementById("name").style.display="inline";
 	 	document.getElementById("name").innerHTML="用户名由数字跟字母组成的3到16位";
	}
	//校验密码是否为空
	if($("#regpass").val()==""){
		document.getElementById("passid").style.display="inline";
 	 	document.getElementById("passid").innerHTML="密码不能为空";
	}

  }

//校验用户名输入是否合法
function user(){
	var myreg =  /[a-zA-Z0-9_]{3,16}/; 
	if($("#regname").val()==""){
		 document.getElementById("name").style.display="inline";
	 	 document.getElementById("name").innerHTML="输入不能为空";
	}else if(!myreg.test($("#regname").val())){//校验用戶名是否合法
		document.getElementById("name").style.display="inline";
 	 	document.getElementById("name").innerHTML="用户名由数字跟字母组成的3到16位";
	}else if(( /[a-zA-Z0-9_]{3,16}/.test($("#regname").val()))){
		$("#name").css("display","none");
	}
}	  

	
//密码鼠标离开事件			 
function passft(){
	if($("#regpass").val()==""){
		document.getElementById("passid").style.display="inline";
 	 	document.getElementById("passid").innerHTML="密码不能为空";
	}else{
  		document.getElementById("passid").style.display="inline";
 	 	document.getElementById("passid").innerHTML="密码长度应为6-15位";
 	 	if($("#regpass").val().length>5 && $("#regpass").val().length<16){
 	 		document.getElementById("passid").style.display="inline";
	 	 	document.getElementById("passid").innerHTML="  ";
 	 	}
  	}
}
//确认密码鼠标离开事件	
function repassft(){
	if($("#regpass1").val()==""){
		document.getElementById("pass2id").style.display="inline";
 	 	document.getElementById("pass2id").innerHTML="密码不能为空";
	}else{
  		document.getElementById("pass2id").style.display="inline";
 	 	document.getElementById("pass2id").innerHTML="密码长度应为6-15位";
 	 	if($("#regpass1").val().length>5 && $("#regpass1").val().length<16){
 	 		document.getElementById("pass2id").style.display="inline";
	 	 	document.getElementById("pass2id").innerHTML="  ";
 	 	}
  	}
	if($("#regpass1").val()==$("#regpass").val()){
			document.getElementById("pass2id").style.display="inline";
	 		document.getElementById("pass2id").innerHTML="  ";
		}else{
			document.getElementById("pass2id").style.display="inline";	 	 	
	 		document.getElementById("pass2id").innerHTML="密码不一致";
		}
}

</script>

<style type="text/css">
/* .iop{
 background-size:1920px 950px;
  } */
    a{ text-decoration:none; } 


  
  html{   
    width: 100%;   
    height: 100%;   
    overflow: hidden;   
    font-style: sans-serif;   
}   
body{   
    width: 100%;   
    height: 100%;   
    font-family: 'Open Sans',sans-serif;   
    margin: 0;   
    background-color: #4A374A;   
}   
#login{   
    position: absolute;   
    top: 50%;   
    left:50%;   
    margin: -150px 0 0 -150px;   
    width: 300px;   
    height: 300px;   
}   
#login h1{   
    color: #fff;   
    text-shadow:0 0 10px;   
    letter-spacing: 1px;   
    text-align: center;   
}   
h1{   
    font-size: 3em;   
    margin: 0.67em 0;  
    margin-top:-10px; 
}   
input{   
    width: 278px;   
    height: 18px;   
    margin-bottom: 10px;   
    outline: none;   
    padding: 10px;   
    font-size: 13px;   
    border-top: 1px solid BEBEBE;   
    border-left: 1px solid BEBEBE;   
    border-right: 1px solid BEBEBE;   
    border-bottom: 1px solid BEBEBE;   
    border-radius: 4px;   
    background-color: #84c1ff;   
    
}   
.but{   
    width: 300px;   
    min-height: 20px;   
    display: block;   
    background-color: #4a77d4;   
    border: 1px solid #3762bc;   
    color: #fff;   
    padding: 9px 14px;   
    font-size: 15px;   
    line-height: normal;   
    border-radius: 5px;   
    margin: 0;   
    margin-top:25px;
    
} 

.submit{width:300px;
         height:40px;
         margin-top:30px;
         background-color: #0066CC;
  }
  
  }
.username{
    margin-top:15px;
}


</style>
</head>
<body class="iop"   background="../img/11.jpg">

   <div id="login">  
        <h1>设备管理登录</h1>  
        <form method="post">    
            <input type="text"  name="password" placeholder="请输入用户名" name="u" class="username"  id="regname"  onblur="user()"   ></input>  
            <p style="display: inline; color:red; " id="name" class="hint"></p>
            
            <input type="password"  name="password" placeholder="请输入密码" name="p" id="regpass" onblur="passft()"></input> 
             <p style="display:none; color:red;" id="passid" class="hint"></p>
<!--             <inpput class="but" type="submit"  onclick="register();" value>登  录 
 -->                        <input type="button"  class="submit"  onclick="register();"  value="登  录 "/>
            
          
        </form>  
    </div>

</body>
</html>