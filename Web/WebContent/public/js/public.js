jQuery.browser = {};(function() {
	jQuery.browser.msie = false;
	jQuery.browser.version = 0;
	if (navigator.userAgent.match(/MSIE ([0-9]+)./)) {
		jQuery.browser.msie = true;
		jQuery.browser.version = RegExp.$1;
	}
})();

var selectBuildId = "";
var selectFloorId = ""; //当前查看控制楼层
var timestep = 2000; //5秒侦听一次
var netStatusTimeStep = 30000;//20秒
var timetask = null;
var listen_status = 0;
var listen_tm = new Date().getTime();

var buildList = [];
var floorList = [];
var classList = [];
//断网教室列表 默认情况下 所有教室都有网 
var withoutNetClass = [];
var Build = {
	getBuildHtml : function(name, id) {
		return "<div class='menu'><div class='menu-title' id='" + id + "' onclick='clickMenu($(this))'>" + name + "</div><div class='menu-items' id='menu-items-" + id + "' style='display:none;'></div></div>";
	},
	getFloorHtml : function(name, id) {
		return "<div class='menu-item' id='" + id + "' onclick='clickMenuItem($(this))'>" + name + "</div>";
	},
	getClassesHtml : function(class_item, html) {
		return html.replace(/\[class\_id\]/g, class_item.id).replace(/\[class\_name\]/g, class_item.name).replace(/\[touyUrl\]/g, class_item.touyUrl);
	},
	addClasses : function(class_item, html) {
		html = Build.getClassesHtml(class_item, html);
		//wjd--0809
		var divLength = $('#' + class_item.id).length;
		if (divLength > 0) {
			$('#' + class_item.id).remove();
		}
		debugger;
		//wjd--
		//防止重复
		if ($('#' + class_item.id).length <= 0) {
			html = html.replace(/\[class\_id\]/g, class_item.id).replace(/\[class\_name\]/g, class_item.name).replace(/\[touyUrl\]/g, class_item.touyUrl)
				.replace(/\[class\_ip\]/g, class_item.serverHost).replace(/\[class\_center\_ip\]/g, class_item.centerHost)
				.replace(/\[class\_touy\]/g, class_item.touyUrl).replace(/\[center\_host\]/g, class_item.centerHost)
				.replace(/\[aOpenCode16\]/g, class_item.aOpenCode16).replace(/\[aOpenCode23\]/g, class_item.aOpenCode23)
				.replace(/\[aOpenCode26\]/g, class_item.aOpenCode26).replace(/\[aCloseCode\]/g, class_item.aCloseCode)
				.replace(/\[bOpenCode16\]/g, class_item.bOpenCode16).replace(/\[bOpenCode23\]/g, class_item.bOpenCode23)
				.replace(/\[bOpenCode26\]/g, class_item.bOpenCode26).replace(/\[bCloseCode\]/g, class_item.bCloseCode)
				.replace(/\[kongtype\]/g, class_item.kongtype)
				.replace(/\[touyType\]/g, class_item.touytype).replace(/\[openCode\]/g, class_item.touyOpenCode).replace(/\[closeCode\]/g, class_item.touyCloseCode);

			$("#class_container").append(html);
			//$("#touyTypeId" + class_item.id).val(class_item.touytype);
			debugger;
			if(class_item.touytype.indexOf("格力")>-1){
				html = html.replace(/\[glchecked\]/g,'checked');
				//$("#gl").attr("checked",'true');
			}
			if(class_item.touytype.indexOf("美的")>-1){
				html = html.replace(/\[mdchecked\]/g,'checked');
				
				//$("#md").attr("checked",'true');
			}

		}

		var centerX = 85,
			centerY = 85,
			radius = 60,
			divHalfWidth = 25,
			divHalfHeight = 25,
			divNum = 5;
		for (var i = 0; i < divNum; i++) {
			var avd = 360 / divNum;
			//每一个BOX对应的弧度;
			var ahd = avd * Math.PI / 180;
			var divH = divHalfWidth;

			var tmpX = centerX - Math.round(Math.cos(ahd * i) * radius) - divH;
			var tmpY = centerY - Math.round(Math.sin(ahd * i) * radius) - divH;
			console.log("y:" + Math.sin(ahd * i) * radius + " x:" + Math.cos(ahd * i) * radius);
			$('#' + class_item.id + " .dev-item:nth-child(" + (i + 1) + ")").css({
				"margin-left" : tmpX + "px",
				"margin-top" : tmpY + "px"
			});
		}

		$('#' + class_item.id + " .dev-item:nth-child(6)").css({
			"margin-left" : "60px",
			"margin-top" : "60px"
		});


	},
	/** 初始化某栋楼的楼层 */
	initBuild : function(build) {
		$('#' + build.id).empty();
		$('#menu-container').append(Build.getBuildHtml(build.name, build.id));
		console.log(getFloorList(build.id));
		$.each(getFloorList(build.id), function(i, floor) {
			$("#menu-items-" + build.id).append(Build.getFloorHtml(floor.name, floor.id));

		});
	},
	resetClasses : function() {
		$('#class_container').empty();
		if (selectFloorId == "")
			$('#class_container').append("<div>请选择相关楼层</div>");else {
			$.each(getClasses(selectBuildId, selectFloorId), function(i, item) {
				var html = $('#face .classes-item')[0].outerHTML;
				Build.addClasses(item, html);
			});
			//win 0288
			listenStatus();
		}
	},
	/** 初始化所有楼层信息 */
	init : function() {
		$('#menu-container').empty();
		$.each(getBuildList(), function(i, build) {
			//console.log(build_id);
			Build.initBuild(build);
		});
	}
};

