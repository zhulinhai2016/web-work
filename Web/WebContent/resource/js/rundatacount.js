/** 运行数据统计页面 */
var webRoot;
var myChartPie;
var myChartBar;
var myChartLine;
var init = false;
$(function(){
	$(window).resize(function(){
		//执行代码块
		if(!init){
			init=true;
			setTimeout(function(){
				myChartPie.resize();
				myChartBar.resize();
				myChartLine.resize();
//				initChartPie(myChartPie,myChartBar,myChartLine);
				init = false;
			},1500);
			console.log(12121);
		}
		
	});
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
	$('.create-year').datetimepicker({
		 language:  'zh-CN',
         todayBtn:  1,
		 autoclose: 1,
		 todayHighlight: 1,
		 startView: 4,
		 minView: 4,
		 forceParse: 0,
		 fromatter:'yyyy'
    });
	
	$(".date-remove").unbind("click");
	$('.date-remove').click(function(){
		$(this).parent().find('.show-date').val('');
		$(this).parent().parent().find('.date-hide').val('');
	});
	
	// 表单搜索
	$("#search").click(function(ev){
		ev.preventDefault();
		ev.stopPropagation();
		initChartPie(myChartPie, myChartBar, myChartLine);
	});
	
	var optionPie = {
		title : {
			text : '运行时间',
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
			name : '运行时间',
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
		        text: '用电量',
		        x:150
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    /*legend: {
		    	orient : 'vertical',
				x : 'left',
		        data:['中控','投影','空调']
		    },*/
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
		            data : ['中控','投影','空调']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} （kw/h）'
		            }
		        }
		    ],
		    series : [
		              {
		                  name:'用电量',
		                  type:'bar',
		                  stack: 'sum',
		                  barCategoryGap: '50%',
		                  itemStyle: {
		                      normal: {
		                          color: 'tomato',
		                          barBorderColor: 'tomato',
		                          barBorderWidth: 6,
		                          barBorderRadius:0,
		                          label : {
		                              show: true, position: 'insideTop'
		                          }
		                      }
		                  },
		                  data:[0,0,0]
		              }
		        ]
		};
	var optionLine = {
		    title : {
		        text: '故障报警',
		        x:150
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		    	orient : 'vertical',
				x : 'left',
		    	data:['中控','投影','空调','电箱']
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
		            boundaryGap : false,
		            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} （次）'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'中控',
		            type:'line',
		            data:[],
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
		            name:'投影',
		            type:'line',
		            data:[],
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
		            name:'空调',
		            type:'line',
		            data:[],
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
		            name:'电箱',
		            type:'line',
		            data:[],
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
		        
		    ]
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
//				initChartPie(myChartPie);
				initChartPie(myChartPie,myChartBar,myChartLine);
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
        url :webRoot + '/runInfo/getRunCount',
        data:reqData,
        success : function(data) {
            //创建一个数组，用来装对象传给series.data，因为series.data里面不能直接鞋for循环
            var servicedataRun=[];
            var namesRun = [];
            var runData = data.runData;
            var powerData = data.powerData;
            if(runData.length == 0){
            	var obj=new Object();
                obj.name="无数据"; 
                obj.value="0";
                servicedataRun[0]=obj;
                namesRun.push("无数据");
            } else{
            	for(var i=0;i<runData.length;i++){
            		var obj=new Object();
            		if(i==0){
            			obj.selected=true;
            		}
            		obj.name=runData[i].name; 
            		obj.value=runData[i].value;
            		servicedataRun[i]=obj;
            		namesRun.push(runData[i].name);
            	}
            }
            myChartPie.hideLoading();
            myChartPie.setOption({
            	title : {
        			text : '运行时间统计',
        			x : 150
        		},
        		tooltip : {
        			/*show:true,*/
        			trigger : 'item',
        			formatter : "{a} <br/>{b} : {c} ({d}%)"
        		},
        		legend : {
        			orient : 'vertical',
					x : 'right',
        			y:40,
        			data :namesRun
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
                series:[{
                	name : '运行时间统计',
        			type : 'pie',
        			radius : '50%',
        			selectedMode: 'single',
        			center : [ '50%', '50%' ],
                    itemStyle : {
                        normal : {
                            label : {
                            	show:true,
                                position : 'outer',
                                formatter: '{b}（{c}）\n{d}%'
                            }
                        }
                    },
                    data:servicedataRun
                }]

            });
            
            // 初始化使用电量图 TODO
            
            var dataPower = [];
            var namesPower=[];
            if(runData.length == 0){
            	var obj=new Object();
                obj.name="无数据"; 
                obj.value="0";
                dataPower[0]=obj;
                namesPower.push("无数据");
            } else{
            	for(var i=0;i<powerData.length;i++){
            		dataPower.push(powerData[i].value);
            		namesPower.push(powerData[i].name);
            	}
            }
           
            var namesP = [];
            for(var i=0;i<namesPower.length;i++){
            	var obj = new Object();
            	obj.name=namesPower[i];
            	var tex = new Object();
            	tex.color='#C33531';
            	obj.textStyle=tex;
            	namesP[i]=obj;
            }
            
//            myChartBar.clear();
            myChartBar.hideLoading();
            myChartBar.setOption({
    		    title : {
    		        text: '用电量',
    		        x:150
    		    },
    		    tooltip : {
    		        trigger: 'axis'
    		    },
    		    legend: {
    		    	orient : 'vertical',
					x : 'right',
        			y:40,
    		        data:namesPower
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
    		            data : namesPower
    		        }
    		    ],
    		    yAxis : [
    		        {
    		            type : 'value',
    		            axisLabel : {
			                formatter: '{value} （kw/h）'
			            }
    		        }
    		    ],
    		    series : [
							{
							    name:namesPower,
							    type:'bar',
							    stack: 'sum',
							    barCategoryGap: '50%',
							    symbolSize:10,
							    itemStyle: {
							        normal: {
//							            barBorderColor: 'tomato',
							            barBorderWidth: 6,
							            barBorderRadius:0,
							            color: function(params) { 
						                    var colorList = ['#C33531','#EFE42A','#64BD3D','#EE9201','#29AAE3', '#B74AE5','#0AAF9F','#E89589','#16A085','#4A235A','#C39BD3 ','#F9E79F','#BA4A00','#ECF0F1','#616A6B','#EAF2F8','#4A235A','#3498DB' ]; 
						                    return colorList[params.dataIndex] 
						                },
							            label : {
							                show: true, position: 'top'
							            }
							        }
							    },
							    data:dataPower
							}
    		        ]
    		});
            
            // 初始化异常报警图 TODO
            myChartLine.hideLoading();
			myChartLine.setOption({
			    title : {
			        text: '故障报警',
			        x:150
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			    	orient : 'vertical',
					x : 'right',
        			y:40,
			    	data:['中控','投影','空调','电箱']
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
			            boundaryGap : false,
			            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value} （次）'
			            }
			        }
			    ],
			    series : [
			        {
			            name:'中控',
			            type:'line',
			            data:data.values1,
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
			            name:'投影',
			            type:'line',
			            data:data.values2,
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
			            name:'空调',
			            type:'line',
			            data:data.values3,
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
			            name:'电箱',
			            type:'line',
			            data:data.values4,
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
			        
			    ]
			});
        }
    });
}