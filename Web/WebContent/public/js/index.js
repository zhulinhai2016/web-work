jQuery.browser = {};
(function() {
	jQuery.browser.msie = false;
	jQuery.browser.version = 0;
	if (navigator.userAgent.match(/MSIE ([0-9]+)./)) {
		jQuery.browser.msie = true;
		jQuery.browser.version = RegExp.$1;
	}
	;
	$(document).keypress(function(e) {
		if (e.keyCode == 13) {
			e.preventDefault();
		}
	});
})();
/**
 * 科学排序
 */
/* 科学排序开始 */
var mArraySort = function(key) {
	var temp = this;
	var splitSortArr = function(x) {
		var t = x.split('');
		var arr = [];
		for ( var i in t) {
			if (t.hasOwnProperty(i)) {
				if (i == 0) {
					arr.push(t[i]);
				} else {
					if (!isNaN(arr[arr.length - 1]) == !isNaN(t[i])) {
						arr[arr.length - 1] += t[i];
					} else {
						arr.push(t[i]);
					}
				}
			}
		}
		for ( var i in arr) {
			if (!arr.hasOwnProperty(i) || isNaN(arr[i]))
				continue;
			arr[i] = Number(arr[i]);
		}
		return arr;
	};
	for ( var i in temp) {
		if (!temp.hasOwnProperty(i))
			continue;
		if (!key) {
			temp[i] = splitSortArr(temp[i]);
			// console.log(temp[i]);
		} else {
			temp[i][key] = splitSortArr(temp[i][key]);
			// console.log(temp[i][key]);
		}
	}
	temp = temp
			.sort(function(a, b) {
				var cnNumArr = [ '零', '一', '二', '三', '四', '五', '六', '七', '八',
						'九', '十' ];
				if (key) {
					var a = a[key];
					var b = b[key];
				}
				for ( var i in a) {
					if (!a.hasOwnProperty(i) || !b[i])
						continue;
					if (typeof a[i] == 'string' && typeof b[i] == 'number') {
						return 1
					}
					if (typeof b[i] == 'string' && typeof a[i] == 'number') {
						return -1
					}
					if (typeof a[i] == 'string' && typeof b[i] == 'string') {
						var tempAIndex = -1;
						var tempBIndex = -1;
						for ( var k in cnNumArr) {
							if (!cnNumArr.hasOwnProperty(k))
								continue;
							for ( var m in cnNumArr) {
								if (!cnNumArr.hasOwnProperty(m))
									continue;
								if (a[i].indexOf(cnNumArr[k]) >= 0
										&& b[i].indexOf(cnNumArr[m]) >= 0
										&& a[i].indexOf(cnNumArr[k]) == b[i]
												.indexOf(cnNumArr[m])) {
									tempAIndex = k;
									tempBIndex = m;
								}
							}
						}
						if (tempAIndex >= 0 && tempBIndex >= 0) {
//							console.log(tempAIndex);
//							console.log(tempBIndex);
							if (tempAIndex > tempBIndex) {
								return 1;
							}
							if (tempAIndex < tempBIndex) {
								return -1;
							}
						}
						if (a[i].toUpperCase() > b[i].toUpperCase()) {
							return 1;
						}
						if (a[i].toUpperCase() < b[i].toUpperCase()) {
							return -1;
						}
					}
					if (a[i] > b[i]) {
						return 1;
					}
					if (a[i] < b[i]) {
						return -1;
					}
				}
				if (a.length > b.length) {
					return 1;
				}
				if (a.length < b.length) {
					return -1;
				} else {
					return 0;
				}
			});
	for ( var i in temp) {
		if (!temp.hasOwnProperty(i))
			continue;
		if (!key) {
			temp[i] = temp[i].join('');
		} else {
			temp[i][key] = temp[i][key].join('');
		}
	}
	return temp;
};
Object.defineProperty(Array.prototype, "mSort", {
	value : mArraySort,
	enumerable : false
});
/* 科学排序结束 */
var selectBuildId = "";
var selectFloorId = ""; // 当前查看控制楼层
var timestep = 2000; // 5秒侦听一次
var netStatusTimeStep = 1000;
var timetask = null;
var listen_status = 0;
var listen_tm = new Date().getTime();