$(document).ready(function() {
	$('.menu-title').click(function() {
		$('.menu-item').toggle();
	});
	$('#addClassBtn').click(function() {
		showClassBox();
	});
	$('#addFloorBtn').click(function() {
		showFloorBox();
	});
	$('#addBuildBtn').click(function() {
		showBuildBox();
	});
	Build.init();
	var enterTime = new Date().getTime();
	debugger;
	timetask = window.setInterval(_listenStatusParam(enterTime), timestep);
	window.setInterval(getNetInfo,netStatusTimeStep);//0829
//getClasses();	
});






function clickMenu(obj) {
	if ($(obj).attr("id") != selectBuildId){
	    selectFloorId = "";
	    selectBuildId = $(obj).attr("id");
	    console.log("clickMenuTitle:" + selectBuildId);
	    $(".menu .menu-items").hide();
	    $('#menu-items-' + selectBuildId).show();
	    Build.resetClasses();
	}else{
	    $(".menu .menu-items").hide();
	    selectBuildId = "";
	}
}

function clickMenuItem(obj) {
	$(obj).parent().find(".menu-item").removeClass("menu-item-active");
	selectFloorId = $(obj).attr("id");
	console.log("clickMenuItem" + selectBuildId + "--" + selectFloorId);
	$(obj).addClass("menu-item-active");
	Build.resetClasses();
}

function getBuildList() {
	if (buildList.length == 0) {
		var url = "Classes?a=getBuildList";
		buildList = syncPost(url, {});
	}
	return buildList;
}

function getFloorList(build_id) {
	var out = [];
	if (floorList.length == 0) {
		var httpUrl = "Classes?a=getFloorList";
		var resp = syncPost(httpUrl, {});
		if (null != resp)
			floorList = resp;
	}
	if (floorList.length > 0) {
		$.each(floorList, function(i, item) {
			if (item.buildId == build_id)
				out[out.length] = item;
		});
	}
	return out;
}

function getClasses(build_id, floor_id) {
	var out = [];
	if (classList.length == 0) {
		console.log(classList);
		var url = "Classes?a=getClassList";
		classList = syncPost(url, {});
	}
	if (classList.length > 0) {
		$.each(classList, function(i, item) {
			if (item.buildId == build_id && item.floorId == floor_id)
				out[out.length] = item;
		});
	}
	return out;
}

function resetFloor(build_id,windowId) {
	console.log("resetFloor bid:" + build_id);
	var $addClassHtml = $("#addClassHtml");
	if(windowId!=undefined){
		$addClassHtml=$("#"+windowId);
	}
	$addClassHtml.find("select[name='class_floor']").empty();
	var floorList = getFloorList(build_id);
	console.log(floorList);
	$.each(floorList, function(i, item) {
		$addClassHtml.find("select[name='class_floor']").append("<option value='" + item.id + "'>" + item.name + "</option>");
	});
	if (selectFloorId != "") $addClassHtml.find("select[name='class_floor']").val(selectFloorId);
}

