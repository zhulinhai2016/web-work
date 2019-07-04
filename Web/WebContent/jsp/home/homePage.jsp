<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>主页</title>
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
<%-- <script src="${pageContext.request.contextPath}/public/js/jquery.js" type="text/javascript" charset="utf-8"></script> --%>
<link href="${pageContext.request.contextPath}/resource/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resource/bootstrapTable/bootstrap-table.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/resource/bootstrap-date/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resource/jquery.min.js" type="text/javascript" charset="utf-8"></script>

<script src="${pageContext.request.contextPath}/public/js/jquery.boxy.js" type="text/javascript"charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/public/js/jquery-ui.min.js" type="text/javascript"charset="utf-8"></script>

<script src="${pageContext.request.contextPath}/resource/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/layer/layer.js" charset="utf-8"></script>

<script src="${pageContext.request.contextPath}/resource/base.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/js/bootstrap.js" type="text/javascript"></script>


<script src="${pageContext.request.contextPath}/resource/bootstrapTable/bootstrap-table.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrapTable/bootstrap-table-zh-CN.js" charset="utf-8"></script>

<script src="${pageContext.request.contextPath}/resource/bootstrap-date/js/bootstrap-datetimepicker.js"  type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap-date/js/locales/bootstrap-datetimepicker.zh-CN.js"  type="text/javascript" ></script>

<script src="${pageContext.request.contextPath}/resource/echarts/source/echarts.js"></script>

