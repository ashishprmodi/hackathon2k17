var response;
//var proxyUrl =  /*"https://userregistration.cfapps.io/alarm/";*/ "http://localhost:8090/alarm/";
var proxyUrl = "http://localhost:8090/alarm/";
var proxyUrl1 = "http://132.186.64.157:8080/alarm/";
var geoCode="https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyAWx2Sw8wX2L5m9rnL3aTHXGPPlHvcvwWo&latlng=";
var proxyUrlLiveRepo = "http://132.186.66.81:8080/alarm/";

 $("body").ready(function() {
	loadCharts();
    $("#live_rep").click(function(){
		$("#main_container").html("");
		$("#main_container").load("live.html");		
	});
	$("#logo").click(function(){
		loadCharts();
	});
 });


var jsonDataPieBasic = {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: 'Accident Percentage Rates'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                style: {
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                }
            }
        }
    },
    series: [{
        name: 'Brands',
        colorByPoint: true,
        data: [{
            name: 'Balewadi',
            y: 56.33
        }, {
            name: 'Wakad - Ginger Hotel',
            y: 24.03,
            sliced: true,
            selected: true
        }, {
            name: 'Chinchawad',
            y: 10.38
        }, {
            name: 'Wagholi',
            y: 4.77
        }, {
            name: 'Kharadi',
            y: 0.91
        }, {
            name: 'Dhanori',
            y: 0.2
        }]
    }]
}

var jsonDataArea = {
    chart: {
        type: 'area'
    },
	value:1,
    title: {
        text: 'Accidents - Vehicles Involved'
    },
    xAxis: {
		type: 'datetime',        
		dateTimeLabelFormats: {
			month: '%b \'%y',
			year: '%Y'
		}
       
    },
    yAxis: {
        title: {
            text: 'Number of Accidents'
        },
        labels: {
            formatter: function () {
                return this.value;
            }
        }
    },
    tooltip: {
        pointFormat: '{series.name} produced <b>{point.y:,.0f}</b><br/>accidents.'
    },
    plotOptions: {
        area: {
            pointStart: Date.UTC(2017, 0, 1),
			pointInterval: (3600*1000*30*24),
            marker: {
                enabled: false,
                symbol: 'circle',
                radius: 2,
                states: {
                    hover: {
                        enabled: true
                    }
                }
            }
        }
    },
    series: [{name: 'Two Wheelers',data: [121, 99, 222, 250, 102, 160, 110, 120]},
			 {name: 'Light Weight 4 Wheelers',data: [12, 9, 22, 20, 12, 16, 11, 12]},
			 {name: 'Heavy Weight Vehicles',data: [100, 30, 112, 20, 212, 116, 115, 212]}
	]
};

var jsonDataBarBasic = {
    chart: {
        type: 'bar'
    },
    title: {
        text: 'Accidental Injuries Analysis'
    },
    xAxis: {
        categories: ['Balewadi', 'Wakad', 'Dhanori', 'Wagholi', 'Chinchawad'],
        title: {
            text: null
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Accident',
            align: 'high'
        },
        labels: {
            overflow: 'justify'
        }
    },
    tooltip: {
        valueSuffix: ' millions'
    },
    plotOptions: {
        bar: {
            dataLabels: {
                enabled: true
            }
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'top',
        x: -40,
        y: 80,
        floating: true,
        borderWidth: 1,
        backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
        shadow: true
    },
    credits: {
        enabled: false
    },
    series: [{
        name: 'Road Rage Casualties',
        data: [127, 131, 135, 203, 122]
    }, {
        name: 'Major Injuries',
        data: [133, 156, 147, 108, 61]
    }, {
        name: 'Minor Injuries',
        data: [105, 154, 250, 140, 138]
    }]
}

function loadCharts(){
	$("#main_container").html("");	
	$("#main_container").load("dashboard.html",function(){
	pieChartData();		
	areaWiseData()
	dataBarChart();	
	$("#heat_chart").load("googleheat.html");		
	
	});
}
function dataBarChart(){
	
	var data={};
		data.url=proxyUrl+"getAccidentAnalysisData";
		data.method="GET";
		data.contenttype="application/json";
		data.datatype="json"; 
	$.ajax({type: data.method,
			contentType: data.contenttype,
			url: data.url,
			data: data.data,
			dataType: data.datatype, 
		    success: function(response){
				jsonDataBarBasic.xAxis.categories = response.area;
				jsonDataBarBasic.series[0].data = response.countList[0].c;
				jsonDataBarBasic.series[1].data = response.countList[1].m;
				jsonDataBarBasic.series[2].data = response.countList[2].i;
				Highcharts.chart('high_chart3', jsonDataBarBasic);
			},
			error: function (e) {
			   
			}});
	
}
function areaWiseData(){
	var data={};
		data.url=proxyUrl+"area";
		data.method="GET";
		data.contenttype="application/json";
		data.datatype="json"; 
	$.ajax({type: data.method,
			contentType: data.contenttype,
			url: data.url,
			dataType: data.datatype, 
		    success: function(response){
				jsonDataArea.plotOptions.area.pointStart = response.startDate;
				jsonDataArea.series=[];
				jsonDataArea.series[0]={"name":'Two Wheelers',"data":response.countMap["tw"]};
				jsonDataArea.series[1]={"name":'Four Wheelers',"data":response.countMap["fw"]};
				jsonDataArea.series[2]={"name":'Heavy Weight Vehicles',"data":response.countMap["hv"]};
				Highcharts.chart('high_chart2', jsonDataArea);
				
			},
			error: function (e) {
			   
			}});
}
 
function pieChartData(){
	var seriesFromBack=[];	
	seriesFromBack[0]={};
	seriesFromBack[0].name="Location";
	seriesFromBack[0].colorByPoint="true";
	seriesFromBack[0].data=[];
	
	
	var data={};
		//data.url="http://localhost:8082/ems/getAccidentAreaWise";
		data.url=proxyUrl+"occurencebyarea";
		data.method="GET";
		//data.data=JSON.stringify({"empId":"1"});
		data.contenttype="application/json";
		data.datatype="json"; 
	var total=0;	
	var countMap=[];
		$.ajax({
			type: data.method,
			contentType: data.contenttype,
			url: data.url,
			data: data.data,
			dataType: data.datatype, 
		    success: function(response){
				var resp=response;
				var data=[];
				var total=0;
				for(var c=0;c<resp.length;c++){
					total=total+parseInt(resp[c].count);
				}
				for(var k=0;k<resp.length;k++){
					data[k]={};
					data[k].name=resp[k].name;
					data[k].y=((parseInt(resp[k].count)/total)*100);
				}
				seriesFromBack[0].data=data;								
				jsonDataPieBasic.series[0]=seriesFromBack[0];
				Highcharts.chart('high_chart1', jsonDataPieBasic);
		},
			error: function (e) {
			   
			}
		})
	
	
}

