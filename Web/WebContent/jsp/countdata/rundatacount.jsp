<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户控制页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="${pageContext.request.contextPath}/resource/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resource/bootstrapTable/bootstrap-table.css" rel="stylesheet" type="text/css">


<script src="${pageContext.request.contextPath}/resource/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<link href="${pageContext.request.contextPath}/resource/bootstrap-date/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resource/lib/layui/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/layer/layer.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/base.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrapTable/bootstrap-table.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrapTable/bootstrap-table-zh-CN.js" charset="utf-8"></script>

<script src="${pageContext.request.contextPath}/resource/bootstrap-date/js/bootstrap-datetimepicker.js"  type="text/javascript" ></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap-date/js/locales/bootstrap-datetimepicker.zh-CN.js"  type="text/javascript" ></script>

<script src="${pageContext.request.contextPath}/resource/echarts/source/echarts.js"></script>
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
						<label for="dtp_input4" class="control-label">&nbsp;&nbsp;统计年份：</label>
			            <div id="dtp_input4" class="input-group date create-year " data-date="2018" data-date-format="yyyy"  data-link-field="dtp_input3" data-link-format="yyyy">
		                    <input class="form-control show-date" size="16" type="text" value="" readonly>
		                    <span class="input-group-addon date-remove"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
							<input type="hidden" id="dtp_input3" class="date-hide" name="createYear" value="" />
		                </div>
						<label for="dtp_input5">&nbsp;&nbsp;故障类型：</label>
		                <select id="dtp_input5" class="form-control" name="errorType">
		                	<option value="1" selected="selected">远程故障</option>
		                	<option value="3">断网故障</option>
		                </select>
					</div>
					<button id="search" type="button" class="btn btn-success">搜&nbsp;&nbsp;索</button>
				</form>
				<!-- 
					<form class="form-horizontal search-form" role="form">
						<div class="form-group">
							<label for="dtp_input1" class="col-sm-1 control-label">统计时间：</label>
							<div class="col-sm-2">
				                <div class="input-group date start-date " data-date="2018-07-08 05:25:07" data-date-format="yyyy-MM-dd HH:mm:ss" data-link-field="dtp_input1">
				                    <input class="form-control show-date" size="32" type="text" value="" readonly>
				                    <span class="input-group-addon date-remove"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
									<input type="hidden" id="dtp_input1"  class="date-hide" name="openTime" value="" />
				                </div>
							</div>
							<div class="col-sm-1" style="width: 5px; padding-left: 0;line-height: 34px;height: 34px;"><span>至</span></div>
							<div class="col-sm-2">
				               	<div class="input-group date end-date" data-date="2018-07-08 05:25:07" data-date-format="yyyy-MM-dd HH:mm:ss" data-link-field="dtp_input2">
									<input class="form-control show-date" size="32" type="text" value="" readonly>
				                    <span class="input-group-addon date-remove"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
									<input type="hidden" id="dtp_input2" class="date-hide" name="closeTime" value="" />
				                </div>
							</div>
							
							<label for="dtp_input3" class="col-sm-1 control-label">统计年份：</label>
							<div class="col-sm-2">
				                <div class="input-group date create-year " data-date="2018" data-date-format="yyyy"  data-link-field="dtp_input3" data-link-format="yyyy">
				                    <input class="form-control show-date" size="16" type="text" value="" readonly>
				                    <span class="input-group-addon date-remove"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
									<input type="hidden" id="dtp_input3" class="date-hide" name="createYear" value="" />
				                </div>
							</div>
							<label for="dtp_input4" class="col-sm-1 control-label">故障类型：</label>
							<div class="col-sm-1">
				                <select id="dtp_input4" class="form-control" name="errorType">
				                	<option value="1" selected="selected">远程故障</option>
				                	<option value="3">断网故障</option>
				                </select>
							</div>
							<div class="form-group">
            
							<div class="col-sm-1">
								<a title="搜索" id="search" class="btn btn-success btn-lg" style="padding: 6px 12px;">
								  <span class="glyphicon glyphicon-search" aria-hidden="true">搜索</span>
								</a>
							</div>
						</div>
					</form> -->
				</div>
			</div> 
			<div class="row">
				<div class="col-xs-12">
				
			
				
				
				
					<div class="row">
						<div id="graphic" class="col-md-4" style=" border-left: 2px solid #00000012;
						    border-right: 2px solid #eee;
						    border-top: 3px solid #fdd49a;
						    border-bottom: 2px solid #eee;
						    /* border: 1px solid #f1f1f1; */
						   
						    margin-left: 18px;
						    background-color: #e3e2e257;">
							<div id="main" class="main" style="width: 100%;height: 500px;"></div>
						</div>
						<div id="graphic2" class="col-md-4" style="border-left: 2px solid #00000012;
						    border-right: 2px solid #eee;
						    border-top: 3px solid #fdd49a;
						    border-bottom: 2px solid #eee;
						    /* border: 1px solid #f1f1f1; */
						    margin-left: 16px;
						    background-color: #eeeeee4f;">
							<div id="usedPwor" class="main" style="width: 100%;height: 500px;"></div>
						</div>
						<div id="graphic3" class="col-md-4" style="border-left: 2px solid #00000012;
						    border-right: 2px solid #eee;
						    border-top: 3px solid #fdd49a;
						    border-bottom: 2px solid #eee;
						    /* border: 1px solid #f1f1f1; */
						    margin-left: 16px;
						    background-color: #fdd49a14;">
							<div id="errorInfo" class="main" style="width: 100%;height: 500px;"></div>
						</div>
					</div>
				</div>
			</div>
	<div class="row" style="border-top: 2px solid  #99999982; padding-bottom: 10px; margin-top: 50px;"></div>		
	<!-- 主页分页查询 TODO -->
	<div class="rows" style="margin-top: 30px;">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12"> 
				<form class="search-form form-inline" role="form">
					<div class="form-group">
						<label for="name3">&nbsp;&nbsp;中断时间：</label>
						<div id="name3" class="input-group date start-date3 " data-date="2018-07-08 05:25:07" data-date-format="yyyy-MM-dd HH:mm:ss" data-link-field="dtp_input3">
							<input class="form-control show-date" size="32" type="text" value="" readonly>
							<span class="input-group-addon date-remove"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
							<input type="hidden" id="createDate" class="date-hide" name="createDate" value="" />
						</div>
						<label for="name4">至</label>
						<div  id="name4" class="input-group date end-date3" data-date="2018-07-08 05:25:07" data-date-format="yyyy-MM-dd HH:mm:ss" data-link-field="dtp_input4">
							<input class="form-control show-date" size="32" type="text" value="" readonly>
		                    <span class="input-group-addon date-remove"><span class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
							<input type="hidden" id="recoveryTime" class="date-hide" name="recoveryTime" value="" />
		                </div>
					</div>
					<div class="form-group">
						<label class="control-label">&nbsp;&nbsp;设备名称：</label>
			          	<input id="deviceName" class="form-control" name="deviceName" value=""/>
					</div>
					<button id="search2" type="button" class="btn btn-info">搜&nbsp;&nbsp;索</button>
				</form>
				</div>
			</div> 
			
				
					
				
			<div class="row">
				<div class="col-xs-12">
					<div id="content">
						<div id="content-body" style=" height:200px;">
							<div id="pageTableDiv" class="span10">
								<table id="pageTable">
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		</div>
		<div style="height: 50px;"></div>
</body>
<script type="text/javascript">
	var webRoot = "<%=request.getContextPath()%>";
	
</script>
<script src="${pageContext.request.contextPath}/resource/js/rundatacount.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/home/homePageErrorInfo.js" type="text/javascript" charset="utf-8"></script>

</html>