function submitClass() {
	//console.log("submit");
	var url = "Classes?a=addClass&class_floor=" + selectFloorId;
	var postData = $('#addClassHtml').find("input,select");
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined" && resp.result == 1) {
			classList[classList.length] = resp.classinfo;
			if (selectFloorId == resp.classinfo.floorId
				&& selectBuildId == resp.classinfo.buildId) {
				var html = $('#face .classes-item')[0].outerHTML;
				Build.addClasses(resp.classinfo, html);
			}
		}
	}, "json");
}

function showClassBox() {
	var $classHtml = $('.addClassHtml');
	$classHtml.find(".clazzIp").each(function(index,val){
		$(val).show();
	});
	var html = " <div id='addClassHtml' style='height:100%;border:1px solid #000;'>" + $('.addClassHtml').html() + "</div>";
	var wform = new Boxy(html, {
		"title" : "添加教室",
		"modal" : false,
		"draggable" : true,
		"unloadOnHide" : true,
		afterHide : function() {},
		afterShow : function() {
			$('.addClassSubmit').click(function() {
				submitClass();
			});
			$("#addClassHtml select[name='class_build']").change(function() {
				resetFloor($(this).val());
			});
			$.each(getBuildList(), function(i, item) {
				$('#addClassHtml').find("select[name='class_build']").append("<option value='" + item.id + "'>" + item.name + "</option>");
			});
			$("#addClassHtml select[name='class_build']").val(selectBuildId);
			resetFloor($("#addClassHtml").find("select[name='class_build']").val());
		}
	});
}

function showFloorBox() {
	var $floorHtml = $('.addFloorHtml');
	$floorHtml.find(".floor").show();
	var html = " <div id='addFloorHtml' style='height:100%;border:1px solid #000;'>" + $floorHtml.html() + "</div>";
	var wform = new Boxy(html, {
		"title" : "添加楼层",
		"modal" : false,
		"draggable" : true,
		"unloadOnHide" : true,
		afterHide : function() {},
		afterShow : function() {
			$('.addFloorSubmit').click(function() {
				submitFloor();
			});
		}
	});
	$.each(getBuildList(), function(i, item) {
		$('#addFloorHtml').find("select").append("<option value='" + item.id + "'>" + item.name + "</option>");
	//$('#addBuildHtml select').append("<option value='"+item.id+"'>"+item.name+"</option>");
	});
	$('#addFloorHtml select').val(selectBuildId);
}
function submitFloor() {
	var url = "Classes?a=addFloor";
	var postData = $('#addFloorHtml').find("input,select");
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined" && resp.result == 1) {
			floorList[floorList.length] = resp.floorinfo;
			if (selectBuildId == resp.floorinfo.buildId
				&& selectBuildId == resp.floorinfo.buildId) {
				var html = Build.getFloorHtml(resp.floorinfo.name, resp.floorinfo.id);
				$("#menu-items-" + resp.floorinfo.buildId).append(html);
			}
		}
	}, "json");
}
//wjd 修改监控Ip
function updateMonitorIp(formId) {
	var $form = $("form[id='monitorIP" + formId + "']");
	var url = "Monitor";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined") {
			alert(resp.msg);
			$("#monitorIP" + formId).dialog('close');
		}
	}, "json");
}
//wjd 修改中控Ip
function updateCloudTerminalIp(formId) {
	var $form = $("form[id='cloudTerminalIP" + formId + "']");
	var url = "CloudTerminal";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined") {
			alert(resp.msg);
			$("#cloudTerminalIP" + formId).dialog('close');
		}
	}, "json");
}
//wjd 修改教室Ip
function updateClassRoomIp(formId) {
	var $form = $("form[id='classroom" + formId + "']");
	var url = "ClassRoom";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined") {
			alert(resp.msg);
			$("#ip-config" + formId).dialog('close');
		}
	}, "json");
}
//wjd 查看当天课表
function showKeBiao(classId) {
	var url = "KeBiao";
	$.post(url, '', function(resp) {
		if (typeof (resp) != "undefined") {
			$kebiaodiv = $("#kebiaoDetail" + classId);
			$kebiaodiv.empty();
			for (var i in resp) {
				$kebiaodiv.append("<li>" + resp[i] + "</li>");
			}
			$("#kebiao" + classId).dialog();
		}
	}, "json");
}
//wjd 打开操作中控框
function openFirstWindow(windowId,classId, operation) {
	$operationdiv = $(windowId+classId);
	//guanbi jian kong chuang kou 
	$("#videoclass" + classId).hide();
	//如果原来是隐藏的
	if (!$operationdiv.is(":visible")) {
		$(".firstWindow").each(function(index, value) {
			$(this).hide();
		});
		$operationdiv.show();
		//显示div里所有button
		$operationdiv.find("button [display='not']").each(function(index, val) {
			$(this).show();
		}
		);
		if (operation != undefined && operation == 'open')
			$operationdiv.find(".open").show();
		else if (operation != undefined && operation == 'close')
			$operationdiv.find(".close").show();
	} else { //如果原来是显示的 那么隐藏
		$operationdiv.hide();
	}
}
	
