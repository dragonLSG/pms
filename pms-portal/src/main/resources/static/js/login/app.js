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

/*发送验证码*/
function sendCheckNum() {
    var inputs = $("#l-forget-password input");
    var account = inputs.eq(0).val();
    var phone = inputs.eq(1).val();
    var email = inputs.eq(2).val();
    var checkNum = inputs.eq(3).val();

    var args = {account: account, phone: phone, email: email};
    $.ajax({
        type: 'GET',
        url: '/sendCheckNum',
        data: args,
        dataType: 'json',
        success: function (result, status) {
            if (result.status == "200") {
                $("#forgetSuccess").text(result.message);
            }
            if (result.status == "500") {
                $("#forgetError").text(result.message);
            }
        }
    })

}

/*手机号 / 邮箱 验证切换*/
function changeWay(select) {
    var text = $(select).find("option:selected").text();
    if (text == "邮箱") {
        $("#mobile input").val("");
        $("#mobile").css("display", "none");
        $("#email").css("display", "table");
    } else {
        $("#email input").val("");
        $("#email").css("display", "none");
        $("#mobile").css("display", "table");
    }
}


/* 提交登陆表单 */
function submitLogin() {
    document.getElementById('loginform').submit();
}

 