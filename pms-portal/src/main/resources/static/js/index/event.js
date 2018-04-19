var dormName = "";
var id = 1;
var ajaxPageInf;
var defaultIndex = [1, 2, 3, 4, 5]
var fbEditor = null;
var isPC = true;

/**
 * button监控
 */
$(function () {

    init();

    //加载页面 初始化参数
    function init() {
        //获取使用设备信息，PC还是手机
        IsPC();

        //首页 显示通告列表， 一条最新通告内容
        showtable_1(getPageInf(id), "reload")
        var noticeId = $("#table_1 tbody tr td").eq(3).text();
        showNoticeContent(noticeId);
    }

    /*注销账户*/
    $("#logoutBtn").click(function () {
        if (confirm("确认注销账户？")) {
            window.location.href = "logout";
        }
        return false;
    });

    /*更改每页行数，刷新列表*/
    $(".SelectEvenNum select").change(function () {
        //初始化分页插件
        resetPagination(id, 2, defaultIndex)
        //加载数据
        switchToShow(id, "reload")
    });

    /* 切换menu */
    $(".menu_item").click(function () {

        // 设置菜单栏切换
        $(".menu_item").each(function () {
            $(this).removeClass("active").css({"background-color": "white"});
        });
        $(this).addClass("active").css({"background-color": "#DCDCDC"});

        // 隐藏content区域所有内容
        $(".content").children().each(function () {
            $(this).css({
                "display": "none"
            });
        });

        // 设置 保存当前所在module的id
        var menu_id = $(this).attr("id");
        id = menu_id.split("_")[1];

        //设置 获取到的分页信息为null
        ajaxPageInf = null;

        //初始化 分页插件
        // if (id == 4 || id==6) {
        //     resetPagination(id, 2, defaultIndex)
        // }

        //加载数据
        switch (parseInt(id)) {
            case 1:
                showtable_1(getPageInf(id), "reload")
                var noticeId = $("#table_1 tbody tr td").eq(3).text();
                showNoticeContent(noticeId);
                break;
            case 2:
                // showtable_2(getSearchUserInf("all"), "reload")
                break;
            case 3:
                showtable_3();
                break;
            case 4:

                break;
            case 5:
                break;
            case 6:
                showtable_6(getSearchFeedbackInf("all"), "reload")
                break;
        }

        //显示选中模块
        $("#module_" + id).css({
            "display": "block"
        });

        //若为手机操作，自动关闭菜单栏
        if (!isPC) {
            $("body").addClass("enlarged");
        }

    });

    //分页控制，下一页，上一页，最后一页
    $(".pagination li").click(function () {
        var pageNum = getPageInf(id).pageNum;
        var totalNum = ajaxPageInf.totalPageNum
        var selectText = $(this).find("a").text();
        if (selectText != pageNum) {
            /* 第一页 */
            if (selectText == "«") {
                resetPagination(id, 2, defaultIndex)
            }
            /* 最后一页 */
            else if (selectText == "»") {
                var index = totalNum % 5;
                index = index == 0 ? 4 : index - 1;
                var args = new Array()
                var i;
                for (i = 0; i < 5; i++) {
                    args[i] = totalNum - index + i
                }
                resetPagination(id, index + 2, args)
            }
            /* 上一页 */
            else if (selectText == "‹") {
                if (pageNum <= 5) {
                    return false;
                } else {
                    var args = new Array();
                    var links = $(".bar_" + id + " .pagination li").find("a");
                    args[0] = links[2].innerHTML
                    args[1] = links[3].innerHTML
                    args[2] = links[4].innerHTML
                    args[3] = links[5].innerHTML
                    args[4] = links[6].innerHTML
                    var i;
                    for (i = 0; i < 5; i++) {
                        if (args[i] != "") {
                            args[i] = parseInt(args[i]) - 5;
                        }
                    }
                    resetPagination(id, 6, args)
                }
            }
            /* 下一页 */
            else if (selectText == "›") {
                if (pageNum > totalNum - 5) {
                    return false;
                } else {
                    var args = new Array();
                    var links = $(".bar_" + id + " .pagination li").find("a");
                    args[0] = links[2].innerHTML
                    args[1] = links[3].innerHTML
                    args[2] = links[4].innerHTML
                    args[3] = links[5].innerHTML
                    args[4] = links[6].innerHTML
                    var i;
                    for (i = 0; i < 5; i++) {
                        if (args[i] != "") {
                            args[i] = parseInt(args[i]) + 5;
                        }
                    }
                    resetPagination(id, 2, args)
                }
            } else {
                $(".bar_" + id + " .pagination li").removeClass("active");
                $(this).addClass("active");
            }
            switchToShow(id, "reset");
        }
    })


    /*************************通告管理*****************************************/
    /*查看通告内容*/
    $(document).on('click', '#table_1 tr', function () {
        var noticeId = $(this).find("td").eq(3).text();
        showNoticeContent(noticeId);
    });

    /*更多信息 按钮*/
    $("#moreInfoBtn").click(function () {

        var flag = $("#collapseOne").hasClass("show");
        if (!flag) {
            $(".bar_1 .SelectEvenNum select").find("option:selected").text("14");
        } else {
            $(".bar_1 .SelectEvenNum select").find("option:selected").text("8");
        }
        showtable_1(getPageInf(id), "reload")
        var activeNode = parseInt(getPageInf(id).pageNum) + 1;
        resetPagination(id, activeNode, defaultIndex)
    })


    /*************************反馈管理*****************************************/
    /* 查询反馈列表 */
    $("#module_6 serch_table .btn").click(function () {
        showtable_6(getSearchFeedbackInf("section"), "reload");
    })
    /*添加反馈*/
    $("#addFBbtn").click(function () {

        createRichEditor();
        //点击外部不关闭模态框
        $("#addFBModal").modal({backdrop: 'static', keyboard: false});
        $("#addFBModal").modal("show");
    })
})