//wjd 修改监控 
function updateAirConditionhongwai(formId) {
	var $form = $("form[id='airConditionerFrom" + formId + "']");
	var url = "AirCondition";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined") {
			alert(resp.msg);
			$("#showAirConditionerHongWai" + formId).dialog('close');
		}
	}, "json");
}
//wjd 修改监控 
function updateTouy(formId) {
	var $form = $("form[id='touyForm" + formId + "']");
	var url = "Touy";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined") {
			alert(resp.msg);
			$("#touyControl" + formId).dialog('close');
		}
	}, "json");
}
function syncPost(httpUrl, postData) {
	var resp = null;
	$.ajax({
		type : "POST",
		url : httpUrl,
		async : false,
		data : postData,
		success : function(msg) {
			resp = msg;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {}
	});
	return resp;
}

function doPost(httpUrl, postData, callbackSuccess, callbackError) {
	$.ajax({
		type : "POST",
		url : httpUrl,
		async : false,
		data : postData,
		success : function(msg) {
			callbackSuccess(msg);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			callbackError(textStatus);
		}
	});
}

function showBuildBox() {
	var html = " <div id='addBuildHtml' style='height:100%;border:1px solid #000;'>" + $('.addBuildHtml').html() + "</div>";
	var wform = new Boxy(html, {
		"title" : "添加大楼",
		"modal" : false,
		"draggable" : true,
		"unloadOnHide" : true,
		afterHide : function() {},
		afterShow : function() {
			$('.addBuildSubmit').click(function() {
				submitBuild();
			});
		}
	});
}
function submitBuild() {
	var url = "Classes?a=addBuild";
	var postData = $('#addBuildHtml').find("input,select");
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined" && resp.result == 1) {
			buildList[buildList.length] = resp.buildinfo;
			$('#menu-container').append(Build.getBuildHtml(resp.buildinfo.name, resp.buildinfo.id));
		}

	}, "json");
}

function deleteClass(class_id) {
	var url = "Classes?a=deleteClass&&build_id=" + selectBuildId + "&floor_id=" + selectFloorId + "&class_id=" + class_id;
	;
	$.post(url, {}, function(resp) {
		if (typeof (resp.result) != "undefined" && resp.result == 1) {
			$('#' + class_id).remove();
			classList.splice(0,classList.length);
		} else {
			alert("操作失败");
		}

	}, "json");
}

/** 向服务端发送指令 */
function doAction(method, class_id) {
	var url = "Cmd?a=common&method=" + method + "&build_id=" + selectBuildId + "&floor_id=" + selectFloorId + "&class_id=" + class_id;
	$.getJSON(url, function(resp) {
		
		if (null != resp && typeof (resp.result) != "undefined" && resp.result == 1) {
			console.log("send cmd[" + method + "] success");
			alert("指令发送成功");
		} else {
			alert("指令发送失败");
		}
	});
}
//0828
/** 向服务端发送指令 */
function headAction(method, build_id,floorId,opt) {
	var url = "Cmd?a=" + method + "&build_id=" + build_id + "&floor_id=" + floorId +"&opt="+opt;
	$.getJSON(url, function(resp) {
		if (null != resp && typeof (resp.result) != "undefined" && resp.result == 1) {
			console.log("send cmd[" + method + "] success");
			alert("指令发送成功");
		} else {
			alert("指令发送失败");
		}
	});
}



