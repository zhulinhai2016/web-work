<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户控制页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="public/css/public.css">
	<link rel="stylesheet" type="text/css" href="public/css/boxy.css">
	<link rel="stylesheet" type="text/css" href="public/css/jquery-ui.min.css">
    <link rel="stylesheet" href="public/css/haha.css"/>
	<script src="public/js/jquery.js" type="text/javascript" charset="utf-8"></script>
	<script src="public/js/jquery.boxy.js" type="text/javascript" charset="utf-8"></script>	
<script src="public/js/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="public/js/public.js" type="text/javascript" charset="utf-8"></script>
<style type="text/css">
a{
text-decoration:none;
}
.kt_1{position:relative;left:7px;top:44px;}
.kt_2{position:relative;left:21px;top:-108px;}
.kt_3{position:relative;left:84px;top:-126px;}
.kt_gj{position:relative;left: 50px;top: -233px;}
.kt_sz{position:relative;left: 16px;top: -182px;}
</style>
	<script type="text/javascript">
	function changeImage(obj,path){
	   obj.getElementsByTagName('img')[0].src=path;
	 }
	</script>
  </head>  
  <script type="text/javascript">	

  </script>
  <body>
    
    <div class="frame-top">
    	<div class="user">
    	   <!-- <a href="./login/index.jsp" >退&nbsp;出</a> -->
    	
    	</div>
    	<div class="top-iteq">
    		<a class="nav_a" href="javascript:void(0)" onclick="javascript:if(confirm('您确定要全部开吗')) headAction('openAll','');"><img src="public/img/nav/qbk.png" width="60"></a>
    		<a class="nav_a" href="javascript:void(0)" onclick="javascript:if(confirm('您确定要全部关吗')) headAction('closeAll','')"><img src="public/img/nav/qbg.png" width="60"></a>
    	</div>
    	<div class="top-itew">
    		 <a class="nav_a" href="javascript:void(0)" onclick="showBuildControlBox(1)"><img src="public/img/nav/zdk.png" width="60"></a>
    		<a class="nav_a" href="javascript:void(0)" onclick="showBuildControlBox(2)"><img src="public/img/nav/zdg.png" width="60"></a> 
    		
    	</div>
    	 <div class="top-itee">
    		<a class="nav_a" href="javascript:void(0)" onclick="showFloorControlBox(1)"><img src="public/img/nav/zck.png" width="60"></a>
    		<a class="nav_a" href="javascript:void(0)" onclick="showFloorControlBox(2)"><img src="public/img/nav/zcg.png" width="60"></a>
    	</div> 
    	
    	<div class="top-iter">
    		<a class="nav_a" href="javascript:void(0)" id="addBuildBtn"><img src="public/img/nav/tjdl.png" width="60"></a>
    		<a class="nav_a" href="javascript:void(0)" id="addFloorBtn"><img src="public/img/nav/tjlc.png" width="60"></a>
    		<a class="nav_a" href="javascript:void(0)" id="addClassBtn"><img src="public/img/nav/tjjs.png" width="60"></a>
    	</div>
    	<div class="user">
    	   <!-- <a href="./login/index.jsp" >退&nbsp;出</a> -->
    	</div>
    	
    	<div class="use">
    	  <a href="javascript:void(0)"><img src="public/img/nav/16.2.png" width="280"></a>
    	  <a href="javascript:void(0)"><img src="public/img/nav/16.2.png" width="160"></a>
    	</div>
    </div>
    <div class="frame-bottom table">
    	<div class="row">
	    	<div id="menu-container" class="frame-left td">
	    		
	    	</div>
	    	<div class="frame-right td" id="class_container">
	    	<div class="frame-right td" id="class_container"></div>
	    		
	    	</div>
    	</div>
    </div>
    <div id="face" style="display:none">
   				<!-- 添加大楼模板 start -->
	   	 <div class="addBuildHtml showBoxItem" >
	    	<div class="input-item" >
	    		<div class="input-item_p">大楼名称：</div>
	    		<div><input  class="input-item_inp" type="text" name="name" /></div>
	    		<div><inpit  class="input-item_inp" type="text" name="name" /></div>
	    	</div>	    	
	    	<div class="input-item" style="width:100%;margin:5px;background:#ccc;">
	    		<button class="addBuildSubmit" style="font">提交</button>
	    		
	    	</div>
	    </div> 
    	<!-- 添加大楼模板 end -->
    
    	<!-- 添加楼层模板 start -->
	   	<div class="addFloorHtml showBoxItem" >
	    	<div class="input-item" >
	    		<div class="input-item_p">教学楼:</div>
	    		<div><select class="input-item_inp" name="build"></select>
	    		</div>
	    	</div>
	    	<div class="input-item floor">
	    		<div  class="input-item_p">所在楼层：</div>
	    		<div><input  class="input-item_inp" type="number" name="floor" /></div>
	    	</div>	    	
	    	<div class="input-item" style="width:100%;margin:5px;background:#ccc;">
	    		<button class="addFloorSubmit">提交</button>
	    		<button class="addFloorSubmit">提交</button>
	    	</div>
	    </div> 
    	<!-- 添加楼层模板 end -->
    
    	<!-- 添加班级模板 start -->
	   	 <div class="addClassHtml showBoxItem">
	   	 	<div class="input-item">
	    		<div class="input-item_p">教学楼：</div>
	    		<div><select class="input-item_inp"  name="class_build"></select></div>
	    	</div>
	    	
	    	<div class="addClassHtml showBoxItem">
	    	   <div class="input-item">
	    	   </div>
	    	</div>
	    	<div class="input-item">
	    		<div class="input-item_p">楼&nbsp;层：</div>
	    		<div><select class="input-item_inp"  name="class_floor"></select></div>
	    	</div>
	    	<div class="input-item clazzIp">
	    		<div class="input-item_p">教室名：</div>
	    		<div><input   class="input-item_inp" type="text" name="class_name" /></div>
	    	</div>
	    	<div class="input-item clazzIp">
	    		<div class="input-item_p">总控IP：</div>
	    		<div><input  class="input-item_inp" type="text" name="class_ip" /></div>
	    		<div class></div>
	    	</div>
	    	<!-- <div class="input-item">
	    		<div>总控端口：</div>
	    		<div><input type="text" name="class_port"  /></div>
	    	</div> -->
	    	<div class="input-item clazzIp">
	    		<div class="input-item_p">中控IP：</div>
	    		<div><input  class="input-item_inp" type="text" name="class_center_ip" /></div>
	    	</div>
	    	<div class="input-item clazzIp">
	    		<div class="input-item_p">监控IP：</div>
	    		<div><input  class="input-item_inp" type="text" name="class_touy" /></div>
	    		<div><input  class="input-item_inp" type="text" name="class_touy" /></div>
	    	</div>
	    	
	    	<div class="input-item clazzIp">
	    	<div class="input-item clazzIp"></div>
	    		<div class="input-item_p">教室ID：</div>
	    		<div><input  class="input-item_inp" type="text" name="class_id" /></div>
	    	</div> 
	    	<div class="input-item" style="width:100%;margin:5px;background:#ccc;">
	    	<
	    		<button class="addClassSubmit">提交</button>
	    	</div>
	    	
	    	<div class=""></div>
	    </div> 
    	<!-- 添	加班级模板 end -->
    	
    	
    	<!-- classes-item start  -->
    	<div class="classes-item" id="[class_id]">	
    		<div class="table">
    			<div class="tr">
    				<!-- left devlist start -->
    				<div class="td" style="width:185px;height:215px;margin-left:-5px;">
    					<div style="width:170px;height:170px;">
		    				<div class="dev-itea">
	    						<img class="dev air_conditioner_on" onclick="openFirstWindow('#airConditioner','[class_id]','close')" style="display:none;" src="public/img/air_conditioner_on.png" />
					    		<img class="dev air_conditioner_offline" onclick="openFirstWindow('#airConditioner','[class_id]','close')" style="display:none;" src="public/img/air_conditioner_offline.png" />
					    		<img class="dev air_conditioner_off" onclick="openFirstWindow('#airConditioner','[class_id]','open')" src="public/img/air_conditioner_off.png" />
