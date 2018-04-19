var dormName = "";
var id = 1;
var ajaxPageInf;
var defaultIndex = [1, 2, 3, 4, 5]
var noticeEditor = null;
/**
 * button监控
 */
$(function () {

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

    IsPC();

    /*注销账户*/
    $("#logoutBtn").click(function () {
        if (confirm("确认注销账户？")) {
            window.location.href = "logout";
        }
        return false;
    });

    /*全选按钮*/
    $(".module_table thead tr th input:checkbox").click(function () {
        if ($(this).is(':checked')) {
            $("#table_" + id + " tbody input[type=checkbox]").prop("checked", true);
        } else {
            $("#table_" + id + " tbody input[type=checkbox]").prop("checked", false);
        }
    });

    /*更改每页行数，刷新列表*/
    $(".SelectEvenNum select").change(function () {
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

        // 清空content内容
        removeALlContent();

        // 设置 当前所在module
        var menu_id = $(this).attr("id");
        id = menu_id.split("_")[1];

        //加载数据
        switch (parseInt(id)) {
            case 1:
                showNoticeList();
                break;
            case 2:
                showtable_2(getSearchUserInf("all"), "reload")
                break;
            case 3:
                showtable_3(getPageInf(id), dormName, "reload");
                break;
            case 4:
                showtable_4(getSearchNoticInf("all"), "reload")
                break;
            case 5:
                showtable_5(getSearchFeedbackInf("all"), "reload")
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

    /***********************用户管理************************************/

    /* 查询用户 */
    $("#module_2 .serchBar .btn").click(function () {
        showtable_2(getSearchUserInf("section"), "reload");
    })

    /* 批量删除用户btn */
    $("#deleteBtn2").click(function () {
        delUser(null)
    })


    /********************宿舍管理***************************************/

    //批量删除宿舍btn
    $("#deleteBtn3").click(function () {
        delDorm(null);
    })

    //查询宿舍btn
    $("#module_3 .serchBar .panel-body .btn").click(function () {
        dormName = "";
        /*select条件查询*/
        if ($(this).is($("#module_3 .serchBar .panel-body .btn").eq(0))) {
            $("#module_3 .serch_table select option:selected").each(function (i, item) {
                dormName += item.text
            })
            dormName += $("#module_3 .serch_table input:eq(0)").val()
        }
        /*input条件查询*/
        else {
            dormName = $.trim($("#module_3 .serch_table input:eq(1)").val());
        }
        resetPagination(id, 2, defaultIndex);
        showtable_3(getPageInf(id), dormName, "reset");
    });

    /*************************通告管理*****************************************/

    /* 查询通告 */
    $("#module_4 .serchBar .btn").click(function () {
        showtable_4(getSearchNoticInf("section"), "reload");
    })

    /*添加通告*/
    $("#addNotice").click(function () {

        createRichEditor();

        var noticeid = $("#noticeUpdate .modal-content").attr("id");
        if (noticeid != null && typeof(noticeid) != 'undefined') {
            $("#noticeUpdate .modal-content").attr("id", "")
            $("#noticeTitle").val("");
            noticeEditor.txt.html("");
        }

        //点击外部不关闭模态框
        $("#noticeUpdate").modal({backdrop: 'static', keyboard: false});
        $("#noticeUpdate").modal("show");
    })

    //删除通告
    $("#deleteBtn4").click(function () {
        delNotice(null)
    })


    /*************************反馈管理*****************************************/

    /* 查询反馈列表 */
    $("#module_5 .serchBar .btn").click(function () {
        showtable_5(getSearchFeedbackInf("section"), "reload");

    })

    /*批量删除反馈*/
    $("#deleteBtn5").click(function () {
        delFeedback(null)
    })

})

/**
 * function
 */


/****************************公共function**********************************/

/*根据module_id调用 不同加载不同module的数据 */
function switchToShow(id, option) {

    switch (parseInt(id)) {
        case 1:
            break;
        case 2:
            showtable_2(getSearchUserInf("section"), option)
            break;
        case 3:
            showtable_3(getPageInf(id), dormName, option);
            break;
        case 4:
            showtable_4(getSearchNoticInf("section"), option)
            break;
        case 5:
            showtable_5(getSearchFeedbackInf("section"), option)
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

/* 隐藏内容区域 */
function removeALlContent() {
    $(".content").children().each(function () {
        $(this).css({
            "display": "none"
        });
    });
}

/* 删除一条 或 多条数据 param：请求的url，删除的对应id*/
function deleteBySelects(ReqUrl, selects) {

    //多选
    if (selects == null) {
        selects = "";
        var trs = $("#table_" + id + " tbody tr");
        trs.each(function (i, item) {
            if ($(item).find("input:eq(0)").is(':checked')) {
                selects += $(item).children("td:eq(1)").text() + ",";
            }
        })
        if ("" == selects) {
            alert("请先选中要删除的记录")
            return;
        }
        selects = selects.substring(0, selects.length - 1);
    }
    //not null 单选
    if (!confirm("确定删除选中的记录么？")) {
        return;
    }
    var url = ReqUrl + "/" + selects;
    $.ajax({
        type: 'delete',
        url: url,
        dataType: 'json',
        timeout: '10000',
        success: function (result) {
            if (result.status == "200" && result.data != null) {
                alert("共" + result.data + "条记录，删除成功!")
                switchToShow(id, "reset")
            } else if (result.status == "500") {
                $("#exceptionPanel").modal("show");
                setTimeout(function () {
                    $("#exceptionPanel").modal("hide");
                }, 1500)
            }
        }
    })

}


/****************************数据统计function**********************************/

function showNoticeList(pageSize) {
    var pageNum = 1;
    if (pageSize == null) {
        pageSize = 6;
    }
    $("#notices").empty();
    var args = {pageNum: pageNum, pageSize: pageSize}
    $.ajax({
        type: 'GET',
        url: '/notice',
        data: args,
        dataType: 'json',
        success: function (result, status) {
            if (result.status == "200" && result.data != null) {

                var data = result.data;
                $.each(data.data, function (key, value) {
                    var li = "<li class=\"text-left\"><span class=\"right\">" +
                        value.publishdate + "</span><span class=\"right\">" +
                        value.publisher + "&nbsp;&nbsp;</span><a href=\"#\"><span class=\"fa fa-angle-right\"></span>" +
                        value.title + "</a></li>"
                    $("#notices").append(li);
                })
            }
        }

    })

}


/*****************************用户管理function********************************/
//获取 搜索条件信息
function getSearchUserInf(option) {
    var pageInf = getPageInf(2);
    var username = "";
    var account = "";
    var dormname = "";
    var status = "2";
    if (option == "section") {
        var inputs = $("#module_2 .serch_table input");
        username = $.trim(inputs.eq(0).val());
        account = $.trim(inputs.eq(1).val());
        dormname = $.trim(inputs.eq(2).val());
        status = $("#module_2 .serch_table select option:selected").eq(0).attr("value");
    }
    var userSearchInf = {
        pageNum: pageInf.pageNum, pageSize: pageInf.pageSize,
        username: username, account: account, dormitoryname: dormname, status: status
    }
    return userSearchInf;
}

/*获取用户列表*/
function showtable_2(args, option) {
    if (option == "reload") {
        $("#table_2 tbody").empty(); //清空tbody
    }
    $("#table_2 thead input[type=checkbox]").prop("checked", false);
    $.ajax({
        type: 'GET',
        url: '/user',
        dataType: 'json',
        timeout: '10000',
        data: args,
        success: function (result) {
            if (result.status == "200" && result.data != null) {
                var data = result.data;
                if (option == "reload") {
                    ajaxPageInf = null;
                    resetPagination(id, 2, defaultIndex)
                    $.each(data.data, function (key, value) {
                        var stat_str = value.status == 1 ? "在校" : "离校";
                        var trs = "<tr><td><input type='checkbox'></td><td>" + value.account +
                            "</td><td>" + value.username + "</td><td>" + value.dormitoryname +
                            "</td><td>" + value.mobile + "</td><td>" + value.email +
                            "</td><td>" + stat_str + "</td><td><a href='javascript:delUser(" + value.account +
                            ")'>删除</a><a href='javascript:void(0);'>编辑</a></td></tr>"
                        $("#table_2 tbody").append(trs);
                    })
                    ajaxPageInf = data.pageInf;
                    resetPagination(id, 2, defaultIndex)
                } else if (option == "reset") {
                    var trs = $("#table_2 tbody tr");
                    trs.css("display", "table-row")
                    var len = trs.length;
                    var i;
                    for (i = 0; i < len; i++) {
                        var tds = trs[i].childNodes;
                        if (typeof (data.data[i]) != "undefined") {
                            tds[0].innerHTML = "<input type='checkbox'>"
                            tds[1].innerHTML = data.data[i].account;
                            tds[2].innerHTML = data.data[i].username;
                            tds[3].innerHTML = data.data[i].dormitoryname;
                            tds[4].innerHTML = data.data[i].mobile;
                            tds[5].innerHTML = data.data[i].email;
                            tds[6].innerHTML = data.data[i].status == 1 ? "在校" : "离校";
                            tds[7].innerHTML = "<a href='javascript:void(0);' onclick='delUser(" + data.data[i].account + ")'>删除</a><a href='javascript:void(0);'>编辑</a>"
                        } else {
                            trs.eq(i).css("display", "none")
                        }
                    }
                }
                ajaxPageInf = data.pageInf;
                $(".bar_2 .sumNum").text("共" + ajaxPageInf.totalNum + "条数据");
            } else if (result.status == "500") {
                $("#exceptionPanel").modal("show");
                setTimeout(function () {
                    $("#exceptionPanel").modal("hide");
                }, 1500)
            }
        }
    })
}

/*删除用户*/
function delUser(accounts) {
    deleteBySelects("/user", accounts)
}


/*****************************宿舍管理function********************************/

/*获取宿舍列表*/
function showtable_3(pageInf, dormName, option) {
    if (option == "reload") {
        // $("#module_3").mLoading("show");
        $("#table_3 tbody").empty(); //清空tbody
    }
    $("#table_3 thead input[type=checkbox]").prop("checked", false);
    var args = {
        pageNum: pageInf.pageNum,
        pageSize: pageInf.pageSize,
        dormName: dormName
    }
    $.ajax({
        type: 'GET',
        url: '/dorm',
        data: args,
        dataType: 'json',
        timeout: '10000',
        success: function (result) {
            if (result.status == "200" && result.data != null) {
                var trs = null;
                var data = result.data;
                if (option == "reload") {
                    ajaxPageInf = null;
                    resetPagination(id, 2, defaultIndex)
                    $.each(data.data, function (key, value) {
                        trs += "<tr><td><input type='checkbox'></td><td>" + value.dormitoryid +
                            "</td><td>" + value.dormitoryname + "</td><td>" + value.balance +
                            "</td><td><a href='javascript:delDorm(" + value.dormitoryid +
                            ")'>删除</a><a href='javascript:void(0);'>编辑</a></td></tr>"

                    })
                    $("#table_3 tbody").append(trs);
                    ajaxPageInf = data.pageInf;
                    resetPagination(id, 2, defaultIndex)
                } else if (option == "reset") {
                    var trs = $("#table_3 tbody tr");
                    trs.css("display", "table-row")
                    var len = trs.length;
                    var i;
                    for (i = 0; i < len; i++) {
                        var tds = trs[i].childNodes;
                        if (typeof (data.data[i]) != "undefined") {
                            tds[0].innerHTML = "<input type='checkbox'>"
                            tds[1].innerHTML = data.data[i].dormitoryid;
                            tds[2].innerHTML = data.data[i].dormitoryname;
                            tds[3].innerHTML = data.data[i].balance;
                            tds[4].innerHTML = "<a href='javascript:void(0);' onclick='delDorm(" + data.data[i].dormitoryid + ")'>删除</a><a href='javascript:void(0);'>编辑</a>"
                        } else {
                            trs.eq(i).css("display", "none")
                        }
                    }
                }
                ajaxPageInf = data.pageInf;
                $(".bar_3 .sumNum").text("共" + ajaxPageInf.totalNum + "条数据");
            } else if (result.status == "500") {
                $("#exceptionPanel").modal("show");
                setTimeout(function () {
                    $("#exceptionPanel").modal("hide");
                }, 1500)
            }
        },
        error: function (status) {
            alert("请求失败！")
        }
    });
}

/*删除宿舍*/
function delDorm(dormIds) {
    deleteBySelects("/dorm", dormIds)
}


/*****************************通告function********************************/

/* 获取通告列表 */
function showtable_4(args, option) {
    if (option == "reload") {
        $("#table_4 tbody").empty(); //清空tbody
    }
    $("#table_4 thead input[type=checkbox]").prop("checked", false);
    $.ajax({
        type: 'GET',
        url: '/notice',
        dataType: 'json',
        timeout: '10000',
        data: args,
        success: function (result) {
            if (result.status == "200" && result.data != null) {
                var data = result.data;
                if (option == "reload") {
                    ajaxPageInf = null;
                    resetPagination(id, 2, defaultIndex)
                    $.each(data.data, function (key, value) {
                        var trs = "<tr><td><input type='checkbox'></td><td>" + value.noticeid +
                            "</td><td>" + value.title + "</td><td><a href='javascript:showNoticContent(" + value.noticeid +
                            ")'>点击查看内容详情</a></td><td>" + value.publisher + "</td><td>" + value.publishdate +
                            "</td><td><a href='javascript:delNotice(" + value.noticeid +
                            ")'>删除</a><a href='javascript:editNotice(" + value.noticeid + ")'>编辑</a></td></tr>"
                        $("#table_4 tbody").append(trs);
                    })
                    ajaxPageInf = data.pageInf;
                    resetPagination(id, 2, defaultIndex)
                } else if (option == "reset") {
                    var trs = $("#table_4 tbody tr");
                    trs.css("display", "table-row")
                    var len = trs.length;
                    var i;
                    for (i = 0; i < len; i++) {
                        var tds = trs[i].childNodes;
                        if (typeof (data.data[i]) != "undefined") {
                            tds[0].innerHTML = "<input type='checkbox'>"
                            tds[1].innerHTML = data.data[i].noticeid;
                            tds[2].innerHTML = data.data[i].title;
                            tds[3].innerHTML = "<a href='javascript:showNoticContent(" + data.data[i].noticeid + ")'>点击查看内容详情</a>"
                            tds[4].innerHTML = data.data[i].publisher;
                            tds[5].innerHTML = data.data[i].publishdate;
                            tds[6].innerHTML = "<a href='javascript:void(0);' onclick='delNotice(" + data.data[i].noticeid + ")'>删除</a><a href='javascript:void(0);'>编辑</a>"
                        } else {
                            trs.eq(i).css("display", "none")
                        }
                    }
                }
                ajaxPageInf = data.pageInf;
                $(".bar_4 .sumNum").text("共" + ajaxPageInf.totalNum + "条数据");
            } else if (result.status == "500") {
                $("#exceptionPanel").modal("show");
                setTimeout(function () {
                    $("#exceptionPanel").modal("hide");
                }, 1500)
            }
        }
    })
}

/*获取搜索条件信息*/
function getSearchNoticInf(option) {
    var pageInf = getPageInf(id);
    var title = "";
    var publisher = "";
    var startDate = "";
    var endDate = "";
    if (option == "section") {
        var inputs = $("#module_4 .serch_table input");
        title = $.trim(inputs.eq(0).val());
        publisher = $.trim(inputs.eq(1).val());
        startDate = inputs.eq(2).val();
        endDate = inputs.eq(3).val();
    }
    var noticeSearchInf = {
        pageNum: pageInf.pageNum, pageSize: pageInf.pageSize,
        title: title, publisher: publisher, startDate: startDate, endDate: endDate
    }
    return noticeSearchInf;
}

/* 删除通告 */
function delNotice(ids) {
    deleteBySelects("/notice", ids)
}

/*获取单条通告的内容，return：{title,content,publisher,publishdate }*/
function getNoticeContent(noticeid) {
    var data = null;
    var args = {noticeId: noticeid}
    $.ajax({
        type: 'GET',
        url: '/notice/content',
        data: args,
        dataType: 'json',
        async: false,
        success: function (result, status) {
            if (result.status == "200" && result.data != null) {
                data = result.data;
            }
        }
    })
    return data;
}

/*显示单条通告的内容*/
function showNoticContent(noticeid) {
    var data = getNoticeContent(noticeid);

    $("#noticeContent .modal-title").empty();
    $("#noticeContent .modal-body").empty();
    $("#noticeContent .modal-footer").empty();

    $("#noticeContent .modal-title").append(data.title);
    $("#noticeContent .modal-body").append(data.content);
    $("#noticeContent .modal-footer").append("by: " + data.publisher + "&nbsp;&nbsp;&nbsp;Date: " + data.publishdate);

    $("#noticeContent").modal("show");
}

/*编辑单条通告的内容*/
function editNotice(noticeid) {

    createRichEditor();

    var data = getNoticeContent(noticeid);
    //title
    $("#noticeTitle").val(data.title);
    //noticeid
    $("#noticeUpdate .modal-content").attr("id", noticeid);
    //content
    noticeEditor.txt.html(data.content)

    //点击外部不关闭模态框
    $("#noticeUpdate").modal({backdrop: 'static', keyboard: false});
    $("#noticeUpdate").modal("show");
}

/* 提交内容 */
function submitNotice() {
    //获取noticeid，若为空，则是add，否则update
    var noticeid = $("#noticeUpdate .modal-content").attr("id");
    var content = noticeEditor.txt.html();
    var title = $("#noticeTitle").val();
    var publisher = 'Kim'

    var args = {noticeid: noticeid, title: title, content: content, publisher: publisher}
    $.ajax({
        type: 'POST',
        url: '/notice',
        dataType: 'json',
        timeout: '10000',
        data: args,
        success: function (result) {
            if (result.status == '200' && result.data != null) {
                if (noticeid != null && noticeid != '') {
                    alert("已成功更新" + result.data + "条通告！")
                } else {
                    alert("已成功添加" + result.data + "条通告！")
                }
                showtable_4(getSearchNoticInf("section"), "reload")
                $("#noticeUpdate").modal("hide");
            }
        }
    })

}

//创建富文本编辑器
function createRichEditor() {

    var E = window.wangEditor
    if (noticeEditor == null) {
        noticeEditor = new E('#noticeRichEditor')
        noticeEditor.create()
    }
}


/*****************************反馈function********************************/
//获取 搜索条件信息
function getSearchFeedbackInf(option) {
    var pageInf = getPageInf(id);
    var title = "";
    var startDate = "";
    var endDate = "";
    var status = "2";
    if (option == "section") {
        var inputs = $("#module_5 .serch_table input");
        title = $.trim(inputs.eq(0).val());
        startDate = inputs.eq(1).val();
        endDate = inputs.eq(2).val();
        status = $("#module_5 .serch_table select option:selected").eq(0).attr("value");
    }
    var searchInf = {
        pageNum: pageInf.pageNum, pageSize: pageInf.pageSize,
        title: title, startDate: startDate, endDate: endDate, status: status
    }
    return searchInf;
}

/* 获取反馈列表 */
function showtable_5(args, option) {
    if (option == "reload") {
        $("#table_5 tbody").empty(); //清空tbody
    }
    $("#table_5 thead input[type=checkbox]").prop("checked", false);
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
                    resetPagination(id, 2, defaultIndex)
                    $.each(data.data, function (key, value) {
                        var status = value.fstatus == 0 ? "未处理" : "已处理"
                        var trs = "<tr><td><input type='checkbox'></td><td>" + value.fid +
                            "</td><td>" + value.title + "</td><td><a href='javascript:showFBContent(" + value.fid +
                            ")'>点击查看内容详情</a></td><td>" + value.sender + "</td><td>" + status +
                            "</td><td>" + value.fdate + "</td><td><a href='javascript:delFeedback(" + value.fid +
                            ")'>删除</a><a href='javascript:editNotice(" + value.fid + ")'>处理</a></td></tr>"
                        $("#table_5 tbody").append(trs);
                    })
                    ajaxPageInf = data.pageInf;
                    resetPagination(id, 2, defaultIndex)
                } else if (option == "reset") {
                    var trs = $("#table_5 tbody tr");
                    trs.css("display", "table-row")
                    var len = trs.length;
                    var i;
                    for (i = 0; i < len; i++) {
                        var tds = trs[i].childNodes;
                        if (typeof (data.data[i]) != "undefined") {
                            tds[0].innerHTML = "<input type='checkbox'>"
                            tds[1].innerHTML = data.data[i].fid;
                            tds[2].innerHTML = data.data[i].title;
                            tds[3].innerHTML = "<a href='javascript:showFBContent(" + data.data[i].fid + ")'>点击查看内容详情</a>"
                            tds[4].innerHTML = data.data[i].sender;
                            tds[5].innerHTML = (data.data[i].fstatus == 0 ? "未处理" : "已处理");
                            tds[6].innerHTML = data.data[i].fdate;
                            tds[7].innerHTML = "<a href='javascript:void(0);' onclick='delFeedback(" + data.data[i].fid + ")'>删除</a><a href='javascript:void(0);'>编辑</a>"
                        } else {
                            trs.eq(i).css("display", "none")
                        }
                    }
                }
                ajaxPageInf = data.pageInf;
                $(".bar_4 .sumNum").text("共" + ajaxPageInf.totalNum + "条数据");
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

//删除单条反馈
function delFeedback(fid) {
    deleteBySelects("/feedback", fid);
}