/**
 * 打开监控
 */
function openMonitor(obj, classId) {
	$(obj).hide();
	$(obj).prev().show();
	//var vlc = $(obj).parent().parent().parent().parent().find(".videoclass");
	$("#videoclass" + classId).show();
	$("#monitor" + classId+" [id=open_jk]").hide();
	$("#monitor" + classId+" [id=jk11]").hide();
	$("#monitor" + classId).removeClass("firstWindow").addClass("firstWindowNoBorder");
	/*$("#monitor" + classIs+"[id=td firstWindow]").hide();*/
	$("#monitor" + classId+" [class=clabel4]").removeClass("clabel4").addClass("clabel4nobg");
}

/**
 * 关闭监控
 */
function closeMonitor(obj, classId) {
	$(obj).hide();
	$("#videoclass" + classId).hide();
	$(obj).prev().show();
	$(obj).next().show();
	$("#monitor" + classId).removeClass("firstWindowNoBorder").addClass("firstWindow");
	var objItem = $(vlc).find("object")[0];

	if (typeof (objItem.playlist) != "undefined") objItem.playlist.stop();
}

function listenStatus() {
	var time = new Date().getTime();
	if (selectFloorId != "" && (listen_status == 0 || time - listen_tm > 120000)) {
		listen_status = 1;
		$.getJSON("DevListen?floor=" + selectFloorId, function(resp) {
			$.each(resp, function(class_key, item) {
				listen_status = 0;
				for (var key in item) {
					//console.log(key+":"+"public/img/"+key+"_"+item[key]+".png");
					// wjd0823 if (key != "monitor") {
						if (item[key] == "on") {
							$('#' + class_key).find('.' + key + "_offline").hide();
							$('#' + class_key).find('.' + key + "_off").hide();
							$('#' + class_key).find('.' + key + "_on").show();
							if($.inArray(class_key, withoutNetClass)>-1){
								   
								   withoutNetClass.remove($.inArray(class_key, withoutNetClass));
							   }
						} else if (item[key] == "offline") {
							$('#' + class_key).find('.' + key + "_on").hide();
							$('#' + class_key).find('.' + key + "_off").hide();
							$('#' + class_key).find('.' + key + "_offline").show();
							if(key=="air_conditioner"&&$.inArray(class_key, withoutNetClass)==-1){
								 alert($('#'+class_key+"roomName").val()+"断网了");
								 withoutNetClass.push(class_key);
							 }
						} else {
							$('#' + class_key).find('.' + key + "_offline").hide();
							$('#' + class_key).find('.' + key + "_on").hide();
							$('#' + class_key).find('.' + key + "_off").show();
							if($.inArray(class_key, withoutNetClass)>-1){
								   
								   withoutNetClass.remove($.inArray(class_key, withoutNetClass));
							   }
						}
					//}
				}
			});
		});
	}
//console.log("listen " + floor);
}

