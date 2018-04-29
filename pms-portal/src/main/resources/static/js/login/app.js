var materialAdmin = angular.module('materialAdmin', [
    'ngAnimate',
    'ngResource',
    'ui.router',
    'ui.bootstrap',
    'angular-loading-bar',
    'oc.lazyLoad',
    'nouislider',
    'ngTable'
])
$(function () {

    /* Enter 键盘操作提交*/
    $("#l-login input").keydown(function (e) {
        var event = e;
        if (event.keyCode == 13) {
            if ($(this).attr("name") == "username") {
                $("#l-login input").eq(1).focus();
            } else {
                if ($.trim($("#l-login input").eq(0).val()) == "") {
                    $("#l-login input").eq(0).focus();
                    alert("请输入完整验证信息")
                    return false;
                }
                if ($.trim($("#l-login input").eq(1).val()) == "") {
                    alert("请输入完整验证信息")
                    return false;
                }
                submitLogin();
            }
        }
    });

    $("#l-forget-password input").focus(function () {
        $("#forgetError").text("");
        $("#forgetSuccess").text("");
    })
})

//提交密码修改
function submitReset() {
    if (!validReset()) {
        return false;
    } else {
        $("#resetForm").submit();
    }
}

function validReset() {

    var inputs = $("#l-forget-password input");
    var account = $.trim(inputs.eq(0).val());
    var newPasswd = $.trim(inputs.eq(1).val());
    var newPasswd2 = $.trim(inputs.eq(2).val());
    var phone = $.trim(inputs.eq(3).val());
    var email = $.trim(inputs.eq(4).val());
    var checkNum = $.trim(inputs.eq(5).val());

    if (account == "") {
        $("#forgetError").text("请输入账号");
        return false;
    }
    if (newPasswd == "" || newPasswd2 == "") {
        $("#forgetError").text("请输入新密码/重输密码");
        return false;
    }
    if (!newPasswd == newPasswd2) {
        $("#forgetError").text("两次密码输入不一致");
        return false;
    }
    if (phone == "" && email == "") {
        $("#forgetError").text("请输入接收方式");
        return false;
    }
    if (checkNum == "") {
        $("#forgetError").text("请输入接收方式");
        return false;
    }
    return true;
}

/*发送验证码*/
function sendCheckNum(btn) {
    var inputs = $("#l-forget-password input");
    var account = $.trim(inputs.eq(0).val());
    var phone = $.trim(inputs.eq(3).val());
    var email = $.trim(inputs.eq(4).val());

    if (account == "") {
        $("#forgetError").text("请输入账号");
        return false;
    }
    if (phone == "" && email == "") {
        $("#forgetError").text("请输入接收方式");
        return false;
    }

    var args = {account: account, phone: phone, email: email};
    $(btn).text("正在发送中...")
    $(btn).attr('disabled', true);
    $.ajax({
        type: 'GET',
        url: '/sendCheckNum',
        data: args,
        dataType: 'json',
        success: function (result, status) {
            if (result.status == "200") {
                setTimeout(function () {
                    $("#forgetSuccess").text(result.message);
                }, 5000)
                //重发校验码 延迟50s
                var time = 50;
                var num = self.setInterval(function () {
                    if (time == 0) {
                        window.clearInterval(num);
                        $(btn).text("重发校验码");
                        $(btn).attr('disabled', false);
                    } else {
                        $(btn).text((--time) + "s后重发")
                    }
                }, 1000)

            }
            if (result.status == "500") {
                $(btn).text("发送校验码")
                $(btn).attr('disabled', false);
                $("#forgetError").text(result.message);
            }
        }
    })
}

/*手机号 / 邮箱 验证切换*/
function changeWay(select) {
    var text = $(select).find("option:selected").text();
    if (text == "邮箱") {
        $("#mobile").css("display", "none");
        $("#email").css("display", "table");
    } else {
        $("#email").css("display", "none");
        $("#mobile").css("display", "table");
    }
}

/* 提交登陆表单 */
function submitLogin() {
    document.getElementById('loginform').submit();
}

 