<!-- 	    						<img class="dev air_conditioner_on" onclick="doAction('closeAirConditioner','[class_id]')" style="display:none;" src="public/img/air_conditioner_on.png" /> -->
<!-- 					    		<img class="dev air_conditioner_offline" onclick="doAction('closeAirConditioner','[class_id]')" style="display:none;" src="public/img/air_conditioner_offline.png" /> -->
<!-- 					    		<img class="dev air_conditioner_off" onclick="doAction('openAirConditioner','[class_id]')" src="public/img/air_conditioner_off.png" /> -->
	    				
	    					</div>
	    					<div class="dev-ites">
	    						<img class="dev air_switch_on" onclick="doAction('closeAirSwitch','[class_id]')" style="display:none;" src="public/img/air_switch_on.png" />
								<img class="dev air_switch_offline" onclick="doAction('closeAirSwitch','[class_id]')" style="display:none;" src="public/img/air_switch_offline.png" />
								<img class="dev air_switch_off" onclick="doAction('openAirSwitch','[class_id]')" src="public/img/air_switch_off.png" />
	    						<img class="dev air_switch_off" onclick="doAction('openAirSwitch','[class_id]')" src="public/img/air_swit"/>
	    					</div>	    				
		    				<div class="dev-ited">
	    						<img class="dev other_switch_on" onclick="doAction('closeOtherSwitch','[class_id]')" style="display:none;" src="public/img/other_switch_on.png" />
								<img class="dev other_switch_offline" onclick="doAction('closeOtherSwitch','[class_id]')" style="display:none;" src="public/img/other_switch_offline.png" />
								<img class="dev other_switch_off" onclick="doAction('openOtherSwitch','[class_id]')" src="public/img/other_switch_off.png" />
	    					</div>
	    					<div class="dev-itef monitor" >
	    						
					    		<img class="dev monitor_offline" onclick="openFirstWindow('#monitor','[class_id]')" style="display:none;" src="public/img/monitor_offline.png" />
					    		<img class="dev monitor_off" onclick="openFirstWindow('#monitor','[class_id]')" src="public/img/monitor_off.png" />
	    						<img class="dev monitor_off" onclick="openFirstWindow('#monitor','[class_id]')" src="public/img/monitor_off.png" />
	    						<img class="dev monitor_off" onclick="openFIrstWindow('#mouitor','[class_id]')" src="public/img/monitor_off.png" />
	    					</div>
	    					<div class="dev-iteg">
	    						<img class="dev cloud_terminal_on" onclick="openFirstWindow('#cloudTerminal','[class_id]','close')" style="display:none;" src="public/img/cloud_terminal_on.png" />
					    		<img class="dev cloud_terminal_offline"  onclick="openFirstWindow('#cloudTerminal','[class_id]','close')" style="display:none;" src="public/img/cloud_terminal_offline.png" />
					    		<img class="dev cloud_terminal_off" onclick="openFirstWindow('#cloudTerminal','[class_id]','open')" src="public/img/cloud_terminal_off.png" />
	    						<img class="dev cloud_terminal_off" onclick="openFirstWindow('#cloudTerminal','[class_id]','open')" src="public/img/cloud_terminal_off.png"/>
	    					</div>
	    					<div class="dev-iteh">
	    						<img class="dev touy_on" onclick="openFirstWindow('#touy','[class_id]','close')" style="display:none;" src="public/img/touy_on.png" />
								<img class="dev touy_offline" onclick="openFirstWindow('#touy','[class_id]','close')" style="display:none;" src="public/img/touy_offlines.png" />
								<img class="	 touy_off" onclick="openFirstWindow('#touy','[class_id]','open')" src="public/img/touy_off.png" />
	    					</div>	    						    						 
    					</div>
    					<div style="width:170px;margin-top:15px;margin-bottom:5px;text-align: center;">
			    			<input type="hidden" value="[class_name]" id="[class_id]roomName"/>
			    			<button style="min-width:80px;color:#fff;background:#0000ff;border:0px;padding:5px;" onclick="$(this).hide();$(this).next().show();openFirstWindow('#classRoomWindow','[class_id]');">[class_name]</button> 
			    			<button style="display:none;min-width:80px;color:#fff;background:#0000ff;border:0px;padding:5px;" onclick="$(this).hide();$(this).prev().show();$(this).parent().parent().parent().find('.classes-item-panel').hide();">[class_name]</button>
			    		    <button style="display:none;min-windth:80px;color"/>
			    		    <button style="display:none;min-windth:80px;color"/>
			    		</div>
    				</div>
    				<!-- left devlist end -->
    				<!-- SDK -->
    				<!-- center panel start -->
    				<div class="td" >
    					 <div style="height:200px;width:200px;background: #ccc;display:none;border:1px solid #ccc;" id="videoclass[class_id]" >    					
    						<object type='application/x-vlc-plugin' style="width:100%;height:100%;" onclick="$(this).hide();$(this).prev().show();" pluginspage="http://www.videolan.org/" events='false' >
							    <param name='mrl' value='rtsp://admin:aaa123457@192.168.1.51:554/cam1/h264' />
							    <param name='mrl' value='rtsp://admin:aaa123457@192.168.1.51:554/cam1/h264' />
							    <param name='volume' value='50' />
							    <param name='autoplay' value='false' />
							    <param name='loop' value='false' />
							    <param name='START FULLSCREEN' value='true' />
							    <param name='controls' value='true' />
							    <param name='controls' value='true' />
							    <param name='controls' value='true' />
							    <param name='controls' value='true' />
			</object>
    					</div> 
    				
    				<!-- center panel end -->
    				
    				<!-- right monitor start -->
    				<div class="td classes-item-panel firstWindow" id="classRoomWindow[class_id]" style="display:none;border:1px solid #ccc;">
    					<div class="clabel1">	    			
								<a onmouseout="changeImage(this,'public/img/js/5.png');" onmouseover="changeImage(this,'public/img/js/6.1.png');" class="js_b_o" href="javascript:void(0)" onclick="doAction('batOpenNoAir','[class_id]')" style="margin-left: 20px"><img src="public/img/js/5.png" width="60"></a>
								<a onmouseout="changeImage(this,'public/img/js/7.png');" onmouseover="changeImage(this,'public/img/js/7.1.png');" class="js_b_c" href="javascript:void(0)" onclick="doAction('batCloseNoAir','[class_id]')" style="margin-left: 17px"><img src="public/img/js/7.png" width="60"></a>
								<a onmouseout="changeImage(this,'public/img/js/4.png');" onmouseover="changeImage(this,'public/img/js/4.1.png');" class="js_h_o" href="javascript:void(0)" onclick="doAction('batOpenHasAir','[class_id]')" style="margin-left: 20px"><img src="public/img/js/4.png" width="60"></a>
								<a onmouseout="changeImage(this,'public/img/js/6.png');" onmouseover="changeImage(this,'public/img/js/5.1.png');" class="js_h_c" href="javascript:void(0)" onclick="doAction('batCloseHasAir','[class_id]')"style="margin-left: 17px"><img src="public/img/js/6.png" width="60"></a>
								<center><a onmouseout="changeImage(this,'public/img/js/8.png');" onmouseover="changeImage(this,'public/img/js/8.1.png');" class="sz_ip" href="javascript:void(0)" onclick="javascript:$('#ip-config[class_id]').dialog();" ><img src="public/img/js/8.png" width="110"></a></center>
								<center><a onmouseout="changeImage(this,'public/img/js/8.png');" onmouseover="changgImage(this,'public/img/js/8.1.png');" class="sz_ip" href="javascript:void(0)" onclick="javascript:$('#ip-config[class_id]').dialog();"><img src="public/img/js/8.png" width="110"></a></center>
								<center><a onmouseout="changeImage(this,'public/img/js/9.png');" onmouseover="changeImage(this,'public/img/js/9.1.png');" class="ck_kb" href="javascript:void(0)" onclick="showKeBiao('[class_id]')" ><img src="public/img/js/9.png" width="80"></a></center>
								<center><a onmouseout="changeImage(this,'public/img/js/10.png');" onmouseover="changeImage(this,'public/img/js/10.1.png');" class="sc_js" href="javascript:void(0)" onclick="deleteClass('[class_id]')" ><img src="public/img/js/10.png" width="80"></a>
								<a  onclick="doAction('syncCmd','[class_id]')" ></a></center>
								<a  onclick="doAction('syncCmd','[class_id]')" ></a></div>
    				</div>
    				<!-- right monitor end -->
    				<!-- 空调 start -->
    				<div class="td firstWindow" id="airConditioner[class_id]" style="display:none;border:1px solid #ccc;">
    					<div class="clabel2">    			
								<div class="kt_1">
								<a onmouseout="changeImage(this,'public/img/kt/16.png');" onmouseover="changeImage(this,'public/img/kt/16x.png');" href="javascript:void(0)" onclick="doAction('setAirConditioner16Degrees','[class_id]')" ><img src="public/img/kt/16.png" />
			   					</a>
			   					</div>
			   					<div class="kt_2">
			   					<a onmouseout="changeImage(this,'public/img/kt/23.png');" onmouseover="changeImage(this,'public/img/kt/23x.png');" href="javascript:void(0)" onclick="doAction('setAirConditioner23Degrees','[class_id]')" ><img src="public/img/kt/23.png" />
			   					<a onmouseout="changeImage(this,'public.img/kt/23.png');"></a>
			   					</a>
			   					</div>
			   					<div class="kt_3">
			   					<a onmouseout="changeImage(this,'public/img/kt/26.png');" onmouseover="changeImage(this,'public/img/kt/26x.png');" href="javascript:void(0)" onclick="doAction('setAirConditioner26Degrees','[class_id]')" ><img src="public/img/kt/26.png" /></a>
								</div>
								<div class="kt_gj">
			   					<a onmouseout="changeImage(this,'public/img/kt/gj.png');" onmouseover="changeImage(this,'public/img/kt/gjx.png');" href="javascript:void(0)" onclick="doAction('closeAirConditioner','[class_id]')" ><img src="public/img/kt/gj.png" /></a>								
								</div>
								<div class="kt_sz">
								<a onmouseout="changeImage(this,'public/img/kt/hwm.png');" onmouseover="changeImage(this,'public/img/kt/hwmx.png');" href="JavaScript:void(0)" onclick="javascript:$('#showAirConditionerHongWai[class_id]').dialog();" ><img src="public/img/kt/hwm.png" /></a>
								</div>
						</div>   			
			   				
    				</div>
    				<!-- 空调 end -->
    				<!-- 中控 start -->
    				<div class="td firstWindow" id="cloudTerminal[class_id]" style="display:none;border:1px solid #ccc;">
    					<div class="clabel3">
    						<div class="davzk">    			
								<center><a onmouseout="changeImage(this,'public/img/zk/16.png');" onmouseover="changeImage(this,'public/img/zk/16.1.png');" class="zk_a" href="javascript:void(0)" onclick="doAction('closeCenterCtrl','[class_id]')" ><img src="public/img/zk/16.png" width="80" height="30"/></a></center><br>
								<center><a onmouseout="changeImage(this,'public/img/zk/15.png');" onmouseover="changeImage(this,'public/img/zk/15.1.png');" class="zk_a" href="javascript:void(0)" onclick="javascript:$('#cloudTerminalIP[class_id]').dialog();" ><img src="public/img/zk/15.png" width="100" height="30"/></a></center><br>
								<center><a onmouseout="changeImage(this,'public/img/zk/14.png');" onmouseover="changeImage(this,'public/img/zk/14.1.png');" class="zk_a" href="javascript:void(0)" onclick="showCloudTerminalNote('[class_id]')" ><img src="public/img/zk/14.png" width="150" height="30"/></a><center>
							</div>
						</div>   			
			   				
    				</div>
    				<!-- 中控 end -->
    				<!-- 监控 start -->
    				<div class="td firstWindow"  id="monitor[class_id]" style="display:none;">
    					<div class="clabel4">	
    						<div class="davzks">    			
								<center><a id="jk11" onmouseout="changeImage(this,'public/img/jk/11.png');" onmouseover="changeImage(this,'public/img/jk/11.1.png');" href="javascript:void(0)" onclick="javascript:$('#monitorIP[class_id]').dialog();" ><img src="public/img/jk/11.png" width="120"></a>
										<a id="close_jk" onmouseout="changeImage(this,'public/img/jk/9.1.png');" onmouseover="changeImage(this,'public/img/jk/9.png');" href="javascript:void(0)" display="not" style="display:none;" onclick="closeMonitor($(this),'[class_id]')" ><img src="public/img/jk/9.png" width="80"></a>
										<a id="open_jk" onmouseout="changeImage(this,'public/img/jk/12.png');" onmouseover="changeImage(this,'public/img/jk/12.1.png');" href="javascript:void(0)" onclick="openMonitor($(this),'[class_id]')" ><img src="public/img/jk/12.png" width="80"></a></center>