// 传参方式监听
function listenStatusParam(enterTime) {
	debugger;
	var time = new Date().getTime();
	if (selectFloorId != "" && (listen_status == 0 || time - listen_tm > 120000)) {
		listen_status = 1;
		//var tip_status = 0,;    111
		$.getJSON("DevListen?floor=" + selectFloorId, function(resp) {
			$.each(resp, function(class_key, item) {
				listen_status = 0;
				for (var key in item) {
					//console.log(key+":"+"public/img/"+key+"_"+item[key]+".png");
					// wjd0823 if (key != "monitor") {
					if (item[key] == "on") {
						$('#' + class_key).find('.' + key + "_offline").hide();
						$('#' + class_key).find('.' + key + "_off").hide();
						$('#' + class_key).find('.' + key + "_on").show();
					} else if (item[key] == "offline") {
						$('#' + class_key).find('.' + key + "_on").hide();
						$('#' + class_key).find('.' + key + "_off").hide();
						$('#' + class_key).find('.' + key + "_offline").show();
						/*tip_status == 1;
						if(tip_status = 1 && time-enterTime>60000){   
						   alert("已经断电");*/
				    	}else {
						$('#' + class_key).find('.' + key + "_offline").hide();
						$('#' + class_key).find('.' + key + "_on").hide();
						$('#' + class_key).find('.' + key + "_off").show();
					}
					//}
				}
			});
		});
		/*if(tip_status = 1 && time-enterTime>60000){   111
			alert("已经断电");
		}*/
	}
//console.log("listen " + floor);
} 

	function getNetInfo(){
		
		for(var index in floorList){
			$.getJSON("DevListen?floor="+floorList[index].id,function(resp){
				$.each(resp,function(class_key,item){	
					for (var key in item){
						if(key=="air_conditioner"){
					   
						   if(item[key] == "on"){
							   	if($.inArray(class_key, withoutNetClass)>-1){
								   withoutNetClass.remove($.inArray(class_key, withoutNetClass));
							   }
						   }else if(item[key] == "offline"){
							 if($.inArray(class_key, withoutNetClass)==-1){
								 alert(resp[class_key+"className"]+"断网了");
								 withoutNetClass.push(class_key);
							 }
						   }else{
							   if($.inArray(class_key, withoutNetClass)>-1){
								   
								   withoutNetClass.remove($.inArray(class_key, withoutNetClass));
							   }
						   }
					   				   
						}
					}
				});				
			});		
	}
		
		//console.log("listen " + floor);
	}
	//open building select window opt=1 toOpen 2 toClose
	function showBuildControlBox(opt) {
		var $build = $('.addFloorHtml');
		$build.find(".floor").hide();
		var html = " <div id='showBuildControlBox' style='height:100%;border:1px solid #000;'>" + $build.html() + "</div>";
		var titleTail="开";
		if(opt==2){
			titleTail ="关";
		}
		var wform = new Boxy(html, {
			"title" : "整栋"+titleTail,
			"modal" : false,
			"draggable" : true,
			"unloadOnHide" : true,
			afterHide : function() {},
			afterShow : function() {
				$('.addFloorSubmit').click(function() {
					if(confirm("你确定要整栋楼 "+titleTail+"吗?")){
						var buildId = $('#showBuildControlBox').find("select").val();
						headAction("buildControl",buildId,'',opt);
					}
				});
			}
		});
		$.each(getBuildList(), function(i, item) {
			$('#showBuildControlBox').find("select").append("<option value='" + item.id + "'>" + item.name + "</option>");
		//$('#addBuildHtml select').append("<option value='"+item.id+"'>"+item.name+"</option>");
		});
		$('#showBuildControlBox select').val(selectBuildId);
	}
	function showFloorControlBox(opt) {
		var $classHtml = $('.addClassHtml');
		$classHtml.find(".clazzIp").each(function(index,val){
			$(val).hide();
		});
		var html = " <div id='showFloorControlHtml' style='height:100%;border:1px solid #000;'>" + $('.addClassHtml').html() + "</div>";
		var titleTail = "关";
		if(opt==2){
			titleTail = "开";
		}
		var currentBuildId='';
		var wform = new Boxy(html, {
			"title" : "整 层"+titleTail,
			"modal" : false,
			"draggable" : true,
			"unloadOnHide" : true,
			afterHide : function() {},
			afterShow : function() {
				$('.addClassSubmit').click(function() {
					if(confirm("你确定要整层楼 "+titleTail+"吗?")){
					var floorId = $("#showFloorControlHtml").find("select[name='class_floor']").val();
					headAction("floorControl",currentBuildId,floorId,opt);
					}
				});
				$("#showFloorControlHtml select[name='class_build']").change(function() {
					currentBuildId = $(this).val();
					resetFloor(currentBuildId,"showFloorControlHtml");
				});
				$.each(getBuildList(), function(i, item) {
					$('#showFloorControlHtml').find("select[name='class_build']").append("<option value='" + item.id + "'>" + item.name + "</option>");
				});
				$("#showFloorControlHtml select[name='class_build']").val(currentBuildId);
				resetFloor($("#showFloorControlHtml").find("select[name='class_build']").val(),"showFloorControlHtml");
			}
		});
	}
//创建一个函数,用于返回一个无参数函数 
function _listenStatusParam(enterTime){ 
	return function(){ 
		listenStatusParam(enterTime); 
	}
} 