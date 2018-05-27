$(function () {
    //step 1 到 2
    $("#step1-2").click(function () {
        $("#step1").slideUp(1000)
        $("#step2").slideDown(1000)
        $(window.frames["proccess"].document).find("#pstep2")[0].click();
    });

    //step 2 返回 1
    $("#step2-1").click(function () {
        $("#step2").slideUp(1000)
        $("#step1").slideDown(1000)
        $(window.frames["proccess"].document).find("#pstep1")[0].click();
    });
    //step 2 - 3
    $("#step2-3").click(function () {
        $("#step2").slideUp(1000)
        $("#step3").slideDown(1000)
        $(window.frames["proccess"].document).find("#pstep3")[0].click();
    });

    //step 3 - 2
    $("#step3-2").click(function () {
        $("#step3").slideUp(1000)
        $("#step2").slideDown(1000)
        $(window.frames["proccess"].document).find("#pstep2")[0].click();
    });

})