var buildList = [];
var floorList = [];
var classList = [];
// 断网教室列表 默认情况下 所有教室都有网
var withoutNetClass = [];
//' onclick='clickMenu($(this))
var Build = {
	getBuildHtml : function(name, id) {
		return "<div class='menu'><div class='menu-title' id='build_" + id
				+ "'>" + name
				+ "</div><div class='menu-items' id='menu-items-" + id
				+ "' style='display:none;'></div></div>";
	},
	getFloorHtml : function(name, id) {
		return "<div class='menu-item' id='floor_" + id
				+ "' onclick='clickMenuItem($(this))'>" + name + "</div>";
	},
	getClassesHtml : function(class_item, html) {
		return html.replace(/\[class\_id\]/g, class_item.id).replace(
				/\[class\_name\]/g, class_item.name).replace(/\[touyUrl\]/g,
				class_item.classTouy);
	},
	addClasses : function(class_item, html) {
		html = Build.getClassesHtml(class_item, html);
		// wjd--0809
		var divLength = $('#class' + class_item.id).length;
		if (divLength > 0) {
			$('#class' + class_item.id).remove();
		}
		// wjd--
		// 防止重复
		else {
			html = html.replace(/\[class\_id\]/g, class_item.id).replace(
					/\[class\_name\]/g, class_item.name).replace(
					/\[touyUrl\]/g, class_item.classTouy).replace(
					/\[classId\]/g, class_item.classId).replace(
					/\[class\_ip\]/g, class_item.classIp).replace(
					/\[class\_center\_ip\]/g, class_item.classCenterIp)
					.replace(/\[class\_touy\]/g, class_item.classTouy).replace(
							/\[center\_host\]/g, class_item.classCenterIp)// 中控IP
					.replace(/\[aOpenCode16\]/g, class_item.aOpenCode16)
					.replace(/\[aOpenCode23\]/g, class_item.aOpenCode23)
					.replace(/\[aOpenCode26\]/g, class_item.aOpenCode26)
					.replace(/\[aCloseCode\]/g, class_item.aCloseCode).replace(
							/\[bOpenCode16\]/g, class_item.bOpenCode16)
					.replace(/\[bOpenCode23\]/g, class_item.bOpenCode23)
					.replace(/\[bOpenCode26\]/g, class_item.bOpenCode26)
					.replace(/\[bCloseCode\]/g, class_item.bCloseCode).replace(
							/\[touyType\]/g, class_item.touyType).replace(
							/\[openCode\]/g, class_item.touyOpenCode).replace(
							/\[closeCode\]/g, class_item.touyCloseCode);

			//if ( class_item.kongtype.indexOf("格力") > -1 || class_item.kongtype2.indexOf("格力") > -1 
				//	|| class_item.kongtype.indexOf("美的") > -1 ||class_item.kongtype2.indexOf("美的") > -1) {
			if(class_item)
			if(class_item.kongtype!=''){
				kongtypes = class_item.kongtype.split(",");
				html = html.replace(class_item.id + "glnochecked", 'checked');
				$("#class_container").append(html);
				$("#showAirConditionerHongWai"+class_item.id).find("#kongtype1").val(kongtypes[0]);
				$("#showAirConditionerHongWai"+class_item.id).find("#kongtype2").val(kongtypes[1]);
//				if (index1 > -1) {
//					checkedVal($("#showAirConditionerHongWai"+class_item.id).find("#gl0"));
//				} 
//				if (index2 > -1) {
//					$("#showAirConditionerHongWai"+class_item.id).find("#gl00").prop("checked",true); 
//					 checkedVal($("#showAirConditionerHongWai"+class_item.id).find("#gl1"));
//				}
//				if(index3 != -1){
//					$("#showAirConditionerHongWai"+class_item.id).find("#md00").prop("checked",true);
//					checkedVal($("#showAirConditionerHongWai"+class_item.id).find("#md2"));
//				}
//				if(index4 > -1){
//					
//					$("#showAirConditionerHongWai"+class_item.id).find("#md3").prop("checked",true);
//					checkedVal($("#showAirConditionerHongWai"+class_item.id).find("#md3"));
//				}
			}else{
				$("#class_container").append(html);
			}
			
			$("#touyTypeId" + class_item.id).val(class_item.touytype);
			localStorage.setItem("#showAirConditionerHongWai"+class_item.id,JSON.stringify(class_item));
			localStorage.setItem("#touyControl"+class_item.id,JSON.stringify(class_item));
//			$("#showAirConditionerHongWai"+class_item.id).data("data",class_item);
		}

		var centerX = 85, centerY = 85, radius = 60, divHalfWidth = 25, divHalfHeight = 25, divNum = 5;
		for (var i = 0; i < divNum; i++) {
			var avd = 360 / divNum;
			// 每一个BOX对应的弧度;
			var ahd = avd * Math.PI / 180;
			var divH = divHalfWidth;

			var tmpX = centerX - Math.round(Math.cos(ahd * i) * radius) - divH;
			var tmpY = centerY - Math.round(Math.sin(ahd * i) * radius) - divH;
//			console.log("y:" + Math.sin(ahd * i) * radius + " x:"
//					+ Math.cos(ahd * i) * radius);
			$('#' + class_item.id + " .dev-item:nth-child(" + (i + 1) + ")")
					.css({
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
		$('#build_' + build.id).empty();
		$('#menu-container').append(Build.getBuildHtml(build.name, build.id));
		/*
		 * $.each(getFloorList(build.id), function(i, floor) { $("#menu-items-" +
		 * build.id).append( Build.getFloorHtml(floor.name, floor.id));
		 * 
		 * });
		 */// 新注释的
	},
	resetClasses : function() {
		$('#class_container').empty();
		if (selectFloorId == "")
			$('#class_container').append("<div>请选择相关楼层</div>");
		else {
//			console.log(selectBuildId);
//			console.log(selectFloorId);
//			console.log(getClasses(selectBuildId, selectFloorId));
			$.each(getClasses(selectBuildId, selectFloorId), function(i, item) {
				var html = $('#face .classes-item')[0].outerHTML;
				Build.addClasses(item, html);
			});
			// win 0288
			listenStatus();
		}
	},
	/** 初始化所有楼层信息 */
	init : function(func) {
		selectBuildId="";
		$('#menu-container').empty();
		var tempBuild = getBuildList() || [];
		// tempBuild.mSort('name'); 新注释的
		for (var i = 0; i < tempBuild.length; i++) {
			Build.initBuild(tempBuild[i]);
		}
		if(func){
			func();
//		setTimeout(func(),500);
			
		}
		// $.each(getBuildList(), function(i, build) {
		// // console.log(build_id);
		// console.log(build);
		 /*console.log(build);
		 Build.initBuild(build);
		 Build.initBuild(build);
		 func();
		 if(func){
            tempBuild = getBuildList();	 
		 }*/
		// Build.initBuild(build);
	
		// });
	}
};

$(document).ready(function() {

	$('.menu-title').click(function() {
		$('.menu-item').toggle();
	});
	$('#addClassBtn').click(function() {

		// $("table").each(function(index, value) {// sou you tan chu dou jia
		// shang
		// // gai duan daima
		// $(value).hide();
		// });

		$("table.boxy-wrapper").remove();
		showClassBox();

	});
	$('#addFloorBtn').click(function() {

		// $("table").each(function(index, value) {// sou you tan chu dou jia
		// shang
		// // gai duan daima
		// $(value).hide();
		// });

		$("table.boxy-wrapper").remove();
		showFloorBox();
	});
	$('#addBuildBtn').click(function() {

		// $("table.boxy-wrapper").each(function(index, value) {// sou you tan
		// chu dou jia shang
		// // gai duan daima
		// $(value).hide();
		// });
		$("table.boxy-wrapper").remove();
		showBuildBox();
	});
	Build.init();
	var enterTime = new Date().getTime();
	timetask = window.setInterval(_listenStatusParam(enterTime), timestep);// TODO
	
	
	//这个setinterval 有同步请求  影响页面效率   但是不知道干什么的   先注释掉 ，测试出问题，再看有什么影响。netStatusTimeStep
	window.setInterval(getNetInfo, 2000);// 0829 
	
	window.isUpdateBuild=false;
	// getClasses();

});

function clickMenu(obj) {
	// TODO 删除iframe
	var class_container=$("#class_container");
	class_container.find("iframe[tab-id='page1']").remove();
//	var iframe1=class_container.find("iframe[tab-id='page1']");
//	if(iframe1 && iframe1.length != 0){
//		class_container.remove(iframe1);
//	}
	var buildId = $(obj).attr("id").split("_")[1];
	var isActive=$(obj).hasClass("menu-title-active");
	$('.menu-title').removeClass('menu-title-active');
	if(!isActive){
		$(obj).addClass('menu-title-active');
	}
	if (buildId != selectBuildId) {
		selectFloorId = "";
		selectBuildId = buildId;
		$("#menu-items-" + buildId).empty();
		$.each(getFloorList(buildId), function(i, floor) {
			$("#menu-items-" + buildId).append(
					Build.getFloorHtml(floor.name, floor.id));
		});
//		console.log("clickMenuTitle:" + selectBuildId);
		$(".menu .menu-items").hide();
		$('#menu-items-' + selectBuildId).show();
//		$(document).on('click','.m-fTop-center .m-rundatacount-page',function(event){
//			$("#menu-container").hide();
//		});
//		$(document).on('click','.m-fTop-center .m-editPower-page',function(event){
//			$("#menu-container").hide();
//		});
//		$(document).on('click','.m-fTop-center .m-home-page',function(event){
//			$("#menu-container").hide();
//		});
		
		Build.resetClasses();
	} else {
		$(".menu .menu-items").hide();
		selectBuildId = "";
	}
}

function clickMenuItem(obj) {
	// TODO 删除iframe
	var class_container=$("#class_container");
	class_container.find("iframe[tab-id='page1']").remove();
//	var iframe1=class_container.find("iframe[tab-id='page1']");
//	if(iframe1 && iframe1.length != 0){
//		class_container.remove(iframe1);
//	}
	$(obj).parent().find(".menu-item").removeClass("menu-item-active");
	selectFloorId = $(obj).attr("id").split("_")[1];
//	console.log("clickMenuItem" + selectBuildId + "--" + selectFloorId);
	
	$(obj).addClass("menu-item-active");
	Build.resetClasses();
}

function getBuildList() {
	// if (buildList.length == 0) {
	var url = "selectAllBuild";
	buildList = syncPost(url, {});
	// }
	return buildList;
}

function getFloorList(build_id) {
	// var out = [];新注释的
	// if (floorList.length == 0) {
	var httpUrl = "selectAllFloor?buildId=" + build_id;
	var resp = syncPost(httpUrl, {});
	if (null != resp) {// 新加的
		floorList = resp;
	}// 新加的
	// }
	/*
	 * if (floorList.length > 0) { $.each(floorList, function(i, item) { // var
	 * temp = Number(item.name); // if (!isNaN(temp)) { // item.name = temp; // }
	 * if (item.buildId == build_id) out[out.length] = item; }); }
	 */// 新加的
//	console.log(floorList);// 三个out改成了三个floorlist
	// TODO 此处排序会造成的影响
	
//	floorList.mSort('name');
	var listsss = floorList.sort(function(a, b)  
     {  
         if(a.name < b.name) return -1;  
         if(a.name > b.name) return 1;  
         return 0;  
     } 
	)
	return listsss;
}
function getClasses(build_id, floor_id) {
	var out = [];
	// if (classList == null || classList.length == 0) {
	var url = "selectAllClassroom";
	classList = syncPost(url, {});
	// }
	if (classList.length > 0) {
		$.each(classList, function(i, item) {
			if (item.buildId == build_id && item.floorId == floor_id)
				out[out.length] = item;
		});
	}
	return out;
}

function resetFloor(build_id, windowId) {
//	console.log("resetFloor bid:" + build_id);
	var $addClassHtml = $("#addClassHtml");
	if (windowId != undefined) {
		$addClassHtml = $("#" + windowId);
	}
	$addClassHtml.find("select[name='class_floor']").empty();
	var floorList = getFloorList(build_id);
//	console.log(floorList);
	$.each(floorList, function(i, item) {
		$addClassHtml.find("select[name='class_floor']").append(
				"<option value='" + item.id + "'>" + item.name + "</option>");
	});
	if (selectFloorId != "")
		$addClassHtml.find("select[name='class_floor']").val(selectFloorId);
}

function submitClass() {
	// console.log("submit");
	var url = "addOrUpdateClassroom";
	var postData = $('#addClassHtml').find("input,select");
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined" && resp.result == 1) {
			alert('教室已添加成功！');
			classList[classList.length] = resp.classinfo;
			if (selectFloorId == resp.classinfo.floorId
					&& selectBuildId == resp.classinfo.buildId) {
				var html = $('#face .classes-item')[0].outerHTML;
				Build.addClasses(resp.classinfo, html);
			}
		} else {
			alert(resp.message);// 409-410新加的
		}
	}, "json");
}