/**
 * function
 */


/****************************公共function**********************************/
//判断是手机还是PC访问
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod", "IOS"];
    isPC = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            isPC = false;
            break;
        }
    }
    return isPC;
}

/*根据module_id调用 不同加载不同module的数据 */
function switchToShow(id, option) {

    switch (parseInt(id)) {
        case 1:
            showtable_1(getPageInf(id), option);
            break;
        case 2:
            showtable_2(getSearchUserInf("section"), option)
            break;
        case 3:
            showtable_3();
            break;
        case 4:
            showtable_4(getSearchNoticInf("section"), option)
            break;
        case 5:
            showtable_5(getSearchNoticInf("section"), option)
            break;
        case 6:
            showtable_6(getSearchFeedbackInf("section"), option)
            break;
    }
}

/*获取分页信息*/
function getPageInf(module_id) {

    var pageNum = $(".bar_" + module_id + " .pagination").find("li.active").text();
    var pageSize = $(".bar_" + module_id + " .SelectEvenNum select").find("option:selected").text();

    var pageInf = {
        pageNum: pageNum,
        pageSize: pageSize
    }
    return pageInf;
}

/*设置分页状态，args[0]~args[4]页，activeIndex页active*/
function resetPagination(id, activeIndex, args) {
    var li = $(".bar_" + id + " .pagination li");
    li.removeClass("active");
    li.eq(activeIndex).addClass("active")

    var links = $(li).find("a");
    links.removeClass("disabled")
    links.css("background-color", "white")
    // links.removeClass("disabled")
    links[2].innerHTML = args[0]
    links[3].innerHTML = args[1]
    links[4].innerHTML = args[2]
    links[5].innerHTML = args[3]
    links[6].innerHTML = args[4]
    if (ajaxPageInf != null) {
        $.each(args, function (index, value) {
            if (value > ajaxPageInf.totalPageNum) {
                links.eq(index + 2).addClass("disabled")
                links.eq(index + 2).css("background-color", "#f1f1f1")
            }
        })
    }
}

/****************************数据统计function**********************************/


/*****************************用户管理function********************************/
function showtable_3() {
    var account = $.trim($("#account").text());
    var args = {account: account}
    $.ajax({
        type: 'GET',
        url: '/user/detail',
        data: args,
        dataType: 'json',
        success: function (result, status) {
            if (result.status == "200" && result.data != null) {
                var data = result.data;
                var input = $("#table_2 input");

                input.eq(0).val(data.account);
                input.eq(1).val(data.username);
                input.eq(2).val(data.dormitoryname);
                input.eq(3).val(data.mobile);
                input.eq(5).val(data.email);
            }
        }
    })
}

/*****************************通告function********************************/

/* 获取通告列表 */
function showtable_1(args, option) {
    if (option == "reload") {
        $("#table_1 tbody").empty(); //清空tbody
    }
    $("#table_1 thead input[type=checkbox]").prop("checked", false);
    $.ajax({
        type: 'GET',
        url: '/notice',
        dataType: 'json',
        timeout: '10000',
        async: false,
        data: args,
        success: function (result) {
            if (result.status == "200" && result.data != null) {
                var data = result.data;
                if (option == "reload") {
                    $.each(data.data, function (key, value) {
                        var trs = "<tr><td id='noticeTitle' title='" + value.title + "'><span class='notice_arrow'/><span>" + value.title + "</span></td><td>" + value.publishdate +
                            "<td>" + value.publisher + "</td><td hidden>" + value.noticeid + "</td></tr>"
                        $("#table_1 tbody").append(trs);
                    })

                } else if (option == "reset") {
                    var trs = $("#table_1 tbody tr");
                    trs.css("display", "table-row")
                    var len = trs.length;
                    var i;
                    for (i = 0; i < len; i++) {
                        var tds = trs[i].childNodes;
                        if (typeof (data.data[i]) != "undefined") {
                            tds[0].childNodes[1].innerHTML = data.data[i].title;
                            tds[1].innerHTML = data.data[i].publishdate;
                            tds[2].innerHTML = data.data[i].publisher;
                            tds[3].innerHTML = data.data[i].noticeid;
                        } else {
                            trs.eq(i).css("display", "none")
                        }
                    }
                }
                ajaxPageInf = data.pageInf;
            } else if (result.status == "500") {
                $("#exceptionPanel").modal("show");
                setTimeout(function () {
                    $("#exceptionPanel").modal("hide");
                }, 1500)
            }
        }
    })
}

