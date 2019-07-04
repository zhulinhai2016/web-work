/** 运行数据统计页面 */
var webRoot;
var myChartPie;
var myChartBar;
var myChartLine;
$(function(){
	
	// 初始化日期组件
	$('.start-date').datetimepicker({
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
	$('.end-date').datetimepicker({
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
	
	$(".date-remove").unbind("click");
	$('.date-remove').click(function(){
		$(this).parent().find('.show-date').val('');
		$(this).parent().parent().find('.date-hide').val('');
	});
	
	// 初始化页面选择
	initClassSelect('#dtp_input4');
	
	// 表单搜索
	$("#search").click(function(ev){
		ev.preventDefault();
		ev.stopPropagation();
		initChartPie(myChartPie, myChartBar, myChartLine);
	});
	
	var optionPie = {
		title : {
			text : '启用未用比例',
			x : 150
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'horizontal',
			x : 'left',
			data : [ '中控', '投影', '空调' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : false
				},
				dataView : {
					show : false,
					readOnly : true
				},
				magicType : {
					show : true,
					type : [ 'pie', 'funnel' ],
					option : {
						funnel : {
							x : '25%',
							width : '50%',
							funnelAlign : 'left',
							max : 1548
						}
					}
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		series : [ {
			name : '启用未用比例',
			type : 'pie',
			radius : '45%',
			center : [ '50%', '60%' ],
			data : [ {
				value : 0,
				name : 'wu'
			}]
		} ]
	};
	var optionBar = {
	    title : {
	        text: '教室使用时长排名',
	        subtext: '纯属虚构'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['最高气温','最低气温']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['周一','周二','周三','周四','周五','周六','周日']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} °C'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'最高气温',
	            type:'line',
	            data:[11, 11, 15, 13, 12, 13, 10],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        },
	        {
	            name:'最低气温',
	            type:'line',
	            data:[1, -2, 2, 5, 3, 2, 0],
	            markPoint : {
	                data : [
	                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        }
	    ]
	};
	var optionLine = {
			title : {
				text : '设备运行时间排名',
				x : 150
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'horizontal',
				x : 'left',
				data : [ '中控', '投影', '空调' ]
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : false
					},
					dataView : {
						show : false,
						readOnly : true
					},
					magicType : {
						show : true,
						type : [ 'pie', 'funnel' ],
						option : {
							funnel : {
								x : '25%',
								width : '50%',
								funnelAlign : 'left',
								max : 1548
							}
						}
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			series : [ {
				name : '设备运行时间排名',
				type : 'pie',
				radius : '45%',
				center : [ '50%', '60%' ],
				data : [ {
					value : 0,
					name : 'wu'
				}]
			} ]
		};
	var echarts;
	require.config({
		paths : {
			echarts : webRoot+'/resource/echarts/source'
		}
	});
	require(
			[
			'echarts',
			'echarts/chart/bar', 
			'echarts/chart/pie',
			'echarts/chart/line',
			'echarts/chart/radar',
			'echarts/chart/force', 
			'echarts/chart/chord',
			'echarts/chart/gauge', 
			'echarts/chart/funnel',
			'echarts/chart/eventRiver', 
			'echarts/chart/venn',
			'echarts/chart/treemap', 
			'echarts/chart/tree',
			'echarts/chart/wordCloud', 
			'echarts/chart/heatmap'
			],
			function(ec) {
				echarts = ec;
				// 初始化饼图
				myChartPie = echarts.init($("#main")[0]);
//				myChartPie.showLoading();
				myChartPie.setOption(optionPie);
				
				// 初始化柱状图
				myChartBar = echarts.init($("#usedPwor")[0]);
				myChartBar.setOption(optionBar);
				
				// 初始化折线图
				myChartLine = echarts.init($("#errorInfo")[0]);
				myChartLine.setOption(optionLine);
				initChartPie(myChartPie,myChartBar,myChartLine);
				setTimeout(function(){
					$('#classRoomRankings').removeClass('active');
					$('#classRoomRankings').removeClass('in');
					$('#deviceRankings').removeClass('active');
					$('#deviceRankings').removeClass('in');
				},100);
			}
	);
	
	
	
});
function initChartPie(myChartPie,myChartBar,myChartLine){
	var reqData = $('.search-form').serializeObject();
	myChartPie.clear();
	myChartBar.clear();
	myChartLine.clear();
	myChartPie.showLoading();
	myChartBar.showLoading();
	myChartLine.showLoading();
    $.ajax({
        type : "post",
        url :webRoot + '/runInfo/getHomeRunCount',
        data:reqData,
        success : function(data) {
            //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接鞋for循环
        	// 启用未启用设备统计开始
        	if(data){
        		data = JSON.parse(data);
        		if(data.status){
        			
        		}
        	}
        	
        	myChartPie.hideLoading();
            myChartPie.setOption({
			    title : {
			        text: '启用未启用信息',
			        	x:'center'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			    	orient:'vertical',
			    	x:20,
			        data:['启用','未启用']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: false},
			            dataView : {show: false, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : ['中控','投影','空调','电箱']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'启用',
			            type:'bar',
			            itemStyle: {
					        normal: {
					            barBorderWidth: 6,
					            barBorderRadius:0,
					            label : {
					                show: true, position: 'top'
					            }
					        }
					    },
			            data:data.useOrNotUse.using,
			           /* markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },*/
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			        },
			        {
			            name:'未启用',
			            type:'bar',
			            data:data.useOrNotUse.notUse,
			            itemStyle: {
					        normal: {
					            barBorderWidth: 6,
					            barBorderRadius:0,
					            label : {
					                show: true, position: 'top'
					            }
					        }
					    },
			            /*markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },*/
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			        }
			       
			    ]
			});
            // 启用未启用设备统计结束
            
            // 教室使用时长排名统计开始
            myChartBar.hideLoading();
            if(!data.classRoomRank.names || data.classRoomRank.names.length==0){
            	data.classRoomRank.names=['null'];
            	data.classRoomRank.values=[0];
            }
            myChartBar.setOption(
	    		{
	    		    title : {
	    		        text: '教室使用时长排名',
	    		        x:'center'
	    		    },
	    		    tooltip : {
	    		    	 trigger: 'axis',
	    		         axisPointer:{
	    		             show: true,
	    		             type : 'cross',
	    		             lineStyle: {
	    		                 type : 'dashed',
	    		                 width : 1
	    		             }
	    		         }
	    		    },
	    		    legend: {
	    		    	orient:'vertical',
				    	x:20,
	    		        data:['使用时长']
	    		    },
	    		    toolbox: {
	    		        show : true,
	    		        feature : {
	    		            mark : {show: false},
	    		            dataView : {show: false, readOnly: false},
	    		            magicType : {show: true, type: ['line', 'bar']},
	    		            restore : {show: true},
	    		            saveAsImage : {show: true}
	    		        }
	    		    },
	    		    calculable : true,
	    		    xAxis : [
	    		             {
	    		                 type : 'category',
	    		                 position: 'bottom',
	    		                 boundaryGap: true,
	    		                 axisLine : {    // 轴线
	    		                     show: true,
	    		                     lineStyle: {
	    		                         color: 'green',
	    		                         type: 'solid',
	    		                         width: 2
	    		                     }
	    		                 },
	    		                 axisLabel : {
	    		                     show:true,
	    		                     interval: 'auto',    // {number}
	    		                     rotate: 45,
	    		                     margin: 8,
	    		                     formatter: '{value}',
	    		                     textStyle: {
	    		                         /*color: 'blue',*/
	    		                         fontFamily: 'sans-serif',
	    		                         fontSize: 15,
	    		                         fontStyle: 'italic',
	    		                         /*fontWeight: 'bold'*/
	    		                     }
	    		                 },
	    		                 splitLine:{
	    		                	 show:true,
	    		                	 onGrap:[0,1],
	    		                	 lineStyle:{
	    		                		    color: ['#DDDDDD'],
	    		                		    width: 1,
	    		                		    type: 'solid'
	    		                	 } 
	    		                 },
	    		                 data : data.classRoomRank.names
	    		             }
	    		         ],
	    		         yAxis : [
	    		             {
	    		                 type : 'value',
	    		                 position: 'left',
	    		                 //min: 0,
	    		                 //max: 300,
	    		                 //splitNumber: 5,
	    		                 boundaryGap: [0,0.1],
	    		                 axisLine : {    // 轴线
	    		                     show: true,
	    		                    /* lineStyle: {
	    		                         color: 'red',
	    		                         type: 'dashed',
	    		                         width: 1
	    		                     }*/
	    		                 },
	    		                 axisTick : {    // 轴标记
	    		                     show:true,
	    		                     length: 10,
	    		                     lineStyle: {
	    		                         color: 'green',
	    		                         type: 'solid',
	    		                         width: 2
	    		                     }
	    		                 },
	    		                 axisLabel : {
	    		                     show:true,
	    		                     interval: 'auto',    // {number}
	    		                     /*rotate: -30,*/
	    		                     margin: 18,
	    		                     formatter: '{value}（分钟）',    // Template formatter!
	    		                     textStyle: {
	    		                         color: '#000000',
	    		                         fontFamily: 'verdana',
	    		                         fontSize: 8,
	    		                         fontStyle: 'normal',
	    		                         fontWeight: 'bold'
	    		                     }
	    		                 },
	    		                 splitLine:{
	    		                	 show:true,
	    		                	 onGrap:[0,1],
	    		                	 lineStyle:{
	    		                		    color: ['#DDDDDD'],
	    		                		    width: 1,
	    		                		    type: 'solid'
	    		                	 } 
	    		                 }
	    		             }
	    		         ],
	    		         
	    		    series : [
	    		        {
	    		            name:'使用时长',
	    		            type:'line',
	    		            data:data.classRoomRank.values,
	    		            markPoint : {
	    		                data : [
	    		                    {type : 'max', name: '最大值'},
	    		                    {type : 'min', name: '最小值'}
	    		                ]
	    		            },
	    		            markLine : {
	    		                data : [
	    		                    {type : 'average', name: '平均值'}
	    		                ]
	    		            }
	    		        }
	    		    ]
	    	  });
            // 教室使用时长排名统计结束
            var myChartLineData = data.deviceRankings.deviceRankings;
            var ss = {selected:true};
            if(data.deviceRankings.deviceRankings && data.deviceRankings.deviceRankings.length > 0){
            	data.deviceRankings.deviceRankings[0].selected=true;
//            	$(data.deviceRankings.deviceRankings[0],ss);
            }
            if(!data.deviceRankings.deviceRankings || data.deviceRankings.deviceRankings.length==0){
            	var obj={
            			name:'null',
            			value:0
            	}
            	data.deviceRankings.deviceRankings.push(obj);
            }
            // 设备使用时长排名统计开始
            myChartLine.hideLoading();
            myChartLine.setOption(
            		{
            			title : {
            				text : '设备运行时间排名',
            				x : 'center'
            			},
            			tooltip : {
            				trigger : 'item',
            				formatter : "{a} <br/>{b} : {c} ({d}%)"
            			},
            			legend : {
            				/*orient : 'horizontal',
            				x : 'left',*/
            				orient:'vertical',
        			    	x:20,
            				data : [ '中控', '投影', '空调']
            			},
            			toolbox : {
            				show : true,
            				feature : {
            					mark : {
            						show : false
            					},
            					dataView : {
            						show : false,
            						readOnly : true
            					},
            					magicType : {
            						show : true,
            						type : [ 'pie', 'funnel' ],
            						option : {
            							funnel : {
            								x : '25%',
            								width : '50%',
            								funnelAlign : 'left',
            								max : 1548
            							}
            						}
            					},
            					restore : {
            						show : true
            					},
            					saveAsImage : {
            						show : true
            					}
            				}
            			},
            			calculable : true,
            			series : [ {
            				name : '设备运行时间排名',
            				type : 'pie',
            				selectedMode: 'single',
            				radius : '45%',
            				center : [ '50%', '60%' ],
            				itemStyle : {
                                normal : {
                                    label : {
                                    	show:true,
                                        position : 'outer',
                                        formatter: '{b}（{c}）\n{d}%'
                                    }
                                }
                            },
            				data : data.deviceRankings.deviceRankings
            			} ]
            		});
            
            // 设备使用时长排名统计结束
        }
    });
}
function initClassSelect(selector){
	 $.ajax({
	        type : "post",
	        url :webRoot + '/runInfo/getClassList',
	       /* data:reqData,*/
	        success : function(data) {
	        	if(data){
	        		data = JSON.parse(data);
	        		if(data.data){
	        			var html ='<option value="">-- 请选择 --</option>';
	    				$.each(data.data,function(i,e){
	    					html += '<option value="'+e.id+'">'+e.name+'</option>'; 
	    				});
	    				$(selector).html(html); 
	        		}
	        		
	        	}
	        	
	        }
	 });
}