function showClassBox() {
	var $classHtml = $('.addClassHtml');
	$classHtml.find(".clazzIp").each(function(index, val) {
		$(val).show();
	});
	var html = " <div id='addClassHtml' style='height:100%;border:1px solid #000;'>"
			+ $('.addClassHtml').html() + "</div>";
	var wform = new Boxy(html, {
		"title" : "添加教室",
		"modal" : false,
		"draggable" : true,
		"unloadOnHide" : true,
		afterHide : function() {
		},
		afterShow : function() {
			$('.addClassSubmit')
					.click(
							function() {
								var tempObj = $(this).parents('#addClassHtml')
										.find('.inputItem');
								for (var i = 0; i < tempObj.length; i++) {
									if (tempObj[i].value == '') {
										alert('输入值不能为空！');
										return;
									}
								}
								;
								var temp = checkInputValue($(this).parents(
										'#addClassHtml').find(
										'.class_input_ip '), 'class_IP');
								if (temp && !temp.value) {
									alert(temp.msg);
									return;
								}
								var temp2 = checkInputValue($(this).parents(
										'#addClassHtml').find('.class_select'),
										'class');
//								console.log(temp2);
								if (temp2 && !temp2.value) {
									alert(temp2.msg);
									return;
								}
//								var listss = $(this).parents('#addClassHtml').find('.ip_input');
//								$.each(listss,function(index ,item){
//									$.getJSON("checkedByIP?ip=" + $(item).val(), function(resp) {
//										if(!resp.result){
//											alert($(item).closest('.input-item').find('.input-item_p').text()+$(item).val()+' 已被使用！');
//											if(!$(item).hasClass('input-check-error')){
//												$(item).addClass('input-check-error');
//											}
//											return false;
//										}
//								});
//							});
								submitClass();
							});
			$("#addClassHtml select[name='class_build']").change(function() {
				resetFloor($(this).val());
			});
			$.each(getBuildList(), function(i, item) {
				$('#addClassHtml').find("select[name='class_build']").append(
						"<option value='" + item.id + "'>" + item.name
								+ "</option>");
			});
			$("#addClassHtml select[name='class_build']").val(selectBuildId);
			resetFloor($("#addClassHtml").find("select[name='class_build']")
					.val());
		}
	});
}

function showFloorBox() {
	var $floorHtml = $('.addFloorHtml');
	$floorHtml.find(".floor").show();
	var html = " <div id='addFloorHtml' style='height:100%;border:1px solid #000;'>"
			+ $floorHtml.html() + "</div>";
	var wform = new Boxy(html, {
		"title" : "添加楼层",
		"modal" : false,
		"draggable" : true,
		"unloadOnHide" : true,
		afterHide : function() {
		},
		afterShow : function() {
			$('.addFloorSubmit').off('click');
			$('.addFloorSubmit').click(
					function() {
						if ($(this).parents('#addFloorHtml').find(
								'.floor_select').val() == '') {
							$(this).parents('#addFloorHtml').find(
									'.floor_select').addClass(
									'input-check-error');
							alert('楼层名称不能为空！');
							return;
						}
						var build_id = $(this).parents('#addFloorHtml').find(
								'.building_select').val();
						var floorList = getFloorList(build_id);
						var tempState = false;
						for (var i = 0; i < floorList.length; i++) {
							if (floorList[i].name == $(this).parents(
									'#addFloorHtml').find('.floor_select')
									.val()) {
								tempState = true;
							}
						}
						if (tempState) {
							$(this).parents('#addFloorHtml').find(
									'.floor_select').addClass(
									'input-check-error');
							alert('楼层名称已使用！');
							return;
						} else {
							$(this).parents('#addFloorHtml').find(
									'.floor_select').removeClass(
									'input-check-error');
						}
						submitFloor();
					});
		}
	});
	$.each(getBuildList(), function(i, item) {
		$('#addFloorHtml').find("select").append(
				"<option value='" + item.id + "'>" + item.name + "</option>");
		// $('#addBuildHtml select').append("<option
		// value='"+item.id+"'>"+item.name+"</option>");
	});
	$('#addFloorHtml select').val(selectBuildId);
}
function submitFloor() {
	var url = "addOrUpdateFloor";
	var $addFloorHtml = $('#addFloorHtml');
	if ($addFloorHtml.find("input").val().trim() == ''
			|| $addFloorHtml.find("select").val() == null
			|| $addFloorHtml.find("input").val().trim() == '0') {
		alert("楼层和教学楼不能为空并且楼层不能为0");

		return false;
	}
	var postData = $addFloorHtml.find("input,select");
	$.post(url, postData, function(resp) {
		if (resp.result == true) {
			floorList[floorList.length] = resp.floorinfo;
			var html = Build.getFloorHtml(resp.floorinfo.name,
					resp.floorinfo.id);
			var temp = $("#menu-items-" + resp.floorinfo.buildId
					+ '>.menu-item');
			var newNum = $(html).text();
			var tempState = false;
			for (var i = 0; i < temp.length; i++) {
				var text = temp.eq(i).text();
				var tempValue = Number(temp.eq(i).text());
				if (!isNaN(tempValue))
					text = tempValue;
				if (text > newNum) {
					temp.eq(i).before($(html));
					tempState = true;
					break;
				}
			}
			if (!tempState)
				temp.eq(temp.length-1).parent().append($(html));
			alert("添加成功");
		}
	}, "json");
}
// wjd 修改监控Ip
function updateMonitorIp(formId, obj) {
	tempSendSuccess = false;
	var tempObj = obj.parents('.table').find('.class_touy');
	var state = checkInputValue(tempObj, 'ip');
	if (state && !state.value) {
		alert(state.msg);
		return;
	}
	var $form = $("form[id='monitorIP" + formId + "']");
	var url = "addOrUpdateClassroom";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
		if (resp.result) {
			
			alert("操作成功");
			tempSendSuccess = true;
			$("#monitorIP" + formId).dialog('close');
		}else{
			if(resp.message){
				alert(resp.message);
			} else{
				alert("操作失败！");
			}
		}
	}, "json");
}
var tempSendSuccess;
// wjd 修改中控Ip
function updateCloudTerminalIp(formId, obj) {
	tempSendSuccess = false;
	var tempObj = obj.parents('.table').find('.class_center');
	var state = checkInputValue(tempObj, 'ip');
	if (state && !state.value) {
		alert(state.msg);
		return;
	}
	var $form = $("form[id='cloudTerminalIP" + formId + "']");
	var url = "addOrUpdateClassroom";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined") {
			alert("操作成功");
			tempSendSuccess = true;
			$("#cloudTerminalIP" + formId).dialog('close');
		}
	}, "json");
}

