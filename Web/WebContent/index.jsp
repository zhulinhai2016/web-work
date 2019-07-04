<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userName = (String) session.getAttribute("userName");
	if(userName== null || userName == "" ){
		userName = "admin";
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户控制页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/css/public.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/public/css/boxy.css">
<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/public/css/jquery-ui.min.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/public/css/haha.css" />
<script type="text/javascript">
var webRoot = "<%=request.getContextPath()%>";
</script>
<script src="${pageContext.request.contextPath}/public/js/jquery.js" type="text/javascript" charset="utf-8"></script>


<!-- <link rel="stylesheet" type="text/css" href="pates/bootstrap-select.css">
<link href="pates/bootstrap.min.css" rel="stylesheet">
<script src="pates/jquery.js"></script>
<script type="text/javascript" src="pates/bootstrap-select.js"></script>
<script src="pates/bootstrap.min.js"></script> -->
   
   
<script src="${pageContext.request.contextPath}/public/js/jquery.boxy.js" type="text/javascript"charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/public/js/jquery-ui.min.js" type="text/javascript"charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/public/js/index.js" type="text/javascript" charset="utf-8"></script>

<style type="text/css">
a {
	text-decoration: none;
}

.kt_1 {
	position: relative;
	left: 7px;
	top: 44px;
}

.kt_2 {
	position: relative;
	left: 21px;
	top: -108px;
}

.kt_3 {
	position: relative;
	left: 84px;
	top: -126px;
}

.kt_gj {
	position: relative;
	left: 50px;
	width: 50px;
	top: -233px;
}

.kt_sz {
	position: relative;
	left: 16px;
	top: -182px;
}


		.select {
			position: relative;
			box-sizing:border-box;
			font-size:10px;
			width:280px; 
			margin-top:10px;
		}
		.select *{
			box-sizing:border-box;
		}

		.select .input-container{
			display:none;
		}
		.select-result {
			border: 1px solid #e8e8e8;
			width: 280px;
			padding: 4px 15px 4px 10px;
			position: relative;
			padding-left: 10px;
			height: 30px;
		}

		.select-area {
			display: none;
			position: absolute;
			border: 1px solid #e8e8e8;
			width: 280px;
			padding: 5px 0 0 0;
			margin:0;
			background:#fff;
			max-height: 200px;
    	 	overflow:auto
		}

		.select-option {
			padding: 0 10px;
			height: 30px;
			line-height: 30px;
			list-style: none;
			margin-bottom: 5px;
		}

		.select-option:hover {
			background: #428bca;
			color: #fff;
		}
		
		.kt-type-selected{
			overflow:visible!important;
		}
.selectedItem {
			display: inline-block;
			padding-left: 5px;
			padding-right: 10px;
			/*border: 1px solid gray;*/
			margin-right: 5px;
			border-radius: 10px;
			cursor: pointer;
			line-height: 20px;
			background: #e2e0dd;
		}

	.selectedItem:first-child {
			margin-left: 10px;
			color:#555;
		}

		.selectedItem span.delBtn {
			margin-right: 5px;
			font-size:10px;
		}

		.select-result .arrow {
			position: absolute;
			width: 0px;
			height: 0px;
			border: 5px solid #000;
			border-left-color: transparent;
			border-right-color: transparent;
			border-bottom-color: transparent;
			top: 13px;
			right: 5px;
		}
</style>
<script type="text/javascript">
	function changeImage(obj, path) {
		obj.getElementsByTagName('img')[0].src = path;
	}
	
	$(window)

		$(window).on('load', function () {
			//$('.selectpicker').selectpicker({'selectedText': 'cat'});
		});

</script>
</head>
<body>


	<div class="m-frame-top">
		<div class="m-fTop-left">
			<div class="logo">
				<img class="logo-title" src="public/img/nav/16.2.png" /> <img
					class="logo-bg" src="public/img/nav/jian.png" />
			</div>
		</div>
		<div class="m-fTop-center">
			<%
			
				if (userName== null || userName == "" || userName.equals("admin")) {
			%>
			<div class="m-fTop-item m-building-del">
				<a class="nav_a" href="javascript:void(0)"
					onclick="showBuildDelBox()"><img src="public/img/nav/sclc.png"
					width="60"></a> <a class="nav_a" href="javascript:void(0)"
					onclick="showFloorDelBox()"><img src="public/img/nav/scdl.png"
					width="60"></a>
			</div>
			<div class="m-fTop-item m-building-add">
				<a class="nav_a" href="javascript:void(0)" id="addBuildBtn"><img
					src="public/img/nav/tjdl.png" width="60"></a> <a class="nav_a"
					href="javascript:void(0)" id="addFloorBtn"><img
					src="public/img/nav/tjlc.png" width="60"></a> <a class="nav_a"
					href="javascript:void(0)" id="addClassBtn"><img
					src="public/img/nav/tjjs.png" width="60"></a>
			</div>
			<%
				}
			%>
			<div class="m-fTop-item m-floor-c">
				<a class="nav_a" href="javascript:void(0)"
					onclick="showFloorControlBox(1)"><img
					src="public/img/nav/zck.png" width="60"></a> <a class="nav_a"
					href="javascript:void(0)" onclick="showFloorControlBox(2)"><img
					src="public/img/nav/zcg.png" width="60"></a>
			</div>
			<div class="m-fTop-item m-building-c">
				<a class="nav_a" href="javascript:void(0)"
					onclick="showBuildControlBox(1)"><img
					src="public/img/nav/zdk.png" width="60"></a> <a class="nav_a"
					href="javascript:void(0)" onclick="showBuildControlBox(2)"><img
					src="public/img/nav/zdg.png" width="60"></a>
			</div>
			<div class="m-fTop-item m-all-c">
				<a class="nav_a" href="javascript:void(0)"
					onclick="javascript:if(confirm('您确定要全部开吗')) headAction('openAll','');"><img
					src="public/img/nav/qbk.png" width="60"></a> <a class="nav_a"
					href="javascript:void(0)"
					onclick="javascript:if(confirm('您确定要全部关吗')) headAction('closeAll','')"><img
					src="public/img/nav/qbg.png" width="60"></a>
			</div>
		</div>
		<div class="m-fTop-right">
			<div class="login-user">
				<a href="./login/index.jsp"><img src="public/img/nav/1.12.png"
					width="40"></a>
			</div>
		</div>
	</div>
	<div class="frame-bottom table">
		<div class="row">
			<div id="menu-container" class="frame-left td"></div>
			<div class="frame-right td" id="class_container"></div>
		</div>
	</div>
	<div id="face" style="display: none">
		<!-- 添加大楼模板 start -->
		<div class="addBuildHtml showBoxItem">
			<div class="input-item">
				<div class="input-item_p">大楼名称：</div>
				<div>
					<input class="input-item_inp build_input" type="text" name="name"
						onfocus="checkInputValue($(this),'build')"
						onblur="checkInputValue($(this),'build')"
						oninput="checkInputValue($(this),'build')" />
				</div>
			</div>
			<div class="input-item"
				style="width: 100%; margin-top: 5px; background: #ccc;">
				<button class="addBuildSubmit" style="">提交</button>
			</div>
		</div>
		<!-- 添加大楼模板 end -->

		<!-- 添加楼层模板 start -->
		<div class="addFloorHtml showBoxItem">
			<div class="input-item">
				<div class="input-item_p">教&nbsp;学&nbsp;楼：</div>
				<div>
					<select class="input-item_inp building_select" name="build"></select>
				</div>
			</div>
			<div class="input-item floor">
				<div class="input-item_p">所在楼层：</div>
				<div>
					<input class="input-item_inp floor_select" type="text"
						onfocus="checkInputValue($(this),'floor')"
						onblur="checkInputValue($(this),'floor')"
						oninput="checkInputValue($(this),'floor')" name="floor" />
				</div>
			</div>
			<div class="input-item"
				style="width: 100%; margin-top: 5px; background: #ccc;">
				<button class="addFloorSubmit">提交</button>
			</div>
		</div>
		<!-- 添加楼层模板 end -->

		<!-- 添加班级模板 start -->
		<div class="addClassHtml showBoxItem">
			<div class="input-item">
				<div class="input-item_p">教学楼：</div>
				<div>
					<select class="input-item_inp building_select inputItem"
						name="class_build"></select>
				</div>
			</div>
			<div class="input-item">
				<div class="input-item_p">楼&nbsp;&nbsp;层：</div>
				<div>
					<select class="input-item_inp floor_select inputItem"
						name="class_floor"></select>
				</div>
			</div>
			<div class="input-item clazzIp">
				<div class="input-item_p">教室名：</div>
				<div>
					<input class="input-item_inp inputItem class_select" type="text"
						name="class_name" onfocus="checkInputValue($(this),'class')"
						onblur="checkInputValue($(this),'class')"
						oninput="checkInputValue($(this),'class')" required="required" />
				</div>
			</div>
			<div class="input-item clazzIp">
				<div class="input-item_p">教室IP：</div>
				<div>
					<input class="input-item_inp class_input_ip ipInput ip_input inputItem"
						type="text" name="class_ip"
						onfocus="checkInputValue($(this),'class_IP')"
						onblur="checkInputValue($(this),'class_IP')"
						 required="required" />
				</div>
			</div>
			<!-- <div class="input-item">
	    		<div>总控端口：</div>
	    		<div><input type="text" name="class_port"  /></div>
	    	</div> -->
			<div class="input-item clazzIp">
				<div class="input-item_p">中控IP：</div>
				<div>
					<input class="input-item_inp ipInput ip_input inputItem" type="text"
						name="class_center_ip"
						onfocus="checkInputValue($(this),'class_IP')"
						onblur="checkInputValue($(this),'class_IP')"
						required="required" />
				</div>
			</div>
			<div class="input-item clazzIp">
				<div class="input-item_p">监控IP：</div>
				<div>
					<input class="input-item_inp ipInput ip_input inputItem" type="text"
						name="class_touy"  onfocus="checkInputValue($(this),'class_IP')"
						onblur="checkInputValue($(this),'class_IP')"
						 required="required" />
				</div>
			</div>

			<div class="input-item clazzIp">
				<div class="input-item_p">教室ID：</div>
				<div>
					<input class="input-item_inp  inputItem" type="text"
						name="class_id" onfocus="checkInputValue($(this))"
						onblur="checkInputValue($(this))"
						oninput="checkInputValue($(this))" required="required" />
				</div>
			</div>
			<div class="input-item"
				style="width: 100%; margin-top: 5px; background: #ccc;">
				<button class="addClassSubmit">提交</button>
			</div>
		</div>
		<!-- 添加班级模板 end -->


		<!-- classes-item start  -->
		<div class="classes-item" id="class[class_id]">
			<div class="table">
				<div class="tr">
					<!-- left devlist start -->
					<div class="td" style="width: 215px; height: 215px; margin-left: -5px;">
						<div style="width: 170px; height: 170px;">
							<div class="dev-itea">
								<img class="dev air_conditioner_on"
									onclick="openFirstWindow('#airConditioner','[class_id]','close')"
									style="display: none; margin-bottom: 4px;  "    src="public/img/air_conditioner_on.png" alt="空调" title="空调" />
								<img class="dev air_conditioner_offline"
									onclick="openFirstWindow('#airConditioner','[class_id]','close')"
									style="display: none; margin-bottom: 4px; "
									src="public/img/air_conditioner_offline.png" alt="空调" title="空调" /> <img
									class="dev air_conditioner_off"
									onclick="openFirstWindow('#airConditioner','[class_id]','open')" style="margin-bottom: 4px; "
									src="public/img/air_conditioner_off.png" alt="空调" title="空调" />
								<!-- 	    						<img class="dev air_conditioner_on" onclick="doAction('closeAirConditioner','[class_id]')" style="display:none;" src="public/img/air_conditioner_on.png" /> -->
								<!-- 					    		<img class="dev air_conditioner_offline" onclick="doAction('closeAirConditioner','[class_id]')" style="display:none;" src="public/img/air_conditioner_offline.png" /> -->
								<!-- 					    		<img class="dev air_conditioner_off" onclick="doAction('openAirConditioner','[class_id]')" src="public/img/air_conditioner_off.png" /> -->

							</div>
							<div class="dev-ites">
								<img class="dev air_switch_on"
									onclick="doAction('closeAirSwitch','[class_id]',$(this))"
									style="display: none;" src="public/img/air_switch_on.png"  alt="空调电闸" title="空调电闸"/> <img
									class="dev air_switch_offline"
									onclick="doAction('closeAirSwitch','[class_id]',$(this))"
									style="display: none;" src="public/img/air_switch_offline.png"  alt="空调电闸" title="空调电闸"/>
								<img class="dev air_switch_off"
									onclick="doAction('openAirSwitch','[class_id]',$(this))"
									src="public/img/air_switch_off.png"  alt="空调电闸" title="空调电闸"/>
							</div>
							<div class="dev-ited">
								<img class="dev other_switch_on"
									onclick="doAction('closeOtherSwitch','[class_id]',$(this))"
									style="display: none;" src="public/img/other_switch_on.png" alt="其它设备电闸" title="其它设备电闸" />
								<img class="dev other_switch_offline"
									onclick="doAction('closeOtherSwitch','[class_id]',$(this))"
									style="display: none;"
									src="public/img/other_switch_offline.png" alt="其它设备电闸" title="其它设备电闸"/> <img
									class="dev other_switch_off"
									onclick="doAction('openOtherSwitch','[class_id]')"
									src="public/img/other_switch_off.png" alt="其它设备电闸" title="其它设备电闸"/>
							</div>
							<div class="dev-itef monitor">
								<img class="dev monitor_on"
									onclick="openFirstWindow('#monitor','[class_id]')"
									style="display: none;" src="public/img/monitor_on.png" alt="监控" title="监控"/> <img
									class="dev monitor_offline"
									onclick="openFirstWindow('#monitor','[class_id]')"
									style="display: none;" src="public/img/monitor_offline.png" alt="监控" title="监控" />
								<img class="dev monitor_off"
									onclick="openFirstWindow('#monitor','[class_id]')"
									src="public/img/monitor_off.png" alt="监控" title="监控" />
							</div>
							<div class="dev-iteg">
								<img class="dev cloud_terminal_on"
									onclick="openFirstWindow('#cloudTerminal','[class_id]','close')"
									style="display: none;" src="public/img/cloud_terminal_on.png" alt="中控" title="中控" />
								<img class="dev cloud_terminal_offline"
									onclick="openFirstWindow('#cloudTerminal','[class_id]','close')"
									style="display: none;"
									src="public/img/cloud_terminal_offline.png" alt="中控" title="中控"/> <img
									class="dev cloud_terminal_off"
									onclick="openFirstWindow('#cloudTerminal','[class_id]','open')"
									src="public/img/cloud_terminal_off.png" alt="中控" title="中控"/>
							</div>
							<div class="dev-iteh">
								<img class="dev touy_on"
									onclick="openFirstWindow('#touy','[class_id]','close')"
									style="display: none;" src="public/img/touy_on.png" alt="投影" title="投影" /> <img
									class="dev touy_offline"
									onclick="openFirstWindow('#touy','[class_id]','close')"
									style="display: none;" src="public/img/touy_offlines.png" alt="投影" title="投影" /> <img
									class="	 touy_off"
									onclick="openFirstWindow('#touy','[class_id]','open')"
									src="public/img/touy_off.png" alt="投影" title="投影" />
							</div>
						</div>
						<div
							style="width: 170px; margin-top: 15px; margin-bottom: 5px; text-align: center;">
							<input type="hidden" value="[class_name]" id="[class_id]roomName" />
							<button
								style="min-width: 80px; color: #fff; background: #0000ff; border: 0px; padding: 5px;margin-top:28px;margin-left: 40px;"
								onclick="$(this).hide();$(this).next().show();openFirstWindow('#classRoomWindow','[class_id]');">[class_name]</button>
							<button
								style="display: none; min-width: 80px; color: #fff; background: #0000ff; border: 0px; padding: 5px;margin-top: 28px; margin-left: 40px;"
								onclick="$(this).hide();$(this).prev().show();$(this).parent().parent().parent().find('.classes-item-panel').hide();">[class_name]</button>
						</div>
					</div>
					<!-- left devlist end -->

					<!-- center panel start --> 
					<div class="td">
						<div
							style="height: 180px; width: 200px; background: #ccc; display: none; border: 1px solid #ccc;"
							id="videoclass[class_id]">
							<object type='application/x-vlc-plugin'
								style="width: 100%; height: 100%;"
								onclick="$(this).hide();$(this).prev().show();"
								pluginspage="http://www.videolan.org/" events='false'>
<!-- 								<param name='mrl'value='rtsp://admin:aaa123457@192.168.1.51:554/cam1/h264' /> 
 -->						    <param name='mrl' value='rtsp://admin:mdb401dj@[touyUrl]:554/cam1/h264' />  
                                <!-- <param name='mrl'value='rtsp://admin:aaa123457@192.168.1.50:554/cam1/h264' />  -->
								<param name='volume' value='50' />
								<param name='autoplay' value='false' />
								<param name='loop' value='false' />
								<param name='START FULLSCREEN' value='true' />
								<param name='controls' value='true' />
							</object> 
						</div>

						<!-- center panel end -->

						<!-- right monitor start -->
						<div class="td classes-item-panel firstWindow"
							id="classRoomWindow[class_id]"
							style="display: none; border: 1px solid #ccc;">
							<div class="clabel1">
								<a onmouseout="changeImage(this,'public/img/js/5.png');"
									onmouseover="changeImage(this,'public/img/js/6.1.png');"
									class="js_b_o" href="javascript:void(0)"
									onclick="doAction('batOpenNoAir','[class_id]')"
									style="margin-left: 20px"><img src="public/img/js/5.png"
									width="60"></a> <a
									onmouseout="changeImage(this,'public/img/js/7.png');"
									onmouseover="changeImage(this,'public/img/js/7.1.png');"
									class="js_b_c" href="javascript:void(0)"
									onclick="doAction('batCloseNoAir','[class_id]')"
									style="margin-left: 19px"><img src="public/img/js/7.png"
									width="60"></a> <a
									onmouseout="changeImage(this,'public/img/js/4.png');"
									onmouseover="changeImage(this,'public/img/js/4.1.png');"
									class="js_h_o" href="javascript:void(0)"
									onclick="doAction('batOpenHasAir','[class_id]')"
									style="margin-left: 20px"><img src="public/img/js/4.png"
									width="60"></a> <a
									onmouseout="changeImage(this,'public/img/js/6.png');"
									onmouseover="changeImage(this,'public/img/js/5.1.png');"
									class="js_h_c" href="javascript:void(0)"
									onclick="doAction('batCloseHasAir','[class_id]')"
									style="margin-left: 19px"><img src="public/img/js/6.png"
									width="60"></a>
								<center>
									<%
										if (userName.equals("admin")) {
									%>

									<a style="outline:none" onmouseout="changeImage(this,'public/img/js/8.png');"
										onmouseover="changeImage(this,'public/img/js/8.1.png');"
										class="sz_ip" href="javascript:void(0)"
										onclick="openDialog('#ip-config[class_id]')"><img
										src="public/img/js/8.png" width="110"></a>
									<%
										}
									%>
								</center>

								<!--392-401  是另外复制的代码  -->
								<center>
									<%
										if (userName.equals("user")) {
									%>

									<a onmouseout="changeImage(this,'public/img/js/9.png');"
										onmouseover="changeImage(this,'public/img/js/9.1.png');"
										class="ck_cc" href="javascript:void(0)"
										onclick="showKeBiao('[class_id]')"><img
										src="public/img/js/9.png" width="100" height="35px"></a>
									<%
										}
									%>
								</center>





								<center>

									<%
										if (userName.equals("admin")) {
									%>

									<a onmouseout="changeImage(this,'public/img/js/9.png');"
										onmouseover="changeImage(this,'public/img/js/9.1.png');"
										class="ck_kb" href="javascript:void(0)"
										onclick="showKeBiao('[class_id]')"><img
										src="public/img/js/9.png" width="80"></a>
									<%
										}
									%>
								</center>




								<center>
									<%
										if (userName.equals("admin")) {
									%>

									<a onmouseout="changeImage(this,'public/img/js/10.png');"
										onmouseover="changeImage(this,'public/img/js/10.1.png');"
										class="sc_js" href="javascript:void(0)"
										onclick="javascript:if(confirm('确定要删除教室吗')) deleteClass('[class_id]');"><img
										src="public/img/js/10.png" width="80"></a> <a
										onclick="doAction('syncCmd','[class_id]')"></a>
									<%
										}
									%>
								</center>
							</div>

						</div>
						<!-- right monitor end -->
						<!-- 空调 start -->
						<div class="td firstWindow" id="airConditioner[class_id]"
							style="display: none; border: 1px solid #ccc;">
							<div class="clabel2">
								<div class="kt_1">
									<a onmouseout="changeImage(this,'public/img/kt/16.png');"
										onmouseover="changeImage(this,'public/img/kt/16x.png');"
										href="javascript:void(0)"
										onclick="doAction('setAirConditioner16Degrees','[class_id]',$(this))"><img
										src="public/img/kt/16.png" /> </a>
								</div>
								<div class="kt_2">
									<a onmouseout="changeImage(this,'public/img/kt/23.png');"
										onmouseover="changeImage(this,'public/img/kt/23x.png');"
										href="javascript:void(0)"
										onclick="doAction('setAirConditioner23Degrees','[class_id]',$(this))"><img
										src="public/img/kt/23.png" /> </a>
								</div>
								<div class="kt_3">
									<a onmouseout="changeImage(this,'public/img/kt/26.png');"
										onmouseover="changeImage(this,'public/img/kt/26x.png');"
										href="javascript:void(0)"
										onclick="doAction('setAirConditioner26Degrees','[class_id]',$(this))"><img
										src="public/img/kt/26.png" /></a>
								</div>
								<div class="kt_gj">
									<a onmouseout="changeImage(this,'public/img/kt/gj.png');"
										onmouseover="changeImage(this,'public/img/kt/gjx.png');"
										href="javascript:void(0)"
										onclick="doAction('closeAirConditioner','[class_id]',$(this))"><img
										src="public/img/kt/gj.png" /></a>
								</div>
								<%
									if (userName.equals("admin")) {
								%>

								<div class="kt_sz">
									<a onmouseout="changeImage(this,'public/img/kt/hwm.png');"
										onmouseover="changeImage(this,'public/img/kt/hwmx.png');"
										href="JavaScript:void(0)"
										onclick="openDialog('#showAirConditionerHongWai[class_id]');"><img
										src="public/img/kt/hwm.png" /></a>
								</div>
								<%
									}
								%>
							</div>

						</div>
						<!-- 空调 end -->
						<!-- 中控 start -->
						<div class="td firstWindow" id="cloudTerminal[class_id]"
							style="display: none; border: 1px solid #ccc;">
							<div class="clabel3">
								<div class="davzk">
									<center>
										<a onmouseout="changeImage(this,'public/img/zk/16.png');"
											onmouseover="changeImage(this,'public/img/zk/16.1.png');"
											calss="zk_a" href="javascript:void(0)"
											onclick="doAction('closeCenterCtrl','[class_id]')"><img
											src="public/img/zk/16.png" width="80" height="30" /></a>
									</center>
									<br>
									<%
										if (userName.equals("admin")) {
									%>
									<center>
										<a onmouseout="changeImage(this,'public/img/zk/15.png');"
											onmouseover="changeImage(this,'public/img/zk/15.1.png');"
											calss="zk_a" href="javascript:void(0)"
											onclick="openDialog('#cloudTerminalIP[class_id]');"><img
											src="public/img/zk/15.png" width="100" height="30" /></a>
									</center>
									<br>
									<%
										}
									%>
									<center>
										<a onmouseout="changeImage(this,'public/img/zk/14.png');"
											onmouseover="changeImage(this,'public/img/zk/14.1.png');"
											calss="zk_a" href="javascript:void(0)"
											onclick="openDialog('#touyText[class_id]');"><img
											src="public/img/zk/14.png" width="150" height="30" /></a>
										<center>
								</div>
							</div>

						</div>
						<!-- 中控 end -->
						<!-- 监控 start -->
						<div class="td firstWindow" id="monitor[class_id]"
							style="display: none;">
							<div class="clabel4">
								<div class="davzks">
									<%
										if (userName.equals("admin")) {
									%>

									<center>
										<a id="jk11"
											onmouseout="changeImage(this,'public/img/jk/11.png');"
											onmouseover="changeImage(this,'public/img/jk/11.1.png');"
											href="javascript:void(0)"
											onclick="openDialog('#monitorIP[class_id]');"><img
											src="public/img/jk/11.png" width="120"></a> <a
											id="close_jk"
											onmouseout="changeImage(this,'public/img/jk/9.1.png');"
											onmouseover="changeImage(this,'public/img/jk/9.png');"
											href="javascript:void(0)" display="not"
											style="display: none;"
											onclick="closeMonitor($(this),'[class_id]')"><img
											src="public/img/jk/9.png" width="80"></a> <a id="open_jk"
											onmouseout="changeImage(this,'public/img/jk/12.png');"
											onmouseover="changeImage(this,'public/img/jk/12.1.png');"
											href="javascript:void(0)"
											onclick="openMonitor($(this),'[class_id]')"><img
											src="public/img/jk/12.png" width="80" id="jk_view"></a>
									</center>
									<%
										}
									%>
								</div>
								<!-- 							<div class="row"> -->
								<!-- 								<div class="td clabel">监控图像全屏</div> -->
								<!-- 								<div class="td controls"> -->
								<!-- 								<button class="dev" onclick="openMonitor($(this))" >监控图像全屏</button> -->
								<!-- 								</div> -->
								<!-- 							</div>    -->
							</div>

						</div>
						<!-- 监控 end -->
						<!-- 投影 start -->
						<div class="td firstWindow" id="touy[class_id]"
							style="display: none; border: 1px solid #ccc;">
							<div class="clabel2">
								<%
									if (userName.equals("admin")) {
								%>
								<a onmouseout="changeImage(this,'public/img/ty/1.png');"
									onmouseover="changeImage(this,'public/img/ty/1.1.png');"
									class="davty" href="javascript:void(0)"
									onclick="openDialog('#touyControl[class_id]');"><img
									src="public/img/ty/1.png" width="160px"> </a>
								<%
									}
								%>




								<%
									if (userName.equals("admin")) {
								%>
								<a id="[class_id]openTouy"
									onmouseout="changeImage(this,'public/img/ty/2.png');"
									onmouseover="changeImage(this,'public/img/ty/2.1.png');"
									class="davty_open" href="javascript:void(0)"
									onclick="javascript:doAction('openTouy','[class_id]');"><img
									src="public/img/ty/2.png" width="78px"></a> <a
									id="[class_id]closeTouy"
									onmouseout="changeImage(this,'public/img/ty/3.png');"
									onmouseover="changeImage(this,'public/img/ty/3.1.png');"
									class="davty_close" href="javascript:void(0)"
									onclick="javascript:doAction('closeTouy','[class_id]');"><img
									src="public/img/ty/3.png" width="78px"></a>
								<%
									}
								%>

								<%
									if (userName.equals("user")) {
								%>
								<a id="[class_id]openTouy"
									onmouseout="changeImage(this,'public/img/ty/2.png');"
									onmouseover="changeImage(this,'public/img/ty/2.1.png');"
									class="davty_open1" href="javascript:void(0)"
									onclick="javascript:doAction('openTouy','[class_id]');"><img
									src="public/img/ty/2.png" width="78px"></a> <a
									id="[class_id]closeTouy"
									onmouseout="changeImage(this,'public/img/ty/3.png');"
									onmouseover="changeImage(this,'public/img/ty/3.1.png');"
									class="davty_close1" href="javascript:void(0)"
									onclick="javascript:doAction('closeTouy','[class_id]');"><img
									src="public/img/ty/3.png" width="78px"></a>
								<%
									}
								%>


							</div>
						</div>
						<!-- 投影 end -->

						<!--空调红外码 config div TODO-->

						<div id="showAirConditionerHongWai[class_id]"
							closed="true" title="设置空调红外码" class="kt-type-selected"
							style="width:300px; hight: 152px; display: none; border: 0px solid #ccc;overflow:hidden">
							<div class="table">
								<!-- <div class="rows">
									<div class="td controls" style="text-align: center;"> -->
									  <div>设置空调类型：</div> 
										<!-- <div class="input-item"> -->
											<form action="#" id='airConditionerFrom[class_id]'>
												<input type="hidden" name="classRoomId" value="[class_id]">
										   	<div class="select">
												<div style="display:inline-block;margin-right:20px">
												<label style="display:block;margin-bottom:5px">空调类型A</label>
												<select name="kongtType" id="kongtype1">
													<option selected = "selected" value="default">==请选择==</option>
													<option data-key=0 id="gl0"  value="格力">格力</option>
													<!-- <option data-key=1 id="gl1"  value="格力">格力</option>
													<option data-key=2 id="md2"  value="美的">美的</option> -->
													<option data-key=3 id="md3"  value="美的">美的</option>
												</select>
												</div>
												<div style="display:inline-block">
												<label style="display:block;margin-bottom:5px;">空调类型B</label>
												<select name="kongtType" id="kongtype2">
													<option selected = "selected" value="default">==请选择==</option>
													<option data-key=0 id="gl01"  value="格力B">格力</option>
													<!-- <option data-key=1 id="gl1"  value="格力">格力</option>
													<option data-key=2 id="md2"  value="美的">美的</option> -->
													<option data-key=3 id="md31"  value="美的B">美的</option>
													
												</select>
												</div>
												</div> 
									<!--   <div class="select">
								
										<div class="select-result">
											<div class="arrow"></div>
										</div>
										<ul class="select-area">
											<li class="select-option" data-val="格力_0" data-key=0>格力</li>
											<li class="select-option" data-val="格力_1" data-key=1>格力</li>
											<li class="select-option" data-val="美的_2" data-key=2>美的</li>
											<li class="select-option" data-val="美的_3" data-key=3>美的</li>
										
										</ul>
										<div class="input-container">
										 <input name="kongtType" type="checkbox" data-key=0 id="gl0" value="格力_0" [class_id]glnochecked/>
										 <input name="kongtType" type="checkbox" data-key=1 id="gl1" value="格力_1" [class_id]glnochecked/>
										 <input name="kongtType" type="checkbox" data-key=2 id="md2" value="美的_2" [class_id]mdnochecked/>
										 <input name="kongtType" type="checkbox" data-key=3 id="md3" value="美的_3" [class_id]mdnochecked/>
									</div>
									</div>-->
				
								<!-- 
									  <input name="kongtType" type="checkbox" id="gl" class="int1" value="格力" [class_id]glnochecked/>格力 <br>
													<input name="kongtType" type="checkbox" id="gl" class="int1" value="格力" [class_id]glnochecked/>格力 
													
													 <input name="kongtType" type="checkbox" id="gl" class="int16" value="格力" [class_id]glnochecked/>格力 
													<p style="margin-top:-38px; margin-left:160px; "><input name="kongtType" type="checkbox" id="md" class="int3"  value="美的" [class_id]mdnochecked/>美的</p>
                                                   <p style="margin-top:-13px;margin-left:160px;"> <input name="kongtType" type="checkbox" id="md" class="int5"  value="美的" [class_id]mdnochecked/>美的</p>
                                                   -->
											</form>
																	
								
								<div class="row">
									<div class="td controls">
										<button class="dev"
											onclick="updateAirConditionhongwai('[class_id]')"
											style="background: red; font-size: 10px; color: #fff;float: right;height: 25px;width: 45px;">确定</button>
									</div>
								</div>
							</div>

						</div> 
						<!-- 空调红外码 config div -->
						<!--投影 ip config div -->

						<div class="td" id="touyControl[class_id]" closed="true"
							title="设置投影类型"
							style="width: 276px; hight: 152px; display: none; ">
							<div class="table">
								<!-- <div class="row">
									<div class="td controls" style="text-align: center;"> -->
										 <div class="input-item"> 
											<div class="sz">设置投影类型：</div>

											<form action="#" id='touyForm[class_id]'>
												<input type="hidden" name="classRoomId" value="[class_id]">
												<!-- <label>投影类型：</label> --><select name="touyType" class="touy_class"
													id="touyTypeId[class_id]" style="width: 190px; height:25px; margin-top:10px;">
													<option value="爱普生">爱普生</option>
													<option value="NEC">NEC</option>
													<option value="松下UX363C">松下UX363C</option>
													<option value="松下UX35">松下UX35</option>
													<option value="三洋">三洋</option>
													<option value="卡西欧">卡西欧</option>
												</select>
												<!-- 							    		<label>开机码：</label><input type="text" name="openCode" value='[openCode]'/> -->
												<!-- 							    		<label>关机码：</label><input type="text" name="closeCode" value='[closeCode]'/> -->
											</form>
									<!-- 	</div> -->
									</div>
									<div class="row">
									<div class="td controls">
										<button class="dev" onclick="updateTouy('[class_id]',$(this))"
											style="background: red;  font-size: 10px;color: #fff;margin-left:235px; height: 25px;width: 45px;">确定</button>
									</div>
								</div>
								</div>
								
							</div>

						</div>
						<!-- 投影 ip config div -->
						
						<!--教室 ip config div -->

						<div class="td" id="ip-config[class_id]" closed="true"
							title="修改教室IP和ID" style="display: none;">
							<div class="table">
								<div class="row">
									<div class="td controls" style="text-align: center;">
										<div class="input-item"> 
											<div>教室IP：</div>

											<form action="#" id='classroom[class_id]'>
												<input type="hidden" name="id" value="[class_id]"> <input
													type="text" name="class_ip" class="class_ip"
													value='[class_ip]' onfocus="checkInputValue($(this),'ip')"
													onblur="checkInputValue($(this),'ip')"
													 style="margin-left:65px;margin-top:5px" />
											</form>
										</div>
										<div class="input-item">
											<div>教室ID：</div>

											<form action="#" id='classroom[class_id]'>
												<input type="hidden" name="id" value="[class_id]"> <input
													type="text" name="class_id" class="class_id"
													value='[classId]' onfocus="checkInputValue($(this))"
													onblur="checkInputValue($(this))"
													style="margin-left:65px;margin-top:5px" />
											</form>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="td controls">
										<button class="dev"
											onclick="updateClassRoomIp('[class_id]',$(this))"
											style="background: red; font-size: 10px; color: #fff;">确定</button>
									</div>
								</div>
							</div>

						</div>
						<!-- 教室 ip config div -->
						<!--中控 ip config div -->

						<div class="td" id="cloudTerminalIP[class_id]" closed="true"
							title="修改中控IP" style="display: none; border: 1px solid #ccc;">
							<div class="table">
								<div class="row">
									<div class="td controls" style="text-align: center;">
										<div class="input-item">
											<div>中控IP：</div>

											<form action="#" id='cloudTerminalIP[class_id]'>
												<input type="hidden" name="id" value="[class_id]"> <input
													type="text" name="class_center_ip" style="margin-top:5px" class="class_center"
													value='[center_host]'
													onfocus="checkInputValue($(this),'ip')"
													onblur="checkInputValue($(this),'ip')"
													 />
											</form>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="td controls">
										<button class="dev"
											onclick="updateCloudTerminalIp('[class_id]',$(this))"
											style="background: red; font-size: 10px; color: #fff;">确定</button>
									</div>
								</div>
							</div>

						</div>
						<!-- 中控 ip config div -->

						<div class="td" id="monitorIP[class_id]" closed="true"
							title="修改监控IP" style="display: none; border: 1px solid #ccc;">
							<div class="table">
								<div class="row">
									<div class="td controls" style="text-align: center;">
										<div class="input-item">
											<div>监控IP：</div>

											<form action="#" id='monitorIP[class_id]'>
												<input type="hidden" name="id" value="[class_id]"> <input
													type="text" name="class_touy" style="margin-top:5px" value='[touyUrl]'
													class="class_touy" onfocus="checkInputValue($(this),'ip')"
													onblur="checkInputValue($(this),'ip')"
													 />
											</form>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="td controls">
										<button class="dev"
											onclick="updateMonitorIp('[class_id]',$(this))"
											style="background: red; font-size: 10px; color: #fff;">确定</button>
									</div>
								</div>
							</div>

						</div>
						<!-- 课表 div -->

						<div class="td" id="kebiao[class_id]" title="查看当天课表"
							style="width: 290px; display: none; border: 1px solid #ccc;">
							<div class="table">
								<div class="row">
									<div class="td controls" style="text-align: center;">
										<ul class="input-item" style="width: 216px;"
											id="kebiaoDetail[class_id]">
										</ul>
									</div>
								</div>

							</div>

						</div>

						<!-- 向投影发送文字窗口 -->
						<div class="td" id="touyText[class_id]" title="发送文字"
							style="width: 290px; display: none; border: 1px solid #ccc;">
							<div class="table">
								<div class="row">
									<div class="td controls" style="text-align: center;">
										<div class="input-item">
											<div>
											   <label >文字内容：</label>
											 </div>

											<form action="#" id='sendTouyTextForm[class_id]'>
												<input type="text" style="margin-top:5px" id="touyTextInfo[class_id]"
													name="touyText" />
											</form>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="td controls">
										<button class="dev" onclick="sendTouyText('[class_id]')"
											style="background: red; font-size: 10px; color: #fff;">发送</button>
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