<style type="text/css">
.frame-right .classes-item .table{
	background: url(../public/img/22.png) no-repeat;
}
a {
	text-decoration: none;
	outline:none;
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

</script>
</head>
<body>
	<div class="container-full">
		<div class="row">
			<div class="col-sm-12" style="padding-top: 37px;"> 
				<form class="search-form form-inline" role="form">
					<div class="form-group">
						<label for="name1">&nbsp;&nbsp;统计时间：</label>
						<div id="name1" class="input-group date start-date " data-date="2018-07-08 05:25:07" data-date-format="yyyy-MM-dd HH:mm:ss" data-link-field="dtp_input1">
							<input class="form-control show-date" size="32" type="text" value="" readonly>
							<span class="input-group-addon date-remove"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
							<input type="hidden" id="dtp_input1" class="date-hide" name="openTime" value="" />
						</div>
						<label for="name2">至</label>
						<div  id="name2" class="input-group date end-date" data-date="2018-07-08 05:25:07" data-date-format="yyyy-MM-dd HH:mm:ss" data-link-field="dtp_input2">
							<input class="form-control show-date" size="32" type="text" value="" readonly>
		                    <span class="input-group-addon date-remove"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
							<input type="hidden" id="dtp_input2" class="date-hide" name="closeTime" value="" />
		                </div>
					</div>
					<div class="form-group">
						<label for="dtp_input4" class="control-label">&nbsp;&nbsp;选择教室：</label>
			            <select id="dtp_input4" class="form-control" name="classRoomId">
			               <option value="">-- 请选择 --</option>
			            </select>
						<label for="dtp_input5">&nbsp;&nbsp;显示教室数量：</label>
		                <select id="dtp_input5" class="form-control" name="showRowsNum">
		                	<option value="">-- 请选择 --</option>
		                	<option value="5">显示前5</option>
		                	<option value="20" selected="selected">显示前20个</option>
		                	<option value="50">显示前50</option>
		                	<option value="100">显示前100</option>
		                	<option value="200">显示前200</option>
		                	<option value="400">显示前400</option>
		                	<option value="-1">显示全部</option>
		                </select>
					</div>
					<button id="search" type="button" class="btn btn-info">搜&nbsp;&nbsp;索</button>
				</form>
			</div>
		</div> 
		<div class="row">
		  <div class="col-sm-12">
			<ul id="myTab" class="nav nav-tabs">
			    <li class="active">
					<a href="#usingAndNotUse" data-toggle="tab">&nbsp;&nbsp;启用跟未启用</a>
				</li>
				<li>
					<a href="#classRoomRankings" data-toggle="tab">教室使用时长排名</a>
				</li>
				<li>
				   <a href="#deviceRankings" data-toggle="tab">设备运行时长排名</a>
				</li>
	
			</ul>
		</div>
	 </div>
	 <div class="row">
		<div class="col-sm-12">
			<div id="myTabContent" class="tab-content">
			
				<!-- 启用跟未启用 -->
				<div class="tab-pane fade in active" id="usingAndNotUse">
					<div class="row">
						<div id="graphic" class="col-xs-offset-3 col-xs-6">
							<div id="main" class="main" style="width: 100%;height: 500px;"></div>
						</div>
				    </div>
				</div>
				<!-- 教室使用时长排名 -->
				<div class="tab-pane fade in active" id="classRoomRankings" >
					<div class="row">
						<div id="graphic2" class="col-xs-offset-2 col-xs-8">
							<div id="usedPwor" class="main" style="width: 100%;height: 500px;"></div>
						</div>
				    </div>
				</div>
				<!-- 设备使用时长排名 -->
				<div class="tab-pane fade in active" id="deviceRankings">
					<div class="row">
						<div id="graphic3" class="col-xs-offset-3 col-xs-4">
							<div id="errorInfo" class="main" style="width: 100%;height: 500px;"></div>
						</div>
				    </div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<hr/>
	<div class="row">
	<!-- <div class="frame-bottom table"> -->
		<!-- <div class="row"> -->
			<div class="frame-right td" id="class_container"></div>
		<!-- </div> -->
	<!-- </div> -->
	<div id="face" style="display: none;">
		<!-- classes-item start  -->
		<div class="classes-item" id="class[class_id]">
			<div class="table">
				<div class="tr">
					<!-- left devlist start -->
					<div class="td"
						style="width: 215px; height: 215px; margin-left: -5px;">
						<div style="width: 170px; height: 170px;">
							<div class="dev-itea">
								<img class="dev air_conditioner_on"
									style="display: none; margin-bottom: 4px;  "    src="${pageContext.request.contextPath}/public/img/air_conditioner_on.png" alt="空调" title="空调" />
								<img class="dev air_conditioner_offline"
									style="display: none; margin-bottom: 4px; "
									src="${pageContext.request.contextPath}/public/img/air_conditioner_offline.png" alt="空调" title="空调" /> <img
									class="dev air_conditioner_off"
									 style="margin-bottom: 4px; "
									src="${pageContext.request.contextPath}/public/img/air_conditioner_off.png" alt="空调" title="空调" />

							</div>
							<div class="dev-ites">
								<img class="dev air_switch_on"
									style="display: none;" src="${pageContext.request.contextPath}/public/img/air_switch_on.png"  alt="空调电闸" title="空调电闸"/> <img
									class="dev air_switch_offline"
									style="display: none;" src="${pageContext.request.contextPath}/public/img/air_switch_offline.png"  alt="空调电闸" title="空调电闸"/>
								<img class="dev air_switch_off"
									src="${pageContext.request.contextPath}/public/img/air_switch_off.png"  alt="空调电闸" title="空调电闸"/>
							</div>
							<div class="dev-ited">
								<img class="dev other_switch_on"
									style="display: none;" src="${pageContext.request.contextPath}/public/img/other_switch_on.png" alt="其它设备电闸" title="其它设备电闸" />
								<img class="dev other_switch_offline"
									style="display: none;"
									src="${pageContext.request.contextPath}/public/img/other_switch_offline.png" alt="其它设备电闸" title="其它设备电闸"/> <img
									class="dev other_switch_off"
									src="${pageContext.request.contextPath}/public/img/other_switch_off.png" alt="其它设备电闸" title="其它设备电闸"/>
							</div>
							<div class="dev-itef monitor">
								<img class="dev monitor_on"
									style="display: none;" src="${pageContext.request.contextPath}/public/img/monitor_on.png" alt="监控" title="监控"/> <img
									class="dev monitor_offline"
									style="display: none;" src="${pageContext.request.contextPath}/public/img/monitor_offline.png" alt="监控" title="监控" />
								<img class="dev monitor_off"
									src="${pageContext.request.contextPath}/public/img/monitor_off.png" alt="监控" title="监控" />
							</div>
							<div class="dev-iteg">
								<img class="dev cloud_terminal_on"
									style="display: none;" src="${pageContext.request.contextPath}/public/img/cloud_terminal_on.png" alt="中控" title="中控" />
								<img class="dev cloud_terminal_offline"
									style="display: none;"
									src="${pageContext.request.contextPath}/public/img/cloud_terminal_offline.png" alt="中控" title="中控"/> <img
									class="dev cloud_terminal_off"
									src="${pageContext.request.contextPath}/public/img/cloud_terminal_off.png" alt="中控" title="中控"/>
							</div>
							<div class="dev-iteh">
								<img class="dev touy_on"
									style="display: none;" src="${pageContext.request.contextPath}/public/img/touy_on.png" alt="投影" title="投影" /> <img
									class="dev touy_offline"
									style="display: none;" src="${pageContext.request.contextPath}/public/img/touy_offlines.png" alt="投影" title="投影" /> <img
									class="	 touy_off"
									src="${pageContext.request.contextPath}/public/img/touy_off.png" alt="投影" title="投影" />
							</div>
						</div>
						<div
							style="width: 170px; margin-top: 15px; margin-bottom: 5px; text-align: center;">
							<input type="hidden" value="[class_name]" id="[class_id]roomName" />
							<button
								style="min-width: 80px; color: #fff; background: #0000ff; border: 0px; padding: 5px;margin-top:28px;margin-left: 40px;"
								onclick="$(this).hide();$(this).next().show();openFirstWindow('#classRoomWindow','[class_id]');">[class_name]</button>
							<button
								style="display: none; min-width: 80px; color: #fff; background: #0000ff; border: 0px; padding: 5px;margin-top:28px;margin-left: 40;"
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
								<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/js/5.png');"
									onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/js/6.1.png');"
									class="js_b_o" href="javascript:void(0)"
									style="margin-left: 20px"><img src="${pageContext.request.contextPath}/public/img/js/5.png"
									width="60"></a> <a
									onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/js/7.png');"
									onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/js/7.1.png');"
									class="js_b_c" href="javascript:void(0)"
									style="margin-left: 19px"><img src="${pageContext.request.contextPath}/public/img/js/7.png"
									width="60"></a> <a
									onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/js/4.png');"
									onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/js/4.1.png');"
									class="js_h_o" href="javascript:void(0)"
									style="margin-left: 20px"><img src="${pageContext.request.contextPath}/public/img/js/4.png"
									width="60"></a> <a
									onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/js/6.png');"
									onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/js/5.1.png');"
									class="js_h_c" href="javascript:void(0)"
									style="margin-left: 19px"><img src="${pageContext.request.contextPath}/public/img/js/6.png"
									width="60"></a>
								<center>

									<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/js/8.png');"
										onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/js/8.1.png');"
										class="sz_ip" href="javascript:void(0)"
										><img
										src="${pageContext.request.contextPath}/public/img/js/8.png" width="110"></a>
								</center>

								<!--392-401  是另外复制的代码  -->
								<center>

									<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/js/9.png');"
										onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/js/9.1.png');"
										class="ck_kb" href="javascript:void(0)"
										><img
										src="${pageContext.request.contextPath}/public/img/js/9.png" width="80"></a>
								</center>




								<center>
									<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/js/10.png');"
										onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/js/10.1.png');"
										class="sc_js" href="javascript:void(0)"
										><img
										src="${pageContext.request.contextPath}/public/img/js/10.png" width="80"></a> <a
										onclick="doAction('syncCmd','[class_id]')"></a>
								</center>
							</div>

						</div>
						<!-- right monitor end -->
						<!-- 空调 start -->
						<div class="td firstWindow" id="airConditioner[class_id]"
							style="display: none; border: 1px solid #ccc;">
							<div class="clabel2">
								<div class="kt_1">
									<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/16.png');"
										onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/16x.png');"
										href="javascript:void(0)"
										><img
										src="${pageContext.request.contextPath}/public/img/kt/16.png" /> </a>
								</div>
								<div class="kt_2">
									<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/23.png');"
										onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/23x.png');"
										href="javascript:void(0)"
										><img
										src="${pageContext.request.contextPath}/public/img/kt/23.png" /> </a>
								</div>
								<div class="kt_3">
									<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/26.png');"
										onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/26x.png');"
										href="javascript:void(0)"
										><img
										src="${pageContext.request.contextPath}/public/img/kt/26.png" /></a>
								</div>
								<div class="kt_gj">
									<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/gj.png');"
										onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/gjx.png');"
										href="javascript:void(0)"
										><img
										src="${pageContext.request.contextPath}/public/img/kt/gj.png" /></a>
								</div>

								<div class="kt_sz">
									<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/hwm.png');"
										onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/kt/hwmx.png');"
										href="JavaScript:void(0)"
										><img
										src="${pageContext.request.contextPath}/public/img/kt/hwm.png" /></a>
								</div>
							</div>

						</div>
						<!-- 空调 end -->
						<!-- 中控 start -->
						<div class="td firstWindow" id="cloudTerminal[class_id]"
							style="display: none; border: 1px solid #ccc;">
							<div class="clabel3">
								<div class="davzk">
									<center>
										<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/zk/16.png');"
											onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/zk/16.1.png');"
											calss="zk_a" href="javascript:void(0)"
											><img
											src="${pageContext.request.contextPath}/public/img/zk/16.png" width="80" height="30" /></a>
									</center>
									<br>
									<center>
										<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/zk/15.png');"
											onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/zk/15.1.png');"
											calss="zk_a" href="javascript:void(0)"
											><img
											src="${pageContext.request.contextPath}/public/img/zk/15.png" width="100" height="30" /></a>
									</center>
									<br>
									<center>
										<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/zk/14.png');"
											onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/zk/14.1.png');"
											calss="zk_a" href="javascript:void(0)"
											><img
											src="${pageContext.request.contextPath}/public/img/zk/14.png" width="150" height="30" /></a>
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
									

									<center>
										<a id="jk11"
											onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/jk/11.png');"
											onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/jk/11.1.png');"
											href="javascript:void(0)"
											><img
											src="${pageContext.request.contextPath}/public/img/jk/11.png" width="120"></a> <a
											id="close_jk"
											onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/jk/9.1.png');"
											onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/jk/9.png');"
											href="javascript:void(0)" display="not"
											style="display: none;"
											><img
											src="${pageContext.request.contextPath}/public/img/jk/9.png" width="80"></a> <a id="open_jk"
											onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/jk/12.png');"
											onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/jk/12.1.png');"
											href="javascript:void(0)"
											><img
											src="${pageContext.request.contextPath}/public/img/jk/12.png" width="80" id="jk_view"></a>
									</center>
									
								</div>
							</div>

						</div>
						<!-- 监控 end -->
						<!-- 投影 start -->
						<div class="td firstWindow" id="touy[class_id]"
							style="display: none; border: 1px solid #ccc;">
							<div class="clabel2">
								<a onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/ty/1.png');"
									onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/ty/1.1.png');"
									class="davty" href="javascript:void(0)"
									><img
									src="${pageContext.request.contextPath}/public/img/ty/1.png" width="160px"> </a>




								<a id="[class_id]openTouy"
									onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/ty/2.png');"
									onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/ty/2.1.png');"
									class="davty_open" href="javascript:void(0)"
									><img
									src="${pageContext.request.contextPath}/public/img/ty/2.png" width="78px"></a> <a
									id="[class_id]closeTouy"
									onmouseout="changeImage(this,'${pageContext.request.contextPath}/public/img/ty/3.png');"
									onmouseover="changeImage(this,'${pageContext.request.contextPath}/public/img/ty/3.1.png');"
									class="davty_close" href="javascript:void(0)"
									onclick="javascript:doAction('closeTouy','[class_id]');"><img
									src="${pageContext.request.contextPath}/public/img/ty/3.png" width="78px"></a>
							

							</div>
						</div>
						<!-- 投影 end -->

						<!--空调红外码 config div TODO-->

						<div id="showAirConditionerHongWai[class_id]"
							closed="true" title="设置空调红外码" class="kt-type-selected"
							style="width:300px; hight: 152px; display: none; border: 0px solid #ccc;overflow:hidden">
							<div class="table">
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
													<option data-key=3 id="md3"  value="美的">美的</option>
												</select>
												</div>
												<div style="display:inline-block">
												<label style="display:block;margin-bottom:5px;">空调类型B</label>
												<select name="kongtType" id="kongtype2">
													<option selected = "selected" value="default">==请选择==</option>
													<option data-key=0 id="gl0"  value="格力">格力</option>
													<option data-key=3 id="md3"  value="美的">美的</option>
												</select>
												</div>
												</div> 
											</form>
																	
								
								<div class="row">
									<div class="td controls">
										<button class="dev"
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
													<option value="明基">明基</option>
													<option value="NEC">NEC</option>
												</select>
												<!-- 							    		<label>开机码：</label><input type="text" name="openCode" value='[openCode]'/> -->
												<!-- 							    		<label>关机码：</label><input type="text" name="closeCode" value='[closeCode]'/> -->
											</form>
									<!-- 	</div> -->
									</div>
									<div class="row">
									<div class="td controls">
										<button class="dev" 
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
													value='[class_ip]' 
													 style="margin-left:65px;margin-top:5px" />
											</form>
										</div>
										<div class="input-item">
											<div>教室ID：</div>

											<form action="#" id='classroom[class_id]'>
												<input type="hidden" name="id" value="[class_id]"> <input
													type="text" name="class_id" class="class_id"
													value='[classId]'
													style="margin-left:65px;margin-top:5px" />
											</form>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="td controls">
										<button class="dev"
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
													 />
											</form>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="td controls">
										<button class="dev"
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
													class="class_touy" 
													 />
											</form>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="td controls">
										<button class="dev"
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
</div>

	
</body>
<script src="${pageContext.request.contextPath}/resource/js/home/homePage.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/js/home/homePageCount.js" type="text/javascript" charset="utf-8"></script>
</html>

