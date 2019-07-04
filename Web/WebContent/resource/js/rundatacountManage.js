/** 运行数据统计入口js */
var webRoot;
(function(){
	$(function(){
		// 打开统计页面点击事件
		$(document).on('click','.m-fTop-center .m-rundatacount-page',function(event){
			$("#class_container").empty();
			$("#class_container").append('<iframe tab-id="page1" frameborder="0" src="'+webRoot+'/runInfo/toPage/rundatacount" scrolling="no" class="x-iframe" width="100%" height="100%"></iframe>');
			$("#menu-container").hide();
		});
		
		// 打开修改额定功率页面点击事件
		$(document).on('click','.m-fTop-center .m-editPower-page',function(event){
			$("#class_container").empty();
			$("#class_container").append('<iframe tab-id="page1" frameborder="0" src="'+webRoot+'/runInfo/toEditPower" scrolling="no" class="x-iframe" width="100%" height="100%"></iframe>');
			$("#menu-container").hide();
		});
		// 打开主页点击事件
		$(document).on('click','.m-fTop-center .m-home-page',function(event){
			$("#class_container").empty();
			$("#class_container").append('<iframe tab-id="page1" frameborder="0" src="'+webRoot+'/runInfo/toHomePage" scrolling="no" class="x-iframe" width="100%" height="100%" style="height:10000px"></iframe>');
			$("#menu-container").hide();
		});
		
		//TODO 空调电闸全开
		$(document).on('click','.open-all-air-sw',function(enent){
			var reqData = {
				'isOpen':true	
			};
			 if(!confirm("确定要开启全部的空调电闸吗？")){
				 return false;
			 }
			 $.ajax({
			        type : "post",
			        url :webRoot + '/runInfo/openOrCloseAllAirSw',
			        data:reqData,
			        success : function(data) {
			        	if(data){
			        		data = JSON.parse(data);
			        	}
			        	if(data && data.status){
			        		alert(data.msg);
			        	} else{
			        		alert("操作失败！")
			        	}
			        }
			 });
		});
		//TODO 空调电闸全关
		$(document).on('click','.close-all-air-sw',function(enent){
			var reqData = {
					'isOpen':false	
				};
			
			if(!confirm("确定要关闭全部的空调电闸吗？")){
				 return false;
			 }
			 $.ajax({
			        type : "post",
			        url :webRoot + '/runInfo/openOrCloseAllAirSw',
			        data:reqData,
			        success : function(data) {
			        	if(data){
			        		data = JSON.parse(data);
			        	}
			        	if(data && data.status){
			        		alert(data.msg);
			        	} else{
			        		alert("操作失败！")
			        	}
			        }
			 });
		});
	});
}).call(this)