/*/wjd送法文字function sendTouyText(classId){var sendText= $("touyTextInfo" +)}
 * //wjd发送文字 function sendTouyText(classId) { var sendText = $("#touyTextInfo" +
 * classId).val(); if (sendText.length == 0) { alert("请输入中文消息"); return false; } //
 * 如果是奇数个中文 后面添加中文; 偶数个添加英文的; if (sendText.length % 2 == 1) { sendText =
 * sendText + "&nbsp;"; } else { sendText = sendText + " "; } var url =
 * "Cmd?a=sendtouyText&class_id=" + classId + "&sendText=" +
 * encodeURI(sendText); $.getJSON(url, function(resp) {
 * 
 * if (null != resp && typeof (resp.result) != "undefined" && resp.result == 1) {
 * console.log("send cmd[" + method + "] success"); alert("指令发送成功"); } else {
 * alert("指令发送失败"); } }); }
 */

// wjd发送文字
function sendTouyText(classId) {
	var encondeURIsendText='';
	var sendText = $("#touyTextInfo" + classId).val();
	if (sendText.length == 0) {
		alert("请输入中文消息");
		return false;
	}
	// 如果是奇数个中文 后面添加中文; 偶数个添加英文的;
	if (sendText.length % 2 == 1) {
		sendText = sendText + "&nbsp;";
	} else {
		sendText = sendText + " ";
	}
	var url = "Cmd?a=sendTouyText&class_id=" + classId + "&sendText="
			+ encodeURI((sendText));
	$.getJSON(url, function(resp) {

		if (null != resp && typeof (resp.result) != "undefined"
				&& resp.result == 1) {
			// console.log("send cmd[" + method + "] success");
			alert("中控指令发送成功");
		} else {
			alert("中控指令发送失败");
		}
	});
}
// wjd 修改教室Ip
function updateClassRoomIp(formId, obj) {
	tempSendSuccess = false;
	var tempObj2 = obj.parents('.table').find('.class_id');
	var state = checkInputValue(tempObj2);
	if (state && !state.value) {
		alert(state.msg);
		return;
	}
	var tempObj = obj.parents('.table').find('.class_ip');
	var state = checkInputValue(tempObj, 'ip');
	if (state && !state.value) {
		alert(state.msg);
		return;
	}
	var $form = $("form[id='classroom" + formId + "']");
	var url = "addOrUpdateClassroom";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined") {
			if (resp.result) {
				alert("操作成功");
				tempSendSuccess = true;
				$("#ip-config" + formId).dialog('close');
			} else {
				if(resp.message){
					alert(resp.message);

				} else{
					
					alert("操作失败");
				}
			}
		}
	}, "json");
}
// wjd 查看当天课表
function showKeBiao(classId) {
	var url = "KeBiao?classRoomId=" + classId;
	$.post(url, '', function(resp) {
		if (typeof (resp) != "undefined") {
			$kebiaodiv = $("#kebiaoDetail" + classId);
			$kebiaodiv.empty();
			for ( var i in resp) {
				$kebiaodiv.append("<li>" + resp[i] + "</li>");
			}
			$("#kebiao" + classId).dialog();
		}
	}, "json");
}
// wjd 打开操作中控框
function openFirstWindow(windowId, classId, operation) {
	$operationdiv = $(windowId + classId);
	// guanbi jian kong chuang kou
	$("#videoclass" + classId).hide();
	// 如果原来是隐藏的
	if (!$operationdiv.is(":visible")) {
		$(".firstWindow").each(function(index, value) {
			$(this).hide();
		});
		$('.firstWindowNoBorder').hide();
		$operationdiv.show();
		// 监控预览显示
		if ($('.firstWindowNoBorder') && $('.firstWindowNoBorder').length > 0
				&& $('.firstWindowNoBorder').is(":visible")) {
			$('#videoclass' + classId).show();
		}
		// 显示div里所有button
		$operationdiv.find("button [display='not']").each(function(index, val) {
			$(this).show();
		});
		if (operation != undefined && operation == 'open')
			$operationdiv.find(".open").show();
		else if (operation != undefined && operation == 'close')
			$operationdiv.find(".close").show();
	} else { // 如果原来是显示的 那么隐藏
		$operationdiv.hide();
	}
}

