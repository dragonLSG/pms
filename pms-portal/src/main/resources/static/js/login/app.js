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
})

/* 提交表单 */
function submitLogin() {
    document.getElementById('loginform').submit();
}
 