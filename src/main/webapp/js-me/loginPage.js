/**
 * Created by Administrator on 2017/4/20.
 */

//点击登录Tab标签跳转到登录页
// function loginTabClick() {
//
// }

$(function () {
    //点击登录Tab标签跳转到登录页
    $("#loginTab").click(function () {
        $("#loginLi").addClass("active");
        $("#registerLi").removeClass("active");
        // $("#loginMenu").removeAttr("hidden");
        // $("#registerMenu").attr("hidden","hidden");
        $("#registerMenu").fadeOut(0);//淡出效果
        $("#loginMenu").fadeIn("slow");//淡入
    });

    //点击注册跳转到注册，隐藏登录页
    $("#registerTab").click(function () {
        $("#registerLi").addClass("active");
        $("#loginLi").removeClass("active");
        // $("#registerMenu").removeAttr("hidden");
        // $("#loginMenu").attr("hidden","hidden");
        $("#loginMenu").fadeOut(0);
        $("#registerMenu").fadeIn("slow");
    });



});



