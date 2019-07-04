/** 主页 -分页查询js */
var webRoot;

$(function () {
	
	// 初始化日期组件
	$('.start-date3').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
        format: 'yyyy-mm-dd hh:ii:ss' 
    });
	$('.end-date3').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
        format: 'yyyy-mm-dd hh:ii:ss' 
    });
	
//	$(".date-remove").unbind("click");
//	$('.date-remove').click(function(){
//		$(this).parent().find('.show-date').val('');
//		$(this).parent().parent().find('.date-hide').val('');
//	});
	
	
	initTab();
	$("#search2").click(function(){
		initTab();
	});
	$(window).resize(function() {
		$('#pageTable').bootstrapTable('resetView');
	});
});

function getDelRows(tableId){
	var rows = $("#"+tableId).bootstrapTable('getSelections');
	if(rows==null || rows.length < 1){
		layer.msg('请至少选中一行！', {icon: 5});
		 return false;
	}else if (rows.length >= 1){
		return rows;
	}else{
		layer.msg('操作失败！', {icon: 2});
		return false;	
	}
}
function getRowData(){
	var rows= $('#pageTable').bootstrapTable('getSelections');
	var id = "";
	if(rows.length==1){
		id = rows[0].wifiId;
		return id;
	}else{
		 layer.msg('请选中一行！', {icon: 5});
		 return false;
	}
}
function initTab(){
	$('#pageTable').bootstrapTable('destroy');  
	var t = $('#pageTable').bootstrapTable({
		method: 'get',
		cache: false,
		height: "auto",
		striped: true,
		pagination: true,
		pageSize: 5,
		pageNumber:1,
		pageList: [5,10, 20, 50, 100, 200, 500],
		clickToSelect: true,
		sidePagination:'server',
		idField:'runErrorId',
		columns: [{checkbox:true},
		{field:"runErrorId",title:"主键ID",visible:false,align:"center",valign:"middle",sortable:"true"},
		{field:"classRoomId",title:"教室ID",visible:false,align:"center",valign:"middle",sortable:"true"},
		{field: 'SerialNumber',title: '序号',formatter: function (value, row, index) {
			return index+1;}
		},
		{field:"deviceName",title:"设备名称",align:"center",valign:"middle",sortable:"true"},
		{field:"className",title:"设备归属",align:"center",valign:"middle",sortable:"true"},
		{field:"createDate",title:"中断时间",align:"center",valign:"middle",sortable:"true"},
		{field:"recoveryTime",title:"恢复时间",align:"center",valign:"middle",sortable:"true"},
		{field:"errorTimeStr",title:"中断时长",align:"center",valign:"middle",sortable:"true",formatter:function(value, row, index){
			if(!row.recoveryTime || row.recoveryTime == null || row.recoveryTime == ""){
				return null;
			}
			return value;
		}}
		],
		url:webRoot+"/runInfo/findPage",
		queryParams: function queryParams(params) {   //设置查询参数  
          var param = {    
        	  createDate : $("#createDate").val()==null||$("#createDate").val()==""?null:$("#createDate").val(),
        	  recoveryTime : $("#recoveryTime").val()==null||$("#recoveryTime").val()==""?null:$("#recoveryTime").val(),
        	  deviceName : $("#deviceName").val()==null||$("#deviceName").val()==""?"":$("#deviceName").val()		  
          };    
          return param;                   
        }, 
		formatNoMatches : function() {
			return '无符合条件的记录';
		},
		pagination : true
	});
}