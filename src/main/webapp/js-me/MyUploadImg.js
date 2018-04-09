/**
 * Created by Administrator on 2017/5/17.
 */


function uploadImg(url) {
    $("#hiddenfile").change(
        function () {
            //图片路径
            var imgPath = $("#hiddenfile").val();

            //判断上传文件的后缀名
            var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
            if (strExtension != 'jpg' && strExtension != 'gif' && strExtension != 'jpeg'
                && strExtension != 'png' && strExtension != 'bmp') {
                alert("请选择图片文件");
                return;
            } else {
//                    alert(imgPath);
                $("#uploadImgForm").ajaxSubmit({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    success: function (data) {
                        var imgUrl = data.imgUrl;
                        $("#mobileTypeHeadId").attr('src', imgUrl);
                        $("#hiddenmobiImg").val(imgUrl);


                    }
                });

            }
        }
    );

}

function clickHiddenfile() {
    $("#hiddenfile").click();
}

/**
 * 用户头像上传
 * @param url
 */
function uploadUserHeadImg(url) {
    $("#userHiddenfile").change(
        function () {
            //图片路径
            var imgPath = $("#userHiddenfile").val();

            //判断上传文件的后缀名
            var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
            if (strExtension != 'jpg' && strExtension != 'gif' && strExtension != 'jpeg'
                && strExtension != 'png' && strExtension != 'bmp') {
                alert("请选择图片文件");
                return;
            } else {
//                    alert(imgPath);
                $("#userUpdateForm").ajaxSubmit({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    success: function (data) {
                        var imgUrl = data.imgUrl;
                        $("#userHeadId").attr('src', imgUrl);
                        $("#hiddenUserHeadImg").val(imgUrl);
                    }
                });

            }
        }
    );

}

function clickUserHiddenfile() {
    $("#userHiddenfile").click();
}