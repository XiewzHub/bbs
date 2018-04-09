/**
 * Created by Administrator on 2017/5/19.
 */
function paginationControl() {
    // alert(param.pageNo);
    $("a[name='pagination']").each(function (index, elem) {
        $(elem).unbind('click');
        $(elem).bind('click', function () {
            var pageNo = $(this).attr('pageNo');
            var pageSize = $(this).attr('pageSize');
            var pageAllNo = $(this).attr('pageAllNo');
            var postsId = $("#postsId1").val();
            var url = "/smart_froum/postsComment/" + postsId + "/ajaxFindComment";

            var commentPagination = {
                "pageNo": pageNo,
                "pageSize": pageSize,
                "pagesAllNo":pageAllNo

            }
            var jsonString = JSON.stringify(commentPagination);
            // alert(JSON.stringify(commentPagination));
            // var url = param.url;
            $.ajax({
                url: url,
                type: "POST",
                data: jsonString,
                dataType: 'json',
                contentType: "application/json",
                success: function (data) {
                    $("#commentAreaDiv").html(data.html);
                    //成功后再次绑定
                    paginationControl();
                },
                error: function (data) {
                    alert(data.status);
                }
            });
        });
    });
    $("a[name='skipBtn']").each(function (index, elem) {
        $(elem).unbind('click');
        $(elem).bind('click', function () {
            var pageSize = $(this).attr('pageSize');
            var pageAllNo = $(this).attr('pageAllNo');
            var postsId = $("#postsId1").val();
            var pageNo;
            $("input[name='skipPageNo']").each(function (index1, elem1) {
                var temp = $(elem1).val();
                if (isInteger(temp) && temp != '') {
                    pageNo = temp;
                }
                $(elem1).val('');
            });
            if (pageNo == undefined || pageNo == "" || pageNo <= 0) {
                alert("请输入页码")
            } else {
                var url = "/smart_froum/postsComment/" + postsId + "/ajaxFindComment";
                var commentPagination = {
                    "pageNo": pageNo,
                    "pageSize": pageSize,
                    "pagesAllNo":pageAllNo
                }
                var jsonString = JSON.stringify(commentPagination);
                alert(JSON.stringify(commentPagination));
                // var url = param.url;
                $.ajax({
                    url: url,
                    type: "POST",
                    data: JSON.stringify(commentPagination),
                    dataType: 'json',
                    contentType: "application/json",
                    success: function (data) {
                        $("#commentAreaDiv").html(data.html);
                        paginationControl();
                    },
                    error: function (data) {
                        alert(data.status);
                    }
                });
            }

        });
    });
    $("input[name='skipPageNo']").each(function (index, elem) {
        $(elem).unbind('click');
        $(elem).bind('click', function () {
            $("input[name='skipPageNo']").each(function (index, elem) {
                $(elem).val("");
            });
        });
    });

}

function isInteger(obj) {
    return obj % 1 === 0;
}
