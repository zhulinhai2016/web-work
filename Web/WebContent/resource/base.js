

/**
 * form表单序列化为ajax请求提供参数{"a":"b"}
 */
$.fn.serializeObject = function()    
{    
   var o = {};  
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};

/**
 * 用ajax请求返回的json结果填充table字段（以name属性为匹配标准）
 */
$.fn.tableFillByName = function(data){
	//判断数据是json对象
	if($.isPlainObject(data)){
		var fields = $(this).find("input, textarea");
		$.each(fields, function(){
			if($(this).attr("name")){
				var name = $(this).attr("name");
				if(this.tagName=='INPUT') {
					$(this).val( data[name]==null ? "" : data[name]);
				} else if(this.tagName=='TEXTAREA') {
					$(this).text( data[name]==null ? "" : data[name]);
				};
			}
		});
	}
};



//清空form表单
$.fn.clearForm = function(includeHidden) {
    return this.each(function() {
        $('input,select,textarea', this).clearFields(includeHidden);    //this表示设置上下文环境，有多个表单时只作用指定的表单
    });
};

$.fn.clearFields = $.fn.clearInputs = function(includeHidden) {
    var re = /^(?:color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i; // 正则表达式匹配type属性
    return this.each(function() {
        var t = this.type, tag = this.tagName.toLowerCase(); //获取元素的type属性和标签
        if (re.test(t) || tag == 'textarea') {
            this.value = '';
        }
        else if (t == 'checkbox' || t == 'radio') {
            this.checked = false;
        }
        else if (tag == 'select') {
            this.selectedIndex = -1;
        }
        else if (t == "file") {
            if (/MSIE/.test(navigator.userAgent)) {
                $(this).replaceWith($(this).clone(true));
            } else {
                $(this).val('');
            }
       }
       else if (includeHidden) {
            if ( (includeHidden === true && /hidden/.test(t)) ||
                 (typeof includeHidden == 'string' && $(this).is(includeHidden)) ) {   //true 、false或者样式属性
                this.value = '';
            }
        }
    });
};