<!-- 									<a id="open_jk" onmouseout="changeImage(this,public/img.jk/12.png);"onmouseover="changeImage(this,'public/img/jk/12.1.png');" href="javascript:void(0)" onclick=
						</div>   			
			   					
    				</div>
    				<!-- 监控 end -->
    				<!-- 投影 start -->
    				<div class="td firstWindow" id="touy[class_id]" style="display:none;border:1px solid #ccc;">
							<div class="clabel2">
								<a onmouseout="changeImage(this,'public/img/ty/1.png');" onmouseover="changeImage(this,'public/img/ty/1.1.png');" class="davty" href="javascript:void(0)" onclick="javascript:$('#touyControl[class_id]').dialog();" ><img src="public/img/ty/1.png" width="160px"></a>
								<a onmouseout="changeImage(this,'public/img/ty/2.png');" onmouseover="changeImage(this,'public/img/ty/2.1.png');" class="davty_open" href="javascript:void(0)"  onclick="doAction('openTouy','[class_id]')" ><img src="public/img/ty/2.png" width="78px"></a>
								<a onmouseout="changeImage(this,'public/img/ty/3.png');" onmouseover="changeImage(this,'public/img/ty/3.1.png');" class="davty_close" href="javascript:void(0)"  onclick="doAction('closeTouy','[class_id]')" ><img src="public/img/ty/3.png" width="78px"></a>
							</div>
    				</div>
    				<!-- 投影 end -->
    				 
    				 <!--空调红外码 config div -->
    				
    				<div class="td" id="showAirConditionerHongWai[class_id]" closed="true" title ="设置空调红外码" style="width:276px;hight:152px;display:none;border:1px solid #ccc;">
    					<div class="table">	    			
							<div class="row">
								<div class="td controls" style="text-align:center;">
									<div class="input-item">
							    	
							    		<form action="#" id='airConditionerFrom[class_id]'>
							    		<input type="hidden" name="classRoomId" value="[class_id]">
							    		<input type="hidden" name="classRoomId" value="[class_id]">
							    		<input type="hidden" name="classRoomid" value="[class_id]">
							    		<input type="hidden" name="classRoomid" value="[class_id]">
							    		<input type="hidden" name="classRoomid" value="[class_id]">
							    		<input type="hidden" name="classRoomid" value="[class_id]">
							    		<input type="hidden" name="classRoomid" value="[class_id]">
							    		</form>
							    	</div>
								</div>
							</div>   
							<div class="row">
								<div class="td controls" >
								<button class="dev" onclick="updateAirConditionhongwai('[class_id]')" style="background:red;font-size:10px;color:#fff;" >确定</button>
								</div>
							</div>
						</div>   			
			   				
    				</div>
    				<!-- 空调红外码 config div -->
    				<!--投影 ip config div -->
    				
    				<div class="td" id="touyControl[class_id]" closed="true" title ="设置投影类型" style="width:276px;hight:152px;display:none;border:1px solid #ccc;">
    				<div class="td" id="touyControl[class_id]" closed="true" title ="设置投影类型" style="width:276px;height:152px;display:none;border:1px solid #ccc;">
    					<div class="table">	    			
							<div class="row">
								<div class="td controls" style="text-align:center;">
								<div class="td controls" style="text-align:center;">
									<div class="input-item">
							    		<div>设置：</div>
							    		<form action="#" id='touyForm[class_id]'>
							    		<input type="hidden" name="classRoomId" value="[class_id]">
							    		<label>投影类型：</label><select name="touyType" id="touyTypeId[class_id]" style="width:185px;">
							    		<label>投影类型：</label><select name="touyType" id="touTypeId"[class_id]" style="width:178px;">
							    			<option value="A" >A</option>
							    			<option value="B" >B</option>
							    			<option value="D" >D</option>
							    			<option value="W" >W</option>
							    			<option value="C" >C</option>
							    			<option value="F" >F</option>
							    		</select>
