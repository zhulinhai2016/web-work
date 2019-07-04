<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../jquery-1.8.3.js"></script>  <!-- 引入jquery框架 -->

 <style >
   *{padding:0;margin:0; list-style:none;}
   .nav{float:left;}
   .nav li.jd{background:#9D9D9D;}
   .box{width:800px;margin:50px; auto; height:650px;float:none;  border:1px solid red; background-color:#89c2dd}
   .nav li{width:200px;height:30px; background:#97CBFF;margin-bottom:3px; text-align: center;}
   .out{width:598px;height:600px; margin-top:-34px; border:1px solid green;background:#E8E8E8; float:right; position: relative;}
   .op{width:200px; height:220px;position: absolute; border:0; }
   .color{width:200px;height:30px; background:red;margin-bottom:3px; text-align: center; }
   .name{margin-left:200px; margin-top:-130px;}
   .lass{background-color:#97CBFF; width:100%; height:49.5px;}
   .user{background-color:red; width:20px; height:20px;}
  
   </style>
<script type="text/javascript">

$(function(){
	 $(".color").bind("click",function(){ $(this).next(".nav").toggle();});  

	$(".nav li").click(function(){
	    $(this).addClass("jd").siblings().removeClass("jd")
	    var index=$(this).index();
		 $(".op").eq(index).fadeIn().siblings().fadeOut(); 
		 
	})
})


     
</script>
</head>
<body>
</head>
<div>
</div>
<div class="box">

<div>
<ul class="lass">
   <li></li>
   <li></li>
   <li></li>
   <li></li>


</ul>
</div>
 <div>
<h3 class="color">教学大楼</h3>
<ul  class="nav">
 <li>教学楼第一层楼</li>
 <li>教学楼第二层楼</li>
 <li>教学楼第三层楼</li>
 <li>教学楼第四层楼</li>

</ul>
</div>
 <div class="out">
 
 <!--  <iframe src="../img/jiankong.png"  class="op"></iframe>
  
  <img src="../img/9.jpg"/> <img src="../img/77.jpg"/> 

  <iframe src="../img/touyingyi.png" class="op"></iframe>

  <iframe src="../img/9.jpg" class="op"></iframe>

  <iframe src="../img/77.jpg" class="op"></iframe> -->
 
 <div class="op"><img src="../img/2.png" /> <img src="../img/1.png"  class="name"/></div>
 <div class="op"><img src="../img/3.png"/></div>
 <div class="op"><img src="../img/1.png"/> </div>
 <div class="op"><img src="../img/4.png"/> </div>
 
 
<!--
 <input type="image" src="../img/jiankong.png" class="op" />
 <input type="image" src="../img/11.jpg" class="op"/>
 <input type="image" src="../img/jiankong.png"  />
 <input type="image" src="../img/jiankong.png" />
</div> 
  -->

</div>
<div>
    <ul class="car">
        <li><img src="../img/oo.jpg" width="50px"></li>
        <li><img src="../img/oo.jpg" width="50px"></li>
        <li></li>
        <li></li>
    
    
    </ul>
  

</div>


</div>
</body>
</html>
