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
<link href="${pageContext.request.contextPath}/resource/bootstrap-date/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resource/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resource/base.js"></script>
<script src="${pageContext.request.contextPath}/resource/bootstrap/js/bootstrap.js" type="text/javascript"></script>

<body>
	<div class="container-full">
		<div style="height:20px; "></div>
		<form class="form-horizontal power-form" role="form">
			<div class="form-group">
				<label for="powerNumber1" class=" col-sm-offset-2 col-sm-2 control-label">中控额定功率</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="powerNumber1" name="powerNumber1" value="${model.powerNumber1}" placeholder="请输入整数（w）">
				</div>
			</div>
			<!-- </div type="text" class="form-control" id="powerNumber1" -->
			<div class="form-group">
				<label for="powerNumber2" class="col-sm-offset-2 col-sm-2  control-label">投影额定功率</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="powerNumber2" name="powerNumber2" value="${model.powerNumber2}" placeholder="请输入整数（w）">
				</div>
			</div>
			<div class="form-group">
				<label for="powerNumber3" class="col-sm-offset-2 col-sm-2  control-label">空调额定功率</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="powerNumber3" name="powerNumber3" value="${model.powerNumber3}" placeholder="请输入整数（w）">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-4">
					<button type="button" class="btn btn-success save-bt">提&nbsp;&nbsp;&nbsp;&nbsp;交</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	var webRoot = "<%=request.getContextPath()%>";
	$(function(){
		$('.save-bt').click(function(event){
			event.preventDefault();
			event.stopPropagation();
			var reqData = $(".power-form").serializeObject();
			$.ajax({
				type:'post',
				url:webRoot+"/runInfo/editPower",
				data:reqData,
				success:function(data){
					if(data || data == 1){
						alert('修改成功！');
					}else{
						alert('修改失败！');
					}
					
				}
			});
		});
		
	});
</script>
</html>