// wjd 修改监控
function updateAirConditionhongwai(formId) {
	
	
	
	var $form = $("form[id='airConditionerFrom" + formId + "']");
	
	//var select=$form.find('.input-container select');
	var select=$form.find('select');
//	for(var i= 0; i<select.length;i++){
//		if(select.eq(i).val()=='default'){
//			alert('请选择空调类型');
//			return;
//		}
//	}
	
/*	if(checkBox.length<=0){
		
		alert('请选择空调类型');
		return;
}*/
	
	var url = "AirCondition";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
		if (typeof (resp.result) != "undefined") {
			alert("操作成功");
			var data = localStorage.getItem("#showAirConditionerHongWai" + formId);
			data = JSON.parse(data);
			if(data){
				if(resp.data){
					
					data.kongtype=resp.data;
				}
			}
			
			if(data){
				
				localStorage.setItem("#showAirConditionerHongWai" + formId,JSON.stringify(data));
			}
			
			
			$("#showAirConditionerHongWai" + formId).dialog('close');
		}
	}, "json");
}
// wjd 修改监控
function updateTouy(formId, obj) {
	tempSendSuccess = false;
	var tempObj = obj.closest('.table').find('select');
//	console.log(tempObj);
//	console.log(tempObj.val());
	if (!tempObj.val()) {
		alert('投影仪类型不能为空！');
		return;
	}
	var $form = $("form[id='touyForm" + formId + "']");
	var url = "Touy";
	var postData = $form.serialize();
	$.post(url, postData, function(resp) {
//		if (typeof (resp.result) != "undefined") {
//			alert("操作成功");
//			tempSendSuccess = true;
//			$("#touyControl" + formId).dialog('close');
//		}
		
			if(resp.result){
				var data = localStorage.getItem("#touyControl" + formId);
				data = JSON.parse(data);
				if(data){
					if(resp.data){
						data.touytype = resp.data;
					}
				}
				localStorage.setItem("#touyControl" + formId,JSON.stringify(data));
				alert("操作成功");
				tempSendSuccess = true;
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
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(msg) {
			resp = msg.rows;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
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
	var html = " <div id='addBuildHtml' style='height:100%;border:1px solid #000;'>"
			+ $('.addBuildHtml').html() + "</div>";
	var wform = new Boxy(html, {
		"title" : "添加大楼",
		"modal" : false,
		"draggable" : true,
		"unloadOnHide" : true,
		afterHide : function() {
		},
		afterShow : function() {
			$('.addBuildSubmit').off('click');
			$('.addBuildSubmit').on('click',function(){submitBuild();})
		}
	});
}
function submitBuild() {
	var url = "addOrUpdateBuild";
	var postData = $('#addBuildHtml').find("input");
	var tempObj = $('#addBuildHtml').find('.build_input');
	var state = checkInputValue(tempObj, 'build');
	if (state && !state.value) {
		alert(state.msg);
		return;
	}
	// if (postData.val().trim() == '') {
	// alert("大楼名称不能为空");
	// } else {
$('.addBuildSubmit').attr('disabled',true);
window.isUpdateBuild=true;
	$.post(url, postData,
			function(resp) {
				if (typeof (resp.result) != "undefined" && resp.result == 1) {
					buildList[buildList.length] = resp.buildinfo;
					$('#menu-container').append(
							Build.getBuildHtml(resp.buildinfo.name,
									resp.buildinfo.id));
					
				var tid=$('#menu-container').find(".menu-items:visible").prev().attr('id');
					Build.init(function(){
						$('#'+tid).trigger("click");
					});
					alert("大楼添加成功！")
				} else {
					alert('大楼添加失败！')
				}
				$('.addBuildSubmit').attr('disabled',false);
				window.isUpdateBuild=false;
			}, "json");
	// }
}

function deleteClass(class_id) {
	var url = "deleteClassroom?id=" + class_id;
	$.post(url, {}, function(resp) {
		if (typeof (resp.result) != "undefined" && resp.result == 1) {
			$('#class' + class_id).remove();
			classList.splice(0, classList.length);
			alert("操作成功");
		} else {
			alert("操作失败");
		}

	}, "json");
}
function deleteBuildOrFloor(id, opt, buildId) {
	if (id == null || id == undefined) {
		alert("请选择要操作的记录");
		$('.addFloorSubmit').attr('disabled',false);
		return false;
	}
	var url = "deleteBuild?id=" + id;
	if (opt == 2)
		url = "deleteFloor?id=" + id;
	window.isUpdateBuild=true;
	
	$.get(url,function(resp) {
		if (typeof (resp.result) != "undefined" && resp.result == 1) {
			if (opt == 2) {
				$('#floor_' + id).remove();
				resetFloor(buildId, "floorDelHtml");

			} else {
				$('#build_' + id).parent().remove();
				if (selectBuildId == id) {
					$("#class_container").empty();
				}
//				console.log(selectBuildId);
//				console.log(id);
				$('#buildDelHtml').find("select").empty();
				$.each(getBuildList(), function(i, item) {
					$('#buildDelHtml').find("select").append(
							"<option value='" + item.id + "'>" + item.name
									+ "</option>");
				});

			}
			classList.splice(0, classList.length);
			/* Menphis */
//			console.log(selectBuildId);
//			console.log(id);
			alert("操作成功");
		} else {
			alert("操作失败");
		}
		window.isUpdateBuild=false;
		$('.addFloorSubmit').attr('disabled',false);
	}, "json");
}
/** 向服务端发送指令 */
var touyState = {};
function doAction(method, class_id, obj) {
	var alertMsg = '';
	if (method == 'openTouy' || method == 'closeTouy') {
		if (touyState[class_id]) {
			alert('请等二分钟之后再控制');
			return;
		}
		$("#" + class_id + method).hide();
		touyState[class_id] = 1;
		// console.log(touyState);
		setTimeout(function() {
			touyState[class_id] = 0;
			$("#" + class_id + method).show();
		}, 120000);
		alertMsg = '投影';
	}
	if (method == 'closeOtherSwitch' || method == 'openOtherSwitch') {
		alertMsg = '其他设备电闸';
		if (obj) {
			var tempState = obj.parent().parent().find('.dev-iteh .touy_on')
					.is(':visible');
			// console.log(tempState);
			if (tempState) {
				alert('请先关闭投影！');
				return;
			}
		}
	}
	/*if (method == 'closeAirSwitch' || method == 'openAirSwitch') {
		
		if (obj.closest('.tr').find('.air_conditioner_on').is(':visible')) {
			alert("请先关闭空调");
			return;
		}
		alertMsg = '空调电闸';
	}*/
	if (method == 'closeCenterCtrl') {
		alertMsg = '中控';
	}
	if (method == 'closeAirConditioner'
			|| method == 'setAirConditioner26Degrees'
			|| method == 'setAirConditioner16Degrees'
			|| method == 'setAirConditioner23Degrees') {
		if (obj.parents('.tr').find('.air_switch_on').is(':visible')) {
			alertMsg = '空调';
		} else {
			alert("请先打开空调电闸");
			return;
		}
	}
	var url = "Cmd?a=common&method=" + method + "&build_id=" + selectBuildId
			+ "&floor_id=" + selectFloorId + "&class_id=" + class_id;
	$.getJSON(url, function(resp) {

		if (null != resp && typeof (resp.result) != "undefined"
				&& resp.result == 1) {
			console.log("send cmd[" + method + "] success");
			alert(alertMsg + "指令发送成功");
		} else {
			alert(alertMsg + "指令发送失败");
		}
	});
}
// 0828
/** 向服务端发送指令 */
function headAction(method, build_id, floorId, opt) {
	var url = "Cmd?a=" + method + "&method=" + method + "&build_id=" + build_id + "&floor_id="
			+ floorId + "&opt=" + opt;
	$.getJSON(url, function(resp) {
		if (null != resp && typeof (resp.result) != "undefined"
				&& resp.result == 1) {
//			console.log("send cmd[" + method + "] success");
			alert("指令发送成功");
		} else {
			alert("指令发送失败");
		}
	});
}

/**
 * TODO2018
 * 处理jQueryUI弹窗缓存问题
 */
function openDialog(id) {
	$(".ui-dialog.ui-widget").remove();
	var tempInputObj = $(id).find('input');
	var tempArr = []
	for (var i = 0; i < tempInputObj.length; i++) {
		tempArr.push(tempInputObj[i].value);
	}
	var tempState = false;
	$(id).find('.dev').click(function() {
		console.log(1);
		tempState = true;
		$(this).off('click');
	});
	
/*	if($(id).find('.select').length>0){
		
		regMultiSelect(id)
	}*/
	var data = localStorage.getItem(id);
	if(data && data != null && data != ""){
		
		var class_item = JSON.parse(data);
		if(id && id.indexOf("touyControl")!= -1){
			$("#touyTypeId" + class_item.id).val(class_item.touytype);
			var touyType = $(id).find(".touy_class");
			touyType.val(class_item.touytype);
		} else {
		
			if(class_item.kongtype){
				var str = [];
				str = class_item.kongtype.split(",");
				var selectResult = $(id).find('select');
				selectResult.find("option").removeAttr("selected");
				if(str){
					var kongtype1 = $(id).find("#kongtype1");
					var kongtype2 = $(id).find("#kongtype2");
					
					if(str.length==2){
						if(str[0] == "格力"){
							kongtype1.find('#gl0').attr("selected",true);
							kongtype1.val(str[0]);
						}
						if(str[0] == '美的'){
							kongtype1.find('#md3').attr("selected",true);
							kongtype1.val(str[0]);
						}
						if(str[1] == "格力"){
							kongtype2.find('#gl0').attr("selected",true);
							kongtype2.val(str[1]);
						}
						if(str[1] == '美的'){
							kongtype2.find('#md3').attr("selected",true);
							kongtype2.val(str[1]);
						}
					}
					
				}
			}
		}
	}
	
	$(id).dialog({
		close : function(a, b, c) {
//			console.log(!tempState && tempSendSuccess);
			if (!tempState || !tempSendSuccess) {
				for (var i = 0; i < tempArr.length; i++) {
					tempInputObj[i].value = tempArr[i];
				}
			}
		}
	});
}
// TODO
/*function (checkedObj){
	
	var container = $(checkedObj).closest(".select");
	var resultVal = container.find(".select-result");  
	var selectArea = container.find(".select-area");
	var value = selectArea.find('li[data-key="'+$(checkedObj).attr("data-key")+'"]').text();
	var template = $('<div class="selectedItem" data-key="'+$(checkedObj).attr("data-key")+'" data-val="'+value+'"><span class="delBtn">X</span><span>'+value+'</span></div>');
	selectArea.find('li[data-key="'+$(checkedObj).attr("data-key")+'"]').addClass('selected').append('<span style="float:right">√</span>');
	resultVal.append(template);
	
}
function regMultiSelect(id) {
	
	var selectResult = $(id).find('.select-result'),
		selectedArea = $(id).find('.select-area'),
		selectOptions=$(id).find('.select-option');
		inputContainer=$(id).find('.input-container')
	selectResult.on('click', function() {
		selectedArea.toggle();
	});
	selectOptions.on('click', function () {
		var key = $(this).attr('data-key'),
			val = $(this).attr('data-val'),
			text = $(this).text();

		if ($(this).hasClass('selected')) {
			delSelected(key);
			return;
		}

		$(this).addClass('selected').append($('<span style="float:right">&radic;</span>'))
		var delBtn = $('<span class="delBtn">').text('X').on('click', function (e) { delSelected(key); e.stopPropagation(); }),
			textSpan = $('<span>').text(text),
			div = $('<div class="selectedItem" data-key="' + key + '" data-val ="' + val + '">').append(delBtn, textSpan).on('click', function (e) { e.stopPropagation() });
		selectResult.append(div);
		inputContainer.find('input[data-key='+key+']').prop('checked',true);
	});
	
	function delSelected(key) {
		var item = selectResult.find("div.selectedItem[data-key=" + key + "]");
		item.remove();
		selectedArea.find('.select-option[data-key=' + key + ']').removeClass('selected').find('span').remove();
		inputContainer.find('input[data-key='+key+']').prop('checked',false);
	}
	var data = localStorage.getItem(id);
	if(data && data != null && data != ""){
		
		var class_item = JSON.parse(data);
		if ( class_item.kongtype.indexOf("格力_0") > -1 || class_item.kongtype.indexOf("格力_1") > -1 
				|| class_item.kongtype.indexOf("美的_2") > -1 ||class_item.kongtype.indexOf("美的_3") > -1) {
			var index1 = class_item.kongtype.indexOf("格力_0");
			var index2 = class_item.kongtype.indexOf("格力_1");
			var index3 = class_item.kongtype.indexOf("美的_2");
			var index4 = class_item.kongtype.indexOf("美的_3");
			selectResult.find(".selectedItem").remove();
			selectedArea.find("li").find("span").remove();
			selectedArea.find(".selected").removeClass("selected");
			
			inputContainer.find("input").prop("checked",false);
			if (index1 > -1) {
//				$("#showAirConditionerHongWai"+class_item.id).find("#gl0").attr("checked",'checked');
				$("#showAirConditionerHongWai"+class_item.id).find("#gl0").prop("checked",true);
				($("#showAirConditionerHongWai"+class_item.id).find("#gl0"));
			} 
			if (index2 > -1) {
//				 $("#showAirConditionerHongWai"+class_item.id).find("#gl1").attr("checked",'checked');
				$("#showAirConditionerHongWai"+class_item.id).find("#gl1").prop("checked",true); 
				 ($("#showAirConditionerHongWai"+class_item.id).find("#gl1"));
			}
			if(index3 != -1){
//				$("#showAirConditionerHongWai"+class_item.id).find("#md2").attr("checked",'checked');
				$("#showAirConditionerHongWai"+class_item.id).find("#md2").prop("checked",true);
				($("#showAirConditionerHongWai"+class_item.id).find("#md2"));
			}
			if(index4 > -1){
				
//				$("#showAirConditionerHongWai"+class_item.id).find("#md3").attr("checked",'checked');
				$("#showAirConditionerHongWai"+class_item.id).find("#md3").prop("checked",true);
				($("#showAirConditionerHongWai"+class_item.id).find("#md3"));
			}
		}
		
		$("#touyTypeId" + class_item.id).val(class_item.touytype);
	}
}
*/
/**
 * 验证处理
 */
function checkInputValue(obj, type) {
	// console.log(obj.val());
	if (!obj.val()) {
		obj.addClass('input-check-error');
		return {
			value : false,
			msg : '输入值不能为空！'
		};
	} else {
		obj.removeClass('input-check-error');
	}
	switch (type) {
	case 'ip':
		var strRegex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		var re = new RegExp(strRegex);
		if (!re.test(obj.val()) || obj.val() == '') {
			obj.addClass('input-check-error');
			return {
				value : false,
				msg : '输入格式错误！'
			};
		} else {
			obj.removeClass('input-check-error');
			return {
				value : true
			};
		}
		break;
	case 'build':
		var tempbuildList = getBuildList();
		var tempState = false;
		for (var i = 0; i < tempbuildList.length; i++) {
			if (tempbuildList[i].name == obj.val()) {
				tempState = true;
			}
		}
		if (tempState) {
			obj.addClass('input-check-error');
			return {
				value : false,
				msg : '大楼名已使用！'
			};
		} else {
			obj.removeClass('input-check-error');
			return {
				value : true
			};
		}
		break;
	case 'floor':
		var build_id = obj.parents('#addFloorHtml').find('.building_select')
				.val();
		var floorList = getFloorList(build_id);
		var tempState = false;
		for (var i = 0; i < floorList.length; i++) {
			if (floorList[i].name == obj.val()) {
				tempState = true;
			}
		}
		if (tempState) {
			obj.addClass('input-check-error');
			return {
				value : false,
				msg : '楼层已使用！'
			};
		} else {
			obj.removeClass('input-check-error');
			return {
				value : true
			};
		}
		break;
	case 'class':
		var build_id = obj.parents('#addClassHtml').find('.building_select')
				.val();
		var floor_id = obj.parents('#addClassHtml').find('.floor_select').val();
		var class_id = obj.parents('#addClassHtml').find('.class_select').val();
		var buildList = getBuildList();
		var floorList = getFloorList(build_id);
		var classList = getClasses(build_id, floor_id)
		var temp = classList.concat(buildList.concat(floorList));
		var tempState = false;
		for (var i = 0; i < temp.length; i++) {
			if (temp[i].name == obj.val()) {
				tempState = true;
			}
		}
		if (tempState) {
			obj.addClass('input-check-error');
			return {
				value : false,
				msg : '教室名已使用！'
			};
		} else {
			obj.removeClass('input-check-error');
			return {
				value : true
			};
		}
		break;
	case 'class_IP':
		var strRegex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		var re = new RegExp(strRegex);
		if (!re.test(obj.val())) {
			obj.addClass('input-check-error');
			return {
				value : false,
				msg : '输入格式错误！'
			};
		} else {
			obj.removeClass('input-check-error');
		}
		var building_select = obj.parents('#addClassHtml').find(
				'.building_select').val();
		var floor_select = obj.parents('#addClassHtml').find('.floor_select')
				.val();
		var classList = getClasses(building_select, floor_select);
		var tempObjArr = obj.parents('#addClassHtml').find('.ipInput');
		var tempState = false;
		for (var i = 0; i < tempObjArr.length; i++) {
			var temp1 = false;
			for (var j = 0; j < tempObjArr.length; j++) {
				if (re.test(tempObjArr[i].value)
						&& re.test(tempObjArr[j].value)
						&& tempObjArr[j].value == tempObjArr[i].value && i != j) {
					temp1 = true;
				}
			}
			for (var j = 0; j < classList.length; j++) {
				if (tempObjArr[i].value == classList[j].classCenterIp
						|| tempObjArr[i].value == classList[j].classTouy
						|| tempObjArr[i].value == classList[j].classIp) {
					temp1 = true;
				}
			}
			
			// TODO
			if (temp1) {
				obj.parents('#addClassHtml').find('.ipInput').eq(i).addClass(
						'input-check-error');
				
				tempState = true;
				
			} else {
				obj.parents('#addClassHtml').find('.ipInput').eq(i)
						.removeClass('input-check-error');
			}
		}
		
		var statsdfe = false;
		if(!statsdfe){
			$.ajaxSettings.async = false;
			var valueMsg=null;
			$.getJSON("checkedByIP?ip=" + obj.val(), function(resp) {
				if(!resp.result){
					statsdfe = true;
					tempState = true;
					if(!obj.hasClass('input-check-error')){
						obj.addClass('input-check-error');
					}
					if (tempState) {
						valueMsg= {
							value : false,
							msg : 'IP已被使用！'
						};
					} else {
						obj.removeClass('input-check-error');
						valueMsg= {
							value : true
						};
					}
				}else{
					if (tempState) {
						valueMsg= {
							value : false,
							msg : 'IP已被使用！'
						};
					} else {
						obj.removeClass('input-check-error');
						valueMsg= {
							value : true
						};
					}
				}
			});
		}
		;
		$.ajaxSettings.async = true;
		return valueMsg;
		break;
	}
}
/**
 * 打开监控
 */
function openMonitor(obj, classId) {
	$(obj).hide();
	$(obj).prev().show();
	// var vlc = $(obj).parent().parent().parent().parent().find(".videoclass");
	$("#videoclass" + classId).show();
	$("#monitor" + classId + " [id=open_jk]").hide();
	$("#monitor" + classId + " [id=jk11]").hide();
	$("#monitor" + classId).removeClass("firstWindow").addClass(
			"firstWindowNoBorder");
	/* $("#monitor" + classIs+"[id=td firstWindow]").hide(); */
	$("#monitor" + classId + " [class=clabel4]").removeClass("clabel4")
			.addClass("clabel4nobg");
}

/**
 * 关闭监控
 */
function closeMonitor(obj, classId) {
	$(obj).hide();
	$("#videoclass" + classId).hide();
	$(obj).prev().show();
	$(obj).next().show();
	$("#monitor" + classId).removeClass("firstWindowNoBorder").addClass(
			"firstWindow");
	var objItem = $(vlc).find("object")[0];

	if (typeof (objItem.playlist) != "undefined")
		objItem.playlist.stop();
}

function listenStatus() {
	var time = new Date().getTime();
	if (selectFloorId != ""
			&& (listen_status == 0 || time - listen_tm > 1200)) {
		listen_status = 1;
		$
				.getJSON(
						"DevListen?floor=" + selectFloorId,
						function(resp) {
							$
									.each(
											resp,
											function(class_key, item) {
												listen_status = 0;
												for ( var key in item) {
													// console.log(key+":"+"public/img/"+key+"_"+item[key]+".png");
													// wjd0823 if (key !=
													// "monitor") {
													if (item[key] == "on") {
														$('#class' + class_key)
																.find(
																		'.'
																				+ key
																				+ "_offline")
																.hide();
														$('#class' + class_key)
																.find(
																		'.'
																				+ key
																				+ "_off")
																.hide();
														$('#class' + class_key)
																.find(
																		'.'
																				+ key
																				+ "_on")
																.show();
														if ($
																.inArray(
																		class_key,
																		withoutNetClass) > -1) {
															withoutNetClass
																	.remove($
																			.inArray(
																					class_key,
																					withoutNetClass));
														}
													} else if (item[key] == "offline") {
														$('#class' + class_key)
																.find(
																		'.'
																				+ key
																				+ "_on")
																.hide();
														$('#class' + class_key)
																.find(
																		'.'
																				+ key
																				+ "_off")
																.hide();
														$('#class' + class_key)
																.find(
																		'.'
																				+ key
																				+ "_offline")
																.show();
														if (key == "air_conditioner"
																&& $
																		.inArray(
																				class_key,
																				withoutNetClass) == -1) {
															alert($(
																	'#'
																			+ class_key
																			+ "roomName")
																	.val()
																	+ "断网了");
															withoutNetClass
																	.push(class_key);
														}
													} else {
														$('#class' + class_key)
																.find(
																		'.'
																				+ key
																				+ "_offline")
																.hide();
														$('#class' + class_key)
																.find(
																		'.'
																				+ key
																				+ "_on")
																.hide();
														$('#class' + class_key)
																.find(
																		'.'
																				+ key
																				+ "_off")
																.show();
														if ($
																.inArray(
																		class_key,
																		withoutNetClass) > -1) {
															withoutNetClass
																	.remove($
																			.inArray(
																					class_key,
																					withoutNetClass));
														}
													}
													// }
												}
											});
						});
	}
	// console.log("listen " + floor);
}

// 传参方式监听
function listenStatusParam(enterTime) {
	var time = new Date().getTime();
	if (selectFloorId != ""
			&& (listen_status == 0 || time - listen_tm > 1200)) {
		listen_status = 1;
		// var tip_status = 0,; 111
		$.getJSON("DevListen?floor=" + selectFloorId, function(resp) {
			$.each(resp,
					function(class_key, item) {
						listen_status = 0;
						for ( var key in item) {
							// console.log(key+":"+"public/img/"+key+"_"+item[key]+".png");
							// wjd0823 if (key != "monitor") {
							if (item[key] == "on") {
								$('#class' + class_key).find(
										'.' + key + "_offline").hide();
								$('#class' + class_key)
										.find('.' + key + "_off").hide();
								$('#class' + class_key).find('.' + key + "_on")
										.show();
								if (key == 'monitor') {

									$('#class' + class_key).find('#jk_view')
											.show();
								}
							} else if (item[key] == "offline") {
								$('#class' + class_key).find('.' + key + "_on")
										.hide();
								$('#class' + class_key)
										.find('.' + key + "_off").hide();
								$('#class' + class_key).find(
										'.' + key + "_offline").show();
								if (key == 'monitor') {

									$('#class' + class_key).find('#jk_view')
											.hide();
								}

								/*
								 * tip_status == 1; if(tip_status = 1 &&
								 * time-enterTime>60000){ alert("已经断电");
								 */
							} else {
								$('#class' + class_key).find(
										'.' + key + "_offline").hide();
								$('#class' + class_key).find('.' + key + "_on")
										.hide();
								$('#class' + class_key)
										.find('.' + key + "_off").show();
								if (key == 'monitor') {
									$('#class' + class_key).find('#jk_view')
											.show();

								}

							}
							// }
						}
					});
		});
		/*
		 * if(tip_status = 1 && time-enterTime>60000){ 111 alert("已经断电"); }
		 */
	}
	// console.log("listen " + floor);
}

function getNetInfo() {
	if(window.isUpdateBuild){
		return;
	}
	//var tempbuildList = getBuildList();
	
	
	
	var url = "selectAllBuild";
	//buildList = syncPost(url, {});
	$.post(url,{},function(resp){
		var tempBuildList=resp.rows;
		for (var i = 0; i < tempBuildList.length; i++) {
			//var tempfloorList = getFloorList(tempbuildList[i].id);
			//netTips(tempfloorList);
			var httpUrl = "selectAllFloor?buildId=" + tempBuildList[i].id;
			$.post(httpUrl,{},function(data){
//				console.log(data);
				if(!!data && !!data.rows){
				var tempfloorList=data.rows;
				netTips(tempfloorList)
				}
			},'json');}
	},'json');
/*	for (var i = 0; i < tempbuildList.length; i++) {
		
		
	var tempfloorList = getFloorList(tempbuildList[i].id);
	netTips(tempfloorList);
	}*/
	


	// console.log("listen " + floor);
}
function netTips(floorList){
for ( var index in floorList) {
		$.getJSON("DevListen?floor=" + floorList[index].id, function(resp) {
			$.each(resp, function(class_key, item) {
				for ( var key in item) {
					if (key == "air_conditioner") {

						if (item[key] == "on") {
							if ($.inArray(class_key, withoutNetClass) > -1) {
								withoutNetClass.remove($.inArray(class_key,
										withoutNetClass));
							}
						} else if (item[key] == "offline") {
							if ($.inArray(class_key, withoutNetClass) == -1) {
								alert(resp[class_key + "className"] + "断网了");
								withoutNetClass.push(class_key);
							}
						} else {
							if ($.inArray(class_key, withoutNetClass) > -1) {

								withoutNetClass.remove($.inArray(class_key,
										withoutNetClass));
							}
						}

					}
				}
			});
		});
	}
}
// open building select window opt=1 toOpen 2 toClose
function showBuildControlBox(opt) {
	$('table.boxy-wrapper').remove();
	var $build = $('.addFloorHtml');
	$build.find(".floor").hide();
	var html = " <div id='showBuildControlBox' style='height:100%;border:1px solid #000;'>"
			+ $build.html() + "</div>";
	// $("table").each(function(index, value) {// sou you tan chu dou jia shang
	// gai
	// // duan daima
	// $(value).hide();
	// });
	var titleTail = "开";
	if (opt == 2) {
		titleTail = "关";
	}
	var wform = new Boxy(html, {
		"title" : "整栋" + titleTail,
		"modal" : false,
		"draggable" : true,
		"unloadOnHide" : true,
		afterHide : function() {
		},
		afterShow : function() {
			$('.addFloorSubmit').off('click');
			$('.addFloorSubmit').on('click',function(){
				var tempObj = $(this).parents('#showBuildControlBox')
				.find('select');
			var state = checkInputValue(tempObj, 'floor');
			if (state && !state.value) {
				alert(state.msg);
				return;
			}
			if (confirm("你确定要整栋楼 " + titleTail + "吗?")) {
				var buildId = $('#showBuildControlBox').find(
						"select").val();
				headAction("buildControl", buildId, '', opt);
			}})
		}
	});
	$.each(getBuildList(), function(i, item) {
		$('#showBuildControlBox').find("select").append(
				"<option value='" + item.id + "'>" + item.name + "</option>");
		// $('#addBuildHtml select').append("<option
		// value='"+item.id+"'>"+item.name+"</option>");
	});
	$('#showBuildControlBox select').val(selectBuildId);
}
function showFloorControlBox(opt) {
	$('table.boxy-wrapper').remove();
	var $classHtml = $('.addClassHtml');
	$classHtml.find(".clazzIp").each(function(index, val) {
		$(val).hide();
	});
	var html = " <div id='showFloorControlHtml' style='height:100%;border:1px solid #000;'>"
			+ $('.addClassHtml').html() + "</div>";
	// $("table").each(function(index, value) {// sou you tan chu dou jia shang
	// gai
	// // duan daima
	// $(value).hide();
	// });
	var titleTail = "开";
	if (opt == 2) {
		titleTail = "关";
	}
//	console.log(titleTail);
	var currentBuildId = '';
	var wform = new Boxy(
			html,
			{
				"title" : "整层" + titleTail,
				"modal" : false,
				"draggable" : true,
				"unloadOnHide" : true,
				afterHide : function() {
				},
				afterShow : function() {
					$('.addClassSubmit')
							.click(
									function() {
										var tempObj = $(this).parents(
												'#showFloorControlHtml').find(
												'select');
//										console.log(tempObj);
										if (!tempObj[0].value
												&& !tempObj[1].value) {
											alert('请选择大楼跟楼层!');
											return;
										}
										if (!tempObj[0].value) {
											alert('请选择大楼!');
											return;
										}
										if (!tempObj[1].value) {
											alert('请选择楼层!');
											return;
										}
										if (confirm("你确定要整层楼 " + titleTail
												+ "吗?")) {
											var floorId = $(
													"#showFloorControlHtml")
													.find(
															"select[name='class_floor']")
													.val();
											headAction("floorControl",
													currentBuildId, floorId,
													opt);
										}
									});
					$("#showFloorControlHtml select[name='class_build']")
							.change(
									function() {
										currentBuildId = $(this).val();
										resetFloor(currentBuildId,
												"showFloorControlHtml");
									});
					$.each(getBuildList(), function(i, item) {
						$('#showFloorControlHtml').find(
								"select[name='class_build']").append(
								"<option value='" + item.id + "'>" + item.name
										+ "</option>");
					});
					$("#showFloorControlHtml select[name='class_build']").val(
							currentBuildId);
					resetFloor($("#showFloorControlHtml").find(
							"select[name='class_build']").val(),
							"showFloorControlHtml");
				}
			});
}
function showFloorDelBox() {
	$('table.boxy-wrapper').remove();
	var $classHtml = $('.addClassHtml');
	$classHtml.find(".clazzIp").each(function(index, val) {
		$(val).hide();
	});
	var html = " <div id='floorDelHtml' style='height:100%;border:1px solid #000;'>"
			+ $('.addClassHtml').html() + "</div>";
	$("table").each(function(index, value) {// sou you tan chu dou jia shang gai
		// duan daima
		$(value).hide();
	});
	var currentBuildId = '';
	var wform = new Boxy(html, {
		"title" : "删除楼层信息",
		"modal" : false,
		"draggable" : true,
		"unloadOnHide" : true,
		afterHide : function() {
		},
		afterShow : function() {
			$('.addClassSubmit').click(
					function() {
						if (confirm("你确定要删除该层楼吗?")) {
							var currentFloorId = $("#floorDelHtml").find(
									"select[name='class_floor']").val();
							var buildId = $("#floorDelHtml").find(
									"select[name='class_build']").val();
							deleteBuildOrFloor(currentFloorId, 2, buildId);
							var ActivedfloorId = $('.menu-item-active').attr(
									'id');
							var tempIndex = ActivedfloorId.indexOf('_');
							var tempId = ActivedfloorId
									.substring(tempIndex + 1)
							if (tempId === currentFloorId) {
								$('#class_container').empty();
							}
						}
					});
			$("#floorDelHtml select[name='class_build']").change(function() {
				currentBuildId = $(this).val();
				resetFloor(currentBuildId, "floorDelHtml");
			});
			$.each(getBuildList(), function(i, item) {
				$('#floorDelHtml').find("select[name='class_build']").append(
						"<option value='" + item.id + "'>" + item.name
								+ "</option>");
			});
			$("#floorDelHtml select[name='class_build']").val(currentBuildId);
			resetFloor($("#floorDelHtml").find("select[name='class_build']")
					.val(), "floorDelHtml");
		}
	});
}
function showBuildDelBox() {
	$('table.boxy-wrapper').remove();
	var $build = $('.addFloorHtml');
	$build.find(".floor").hide();
	var html = " <div id='buildDelHtml' style='height:100%;border:1px solid #000;'>"
			+ $build.html() + "</div>";
	$("table").each(function(index, value) {// 这三段代码是隐藏页面上面那些控件的
		$(value).hide(); //
	}); //
	var wform = new Boxy(html, {
		"title" : "删除整栋楼信息",
		"modal" : false,
		"draggable" : true,
		"unloadOnHide" : true,
		afterHide : function() {
		},
		afterShow : function() {
			$('.addFloorSubmit').off('click');
			$('.addFloorSubmit').on('click',function(){
				
				if (confirm("你确定要删除整栋楼信息吗?")) {
					$('.addFloorSubmit').attr('disabled',true);
					var buildId = $('#buildDelHtml').find("select").val();
					deleteBuildOrFloor(buildId, 1);
				}
			})
		/*	$('.addFloorSubmit').click(function() {
				if (confirm("你确定要删除整栋楼信息吗?")) {
					var buildId = $('#buildDelHtml').find("select").val();
					deleteBuildOrFloor(buildId, 1);
				}
			});*/
		}
	});
	$.each(getBuildList(), function(i, item) {
		$('#buildDelHtml').find("select").append(
				"<option value='" + item.id + "'>" + item.name + "</option>");
		// $('#addBuildHtml select').append("<option
		// value='"+item.id+"'>"+item.name+"</option>");
	});
	$('#buildDelHtml select').val(selectBuildId);
}
// 创建一个函数,用于返回一个无参数函数
function _listenStatusParam(enterTime) {
	return function() {
		listenStatusParam(enterTime);
	}
}
(function(){
	$(function(){
		$(document).on('click','#menu-container .menu-title',function(ev){
			ev.preventDefault();
	  		ev.stopPropagation();
	  		clickMenu($(this));
		});
	});
}).call(this);

/*function toggleSelectArea(){
	$('.select-area').toggle();
}

function selectOptionClick(){
	 var e= window.event || arguments.callee.caller.arguments[0];
	var target=$(e.target);
	var key = target.attr('data-key'),
	val =target.attr('data-val'),
	text = target.text();

if (target.hasClass('selected')) {
	delSelected(key);
	return;
}

target.addClass('selected').append($('<span style="float:right">&radic;</span>'))
var delBtn = $('<span class="delBtn">').text('X').on('click', function (e) { delSelected(key); e.stopPropagation(); }),
	textSpan = $('<span>').text(text),
	div = $('<div class="selectedItem" data-key="' + key + '" data-val ="' + val + '">').append(delBtn, textSpan).on('click', function (e) { e.stopPropagation() });
$('.select-result').append(div);
	
}

function delSelected(key) {
	var item = $('.select-result').find("div.selectedItem[data-key=" + key + "]");
	item.remove();
	$('.select-area .select-option[data-key=' + key + ']').removeClass('selected').find('span').remove();
}*/

