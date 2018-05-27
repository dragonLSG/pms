var option = null;
var myChart = null;

$(function () {
    var dom = document.getElementById("container1");
    myChart = echarts.init(dom);
    option = {
        title: {
            text: '用电量/电费记录',
            textStyle: {
                fontSize: 21
            },
            subtext: 'time',
            subtextStyle: {
                fontSize: 17
            }

        },
        subtitle: {
            text: '',
            textStyle: {
                fontSize: 21
            }
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['用电量', '电费']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        //天 / 月份
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: [],
            name: 'Day/号'
        },
        //用电量 / 电费
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}'
            },
            name: 'kW·h/元'
        },
        series: [
            {
                name: '用电量',
                type: 'line',
                data: [],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            },
            {
                name: '电费',
                type: 'line',
                data: [],
                markPoint: {
                    data: [
                        {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'},
                        [{
                            symbol: 'none',
                            x: '90%',
                            yAxis: 'max'
                        }, {
                            symbol: 'circle',
                            label: {
                                normal: {
                                    position: 'start',
                                    formatter: '最大值'
                                }
                            },
                            type: 'max',
                            name: '最高点'
                        }]
                    ]
                }
            }
        ]
    };
    $("#viewSelect").change(function () {
        var view = $("#viewSelect option:selected").text();
        if (view == "日视图") {
            $("#monSelect").css("display", "inline-block")
            $("#yearSelect").css("display", "none")
        } else {
            $("#monSelect").css("display", "none")
            $("#yearSelect").css("display", "inline-block")
        }
    })

})

function genChart() {

    myChart.showLoading();
    var account = $.trim($("#account").text());
    var flag = null;
    var url = "";

    //选择的视图
    var view = $("#viewSelect option:selected").text();

    if (view == "日视图") {
        flag = $("#monSelect option:selected").attr("id");
        url = "/getDayUse"
        if (flag == null || flag == "") {
            flag = "thisMon";
        }
    } else {
        flag = $("#yearSelect option:selected").attr("id");
        url = "/getMonUse"
        if (flag == null || flag == "") {
            flag = "thisYear";
        }
    }

    var args = {account: account, flag: flag};
    //ajax获取数据
    var date = [];
    var use = [];
    var fee = [];
    var title = "";
    var xname = "";
    $.ajax({
        type: 'GET',
        url: url,
        data: args,
        async: false,
        dataType: 'json',
        success: function (result, status) {
            if (result.status == "200" && result.data.length != 0) {
                if (view == "日视图") {
                    $.each(result.data, function (k, v) {
                        date[k] = v.date.substr(8, 2);
                        use[k] = v.delectUse;
                        fee[k] = v.dfee;
                    })
                    title = result.data[0].date.substr(0, 7) + "月";
                    xname = "Day/号";
                } else {
                    $.each(result.data, function (k, v) {
                        date[k] = v.date.substr(5, 2);
                        use[k] = v.melectUse;
                        fee[k] = v.mfee;
                    })
                    title = result.data[0].date.substr(0, 4) + "年";
                    xname = "Mon/月";
                }
            } else {
                xname = (view == "日视图" ? "Day/号" : "Mon/月");
            }
            if (!isPC) {
                option.title.textStyle.fontSize = 13;
                option.title.subtextStyle.fontSize = 10;
                option.toolbox.show = false;
                $("#container1").css("height", "60vh");
            }
            //subtitle
            option.title.subtext = title;
            //x轴 day / month
            option.xAxis.name = xname;
            option.xAxis.data = date;
            //用电量
            option.series[0].data = use;
            //电费
            option.series[1].data = fee;
        }
    })

    if (option && typeof option === "object") {
        myChart.setOption(option, true);
        myChart.resize();
        myChart.hideLoading();
        if (use.length == 0) {
            $("#noDataNotice").modal("show");
        }
    }
}