/*展示noticid的通告内容*/
function showNoticeContent(noticeid) {

    $("#noticeContent .panel-body").empty();
    $("#noticeContent .panel-footer").empty();

    var args = {noticeId: noticeid}
    $.ajax({
        type: 'GET',
        url: '/notice/content',
        data: args,
        dataType: 'json',
        success: function (result, status) {
            if (result.status == "200" && result.data != null) {
                var data = result.data;
                var content = "<h2 style='text-align: center'>" + data.title + "</h2>"
                content += data.content;

                $("#noticeContent .panel-body").append(content);
                var footer = "<div style='text-align: right'>by " + data.publisher + "&nbsp;&nbsp; Time: " + data.publishdate;
                $("#noticeContent .panel-footer").append(footer);
            }
        }
    })
}


/*****************************反馈function********************************/
//获取 搜索条件信息
function getSearchFeedbackInf(option) {
    var pageInf = getPageInf(id);
    var title = "";
    var startDate = "";
    var endDate = "";
    var status = "2";
    var sender = $.trim($("#account").text());
    if (option == "section") {
        var inputs = $("#module_6 .serch_table input");
        title = $.trim(inputs.eq(0).val());
        startDate = inputs.eq(1).val();
        endDate = inputs.eq(2).val();
        status = $("#module_6 .serch_table select option:selected").eq(0).attr("value");
    }
    var searchInf = {
        pageNum: pageInf.pageNum, pageSize: pageInf.pageSize,
        title: title, startDate: startDate, endDate: endDate, status: status, sender: sender
    }
    return searchInf;
}

/* 获取反馈列表 */
function showtable_6(args, option) {
    if (option == "reload") {
        $("#table_6 tbody").empty(); //清空tbody
    }
    $.ajax({
        type: 'GET',
        url: '/feedback',
        dataType: 'json',
        timeout: '10000',
        data: args,
        success: function (result) {
            if (result.status == "200" && result.data != null) {
                var data = result.data;
                if (option == "reload") {
                    $.each(data.data, function (key, value) {
                        var status = value.fstatus == 0 ? "未处理" : "已处理"
                        var trs = "<tr><td>" + value.fid +
                            "</td><td>" + value.title + "</td><td><a href='javascript:showFBContent(" + value.fid +
                            ")'>点击查看内容详情</a></td><td>" + value.sender + "</td><td>" + status +
                            "</td><td>" + value.fdate + "</td></tr>"
                        $("#table_6 tbody").append(trs);
                    })
                    ajaxPageInf = data.pageInf;

                } else if (option == "reset") {
                    var trs = $("#table_6 tbody tr");
                    trs.css("display", "table-row")
                    var len = trs.length;
                    var i;
                    for (i = 0; i < len; i++) {
                        var tds = trs[i].childNodes;
                        if (typeof (data.data[i]) != "undefined") {
                            tds[0].innerHTML = data.data[i].fid;
                            tds[1].innerHTML = data.data[i].title;
                            tds[2].innerHTML = "<a href='javascript:showFBContent(" + data.data[i].fid + ")'>点击查看内容详情</a>"
                            tds[3].innerHTML = data.data[i].sender;
                            tds[4].innerHTML = (data.data[i].fstatus == 0 ? "未处理" : "已处理");
                            tds[5].innerHTML = data.data[i].fdate;
                        } else {
                            trs.eq(i).css("display", "none")
                        }
                    }
                }
                ajaxPageInf = data.pageInf;
                resetPagination(id, 2, defaultIndex);
                $(".bar_6 .sumNum").text("共" + ajaxPageInf.totalNum + "条数据");
            } else if (result.status == "500") {
                $("#exceptionPanel").modal("show");
                setTimeout(function () {
                    $("#exceptionPanel").modal("hide");
                }, 1500)
            }
        }
    })
}

//显示单条反馈内容
function showFBContent(fid) {

    var args = {fId: fid}

    $.ajax({
        type: 'GET',
        url: '/feedback/content',
        data: args,
        dataType: 'json',
        async: false,
        success: function (result, status) {

            $("#fbContent .modal-title").empty();
            $("#fbContent .modal-body").empty();
            $("#fbContent .modal-footer").empty();

            if (result.status == "200" && result.data != null) {

                $("#fbContent .modal-title").append(result.data.title);
                $("#fbContent .modal-body").append(result.data.content);
                $("#fbContent .modal-footer").append("by: " + result.data.sender + "&nbsp;&nbsp;&nbsp;Date: " + result.data.fdate);

            } else {
                $("#fbContent .modal-body").append("内容获取失败，请重试！");
            }
            $("#fbContent").modal("show");
        }
    })
}

//创建富文本编辑器
function createRichEditor() {

    if (fbEditor == null) {
        var E = window.wangEditor
        fbEditor = new E('#fbRichEditor')
        fbEditor.create()
    }
}
