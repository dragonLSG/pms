$(function () {

    $("#l-register input").focus(function () {
        $("#registMsg").text("");
    })

})

function noEnter(input) {
    if (event.keyCode == 13) {
        event.preventDefault();
        return false;
    }
}

function submitRegist() {

    //设置dormName
    var selects = $("#dormName option:selected");
    var input = $("#dormName input").val();
    $("#dormitoryname").val(selects.eq(0).text() + selects.eq(1).text() + input);

    if (checkFields()) {
        $("#registform").submit();
    } else {
        return false;
    }

}

function checkFields() {
    var inputs = $("#l-register input");

    //校验account
    var account = $.trim(inputs.eq(0).val());
    if (account == "") {
        $("#registMsg").text("请输入账号");
        return false;
    } else {
        var reg = /^[0-9]+$/
        if (!reg.test(account)) {
            $("#registMsg").text("账号应只包含数字");
            return false;
        }
        var args = {account: account};
        //检查账号是否被使用
        $.ajax({
            type: 'GET',
            url: '/user/checkAccount',
            data: args,
            dataType: 'json',
            success: function (result, status) {
                if (result.status == "200" && result.data != null) {
                    if (result.data == 1) {
                        $("#registMsg").text("账号已存在，请确认");
                    }
                }
            }
        })
    }

    //校验username
    var username = $.trim(inputs.eq(1).val());
    if (username == "") {
        $("#registMsg").text("请输入用户名");
        return false;
    }

    //校验password
    var password = $.trim(inputs.eq(2).val());
    if (password == "") {
        $("#registMsg").text("请输入密码");
        return false;
    }

    //校验dorm
    var dorm = $.trim(inputs.eq(4).val());
    if (dorm == "") {
        $("#registMsg").text("请输入宿舍号");
        return false;
    } else {
        var reg = /^\d{3}$/;
        if (!reg.test(dorm)) {
            $("#registMsg").text("不存在该宿舍号");
            return false;
        }
    }

    //校验phone
    var phone = $.trim(inputs.eq(5).val());
    if (phone == "") {
        $("#registMsg").text("请输入手机号");
        return false;
    } else {
        var reg = /^\d{11}$/;
        if (!reg.test(phone)) {
            $("#registMsg").text("未知手机号，请检查");
            return false;
        }
    }

    //校验checkNum
    var checkNum = $.trim(inputs.eq(6).val());
    if (checkNum == "") {
        $("#registMsg").text("请输入校验码");
        return false;
    }

    //校验email
    var email = $.trim(inputs.eq(7).val());
    if (checkNum != "") {
        var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!reg.test(email)) {
            $("#registMsg").text("邮箱不符合规范，请检查");
            return false;
        }
    }

    return true;
}