<!-- 							    		<label>开机码：</label><input type="text" name="openCode" value='[openCode]'/> -->
<!-- 							    		<label>关机码：</label><input type="text" name="closeCode" value='[closeCode]'/> -->
							    			<label>关机码：</label><input type="text" name="closeCode" value='[closeCode]'/>
							    		</form>
							    	</div>
								</div>
							</div>   
							<div class="row">
								<div class="td controls" >
								<button class="dev" onclick="updateTouy('[class_id]')" style="background:red;font-size:10px;color:#fff;" >确定</button>
								<button class="dev" onclick="updateTouy('[class_id]')" style="background:red;font-size:10px;color:#fff;" >确定</button>
								<button class="dev" onclick="updateTouy('[class_id]')" style="background:red;font-size:20px;color:#fff;" >确定</button>
								<button class="dev" onclick="updateTouy('[class_id]')" style="background:red;font-size:20px;color:#fff;" >确定</button>	
			   					<button class="dev" onclick="updateTouy('[class_id]')" style="background:red;font-size:20px;color:#ooo;" >确定</button>
    				</div>
    			
    				
    					<div class="table">	    			
							<div class="row">
								<div class="td controls" style="text-align:center;">
									<div class="input-item">
							    		<div>教室IP：</div>
							    		<div>教室IP：</div>
							    		<form action="#" id='classroom[class_id]'>
							    		<input type="hidden" name="classRoomId" value="[class_id]">
							    		<input type="text" name="ip" value='[class_ip]'/>
							    		<input type="text" name="ip" value='[class_ip]'/>
							    		<input type="text" name="ip" value='[class_ip]'/>
							    		</form>
							    	</div>
								</div>
							</div>   
							<div class="row">
								<div class="td controls" >
								<button class="dev" onclick="updateClassRoomIp('[class_id]')" style="background:red;font-size:10px;color:#fff;" >确定</button>
								<button class="dev" onclick="updateClassRoomIp('[class_id]')" style="background:red; font-size:10px;color:#fff;">确定</button>
								
								</div>
							</div>
						</div>   			
			   				
    				</div>
    				<!-- 教室 ip config div -->
    				<!--中控 ip config div -->
    				
    				<div class="td" id="cloudTerminalIP[class_id]" closed="true" title ="修改中控IP" style="display:none;border:1px solid #ccc;">
    					<div class="table">	    			
							<div class="row">
								<div class="td controls" style="text-align:center;">
									<div class="input-item">
							    		<div>中控IP：</div>
							    		
							    		<form action="#" id='cloudTerminalIP[class_id]'>
							    		<input type="hidden" name="sRoomId" value="[class_id]">
							    	</div>
								</div>
							</div>   
							<div class="row">
								<div class="td controls" >
								<button class="dev" onclick="updateCloudTerminalIp('[class_id]')" style="background:red;font-size:10px;color:#fff;" >确定</button>
								<button class="dev" onclick="updateCloudTerminalIp('[class_id]')" style="background:red;font-size:20px;color:#fff;">确定</button>  
								</div>
							</div>
						</div>   			
			   				
    				</div>
    				<!-- 中控 ip config div -->
    				
    				<div class="td" id="monitorIP[class_id]" closed="true" title ="修改监控IP" style="display:none;border:1px solid #ccc;">
    						<div class="table">	    			
							<div class="row">
								<div class="td controls" style="text-align:center;">
									<div class="input-item">
							    		<div>监控IP：</div>
							    		<form action="#" id='monitorIP[class_id]'>
							    		<input type="hidden" name="classRoomId" value="[class_id]">
							    		<input type="text" name="ip" value='[touyUrl]'/>
							    		<input type="text" name="ip" value='[touyUrl]'/>
							    		<input type="text" name="ip" value='[touyUrl]'/>
							    		<input type="text" name="ip" value='[touyUrl]'/>
							    		<input type="text" name="ip" value='[touyUrl]'/>
							    		<input type="text" name="ip" value='[touUrl]'/>
							    		</form>
							    	</div>				
								</div>
							</div>   
							
    				<!-- 课表 div -->
    				</div>
    				 </>
    				<div class="td" id="kebiao[class_id]" title ="查看当天课表" style="width:290px;display:none;border:1px solid #ccc;">
    					<div class="table">	    			
							<div class="row">
								<div class="td controls"  style="text-align:center;">
							    	<ul class="input-item" style="width:216px;" id="kebiaoDetail[class_id]" solid #ccc;">
							    	<ul class="input-item" style="width:216px;" id="kebiaoDetail[class_id]" solid #ccc;"></ul>
							    	</ul>
								</div>
							</div>   
							
						</div>   			
			   				
    				</div>
    			</div>
    		</div>    		
    	</div> 
    	<!-- classes-item end  -->
    	
    </div>   
  </div>
  
  </body>
</html>
 