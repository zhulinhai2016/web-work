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

			if(class_item)
			if(class_item.kongtype!='' && class_item.kongtype){
				kongtypes = class_item.kongtype.split(",");
				html = html.replace(class_item.id + "glnochecked", 'checked');
				$("#class_container").append(html);
				$("#showAirConditionerHongWai"+class_item.id).find("#kongtype1").val(kongtypes[0]);
				$("#showAirConditionerHongWai"+class_item.id).find("#kongtype2").val(kongtypes[1]);
			}else{
				$("#class_container").append(html);
			}
			
		}

		var centerX = 85, centerY = 85, radius = 60, divHalfWidth = 25, divHalfHeight = 25, divNum = 5;
		for (var i = 0; i < divNum; i++) {
			var avd = 360 / divNum;
			// 每一个BOX对应的弧度;
			var ahd = avd * Math.PI / 180;
			var divH = divHalfWidth;

			var tmpX = centerX - Math.round(Math.cos(ahd * i) * radius) - divH;
			var tmpY = centerY - Math.round(Math.sin(ahd * i) * radius) - divH;
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
	},
	resetClasses : function() {
		$('#class_container').empty();
		
			$.each(getClasses(selectBuildId, selectFloorId), function(i, item) {
				var html = $('#face .classes-item')[0].outerHTML;
//				var html ="";
				Build.addClasses(item, html);
			});
			
			// win 0288 
			// TODO 180812 记得修改回去
//			listenStatus();
			
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
		}
	}
};

$(document).ready(function() {


	clickMenuItem(1);
	window.setInterval(listenStatusParam,900000);// 15分钟同步一次状态
	window.setInterval(netTips, 900000);// 15分钟同步一次
//	window.setInterval(listenStatusParam,5000);// 5s同步一次状态
//	window.setInterval(netTips, 5000);// 5s同步一次
	window.isUpdateBuild=false;
	// getClasses();

});

function clickMenu(obj) {
	var class_container=$("#class_container");
	class_container.find("iframe").remove();
	Build.resetClasses();
}

function clickMenuItem(obj) {
	
	var class_container=$("#class_container");
	class_container.find("iframe[tab-id='page1']").remove();
	$(obj).parent().find(".menu-item").removeClass("menu-item-active");
	
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
	var httpUrl = webRoot+"/runInfo/getHomePageData";
	classList = null;
	$.ajax({
		type : "POST",
		url : httpUrl,
		async : false,
//		data : postData,
//		dataType : "json",
//		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(data) {
			if(data){
				data = JSON.parse(data);
				if(data.data){
					classList = data.data;
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
	return classList;
}







var tempSendSuccess;




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

function listenStatus() {
	var time = new Date().getTime();
//	if (selectFloorId != "" && (listen_status == 0 || time - listen_tm > 120000)) {
		listen_status = 1;
		$.getJSON(webRoot+"/DevListen?floor=all",function(resp) {
			$.each(resp,function(class_key, item) {
				listen_status = 0;
				for ( var key in item) {
					if (item[key] == "on") {
						$('#class' + class_key).find('.'+ key+ "_offline").hide();
						$('#class' + class_key).find('.'+ key+ "_off").hide();
						$('#class' + class_key).find('.'+ key+ "_on").show();
						if ($.inArray(class_key,withoutNetClass) > -1) {
							withoutNetClass.remove($.inArray(class_key,withoutNetClass));
						}
					} else if (item[key] == "offline") {
						$('#class' + class_key).find('.'+ key+ "_on").hide();
						$('#class' + class_key).find('.'+ key+ "_off").hide();
						$('#class' + class_key)
								.find('.'+ key+ "_offline").show();
						if (key == "air_conditioner" && $.inArray(class_key,withoutNetClass) == -1) {
							alert($('#'+ class_key+ "roomName").val()+ "断网了");
							withoutNetClass.push(class_key);
						}
					} else {
						$('#class' + class_key).find('.'+ key+ "_offline").hide();
						$('#class' + class_key).find('.'+ key+ "_on").hide();
						$('#class' + class_key)
								.find('.'+ key+ "_off").show();
						if ($.inArray(class_key,withoutNetClass) > -1) {
							withoutNetClass.remove($.inArray(class_key,withoutNetClass));
						}
					}
				}
			});
		});
//	}
}

// 传参方式监听
function listenStatusParam(enterTime) {
	var time = new Date().getTime();
//	if (selectFloorId != ""&& (listen_status == 0 || time - listen_tm > 120000)) {
		listen_status = 1;
		// var tip_status = 0,; 111
		$.getJSON(webRoot+"/DevListen?floor=all", function(resp) {
			$.each(resp,function(class_key, item) {
				listen_status = 0;
				for ( var key in item) {
					if (item[key] == "on") {
						$('#class' + class_key).find('.' + key + "_offline").hide();
						$('#class' + class_key).find('.' + key + "_off").hide();
						$('#class' + class_key).find('.' + key + "_on").show();
						if (key == 'monitor') {
							$('#class' + class_key).find('#jk_view').show();
						}
					} else if (item[key] == "offline") {
						$('#class' + class_key).find('.' + key + "_on").hide();
						$('#class' + class_key).find('.' + key + "_off").hide();
						$('#class' + class_key).find('.' + key + "_offline").show();
						if (key == 'monitor') {

							$('#class' + class_key).find('#jk_view').hide();
						}

					} else {
						$('#class' + class_key).find('.' + key + "_offline").hide();
						$('#class' + class_key).find('.' + key + "_on").hide();
						$('#class' + class_key).find('.' + key + "_off").show();
						if (key == 'monitor') {
							$('#class' + class_key).find('#jk_view').show();

						}

					}
				}
			});
		});
//	}
}


function netTips(floorList){
		$.getJSON(webRoot+"/DevListen?floor=all", function(resp) {
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



