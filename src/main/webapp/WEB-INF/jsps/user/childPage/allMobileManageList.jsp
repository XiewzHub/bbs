<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/29
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%--<head>
    <title>Title</title>
</head>--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/css/bootstrapValidator.css">
<script src="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/js/bootstrapValidator.min.js"></script>
<body>

<form id="uploadImgForm" class="form-horizontal" role="form" method="post"
      action="${pageContext.request.contextPath}/mobileType/add">
    <%--设置id隐藏域--%>
    <input id="inputmobiId" name="mobiId" hidden>
    <div class="form-group">
        <label for="inputmobiBrand" class="col-sm-1 control-label padding-LR-0per ">
            <span class="font-set-pub">手机品牌</span></label>
        <div class="col-sm-2">
            <input name="mobiBrand" class="form-control" id="inputmobiBrand" placeholder="品牌"
                   style="padding-right: 0px"/>
        </div>

        <label for="inputmobiSeries" class="col-sm-1 control-label padding-LR-0per ">
            <span class="font-set-pub">手机系列</span></label>
        <div class="col-sm-2">
            <input name="mobiSeries" class="form-control" id="inputmobiSeries" placeholder="系列"
                   style="padding-right: 0px"/>
        </div>

        <label for="inputmobiType" class="col-sm-1 control-label padding-LR-0per ">
            <span class="font-set-pub">手机型号</span></label>
        <div class="col-sm-2">
            <input name="mobiType" class="form-control" id="inputmobiType" placeholder="型号" style="padding-right: 0px"/>
        </div>
    </div>
    <div class="form-group" style="margin-left: 10px;">
        <div style="float: left">

            <img id="mobileTypeHeadId"  width="100px" height="100px">
            <button type="button" class="btn btn-success" onclick="clickHiddenfile()">上传图片</button>
            <%--隐藏的图片上传按钮--%>
            <input name="uploadImg" id="hiddenfile" type="file" style="display: none;">
            <input id="hiddenmobiImg" name="mobiImg" value="" hidden>
        </div>
        <div style="float: left;margin-left: 60px;">
            <span class="font-set-pub">描述</span><textarea id="inputmobiDescription" name="mobiDescription" placeholder="添加手机描述" style="width: 340px;height: 100px"></textarea>
        </div>
        <div style="float: right;margin-top: 60px;">
            <button id="mobileTypeSubmitBtn" type="submit" class="btn btn-primary">添加</button>
        </div>
    </div>
</form>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>
            手机品牌
        </th>
        <th>
            手机系列
        </th>
        <th>
            手机型号
        </th>
        <th>
            操作
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${mobilePagination.mobileTypeList}" var="mobileType" varStatus="status">
        <tr <c:if test="${status.index%2==0}">class="success"</c:if>>
            <td>
                    ${mobileType.mobiBrand}
            </td>
            <td style="width: 60%">
                <a href="${pageContext.request.contextPath}/posts/${posts.id}/view">${posts.title}</a>
                    ${mobileType.mobiSeries}
            </td>
            <td>
                    ${mobileType.mobiType}
            </td>
            <td>
                <a name="deleteMobileType" class="btn btn-danger" style="padding: 5px"  href="javascript:void(0);" mobileTypeId="${mobileType.mobiId}">删除</a>&nbsp;
                <a name="updateMobileType" class="btn btn-warning" style="padding: 5px 10px;"  href="javascript:void(0);" mobileTypeId="${mobileType.mobiId}"
                   mobiBrand="${mobileType.mobiBrand}" mobiSeries="${mobileType.mobiSeries}" mobiType="${mobileType.mobiType}"
                   mobiImg="${pageContext.request.contextPath}${mobileType.mobiImg}" mobiDescription="${mobileType.mobiDescription}"
                >更新</a>
            </td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="4">
            <ul id="allMobileTypePagesUl" class="pagination">
            </ul>

        </td>
    </tr>
    </tbody>
</table>
<script src="${pageContext.request.contextPath}/js-me/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>
<script src="${pageContext.request.contextPath}/js-me/MyUploadImg.js"></script>
<script type="text/javascript">
    function uploadImgFormValidator() {
        $('#uploadImgForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                mobiBrand: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '手机品牌不能为空'
                        },
                        stringLength: {
                            max: 10,
                            message: '不能超过10个字'
                        }
                    }
                },
                mobiSeries: {
                    validators: {
                        notEmpty: {
                            message: '手机系列不能为空'
                        },
                        stringLength: {
                            max: 10,
                            message: '不能超过10个字'
                        }

                    }
                },
                mobiType: {
                    validators: {
                        notEmpty: {
                            message: '手机型号不能为空'
                        },
                        stringLength: {
                            max: 10,
                            message: '不能超过10个字'
                        }
                    }
                },
                mobiDescription:{
                    validators: {
                        stringLength: {
                            max: 50,
                            message: '不能超过50个字'
                        }
                    }
                }
            }
        });
    }
</script>
<script type="text/javascript">
    function deleteMobileType() {
        $("a[name='deleteMobileType']").each(function (index, elem) {
            $(elem).unbind("click");
            $(elem).bind("click", function () {
                var mobileTypeId = $(this).attr("mobileTypeId");
                var url = "${pageContext.request.contextPath}/mobileType/" + mobileTypeId + "/ajaxDelete";
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    data: {"info": 1},
                    success: function (data) {
                        var result = data.result;
                        alert(result);
                        if (data.result == 1) {
                            var newUrl = window.location.href;
                            if(newUrl.search("myPostsManager")>=0) {
                                newUrl = newUrl.replace("myPostsManager", "mobileTypeManager");
                            }else if(newUrl.search("manager")>=0){
                                newUrl = newUrl.replace("manager", "mobileTypeManager");
                            }else if(newUrl.search("allPostsManager")>=0){
                                newUrl = newUrl.replace("allPostsManager","mobileTypeManager");
                            }
                            window.location.href = newUrl;
                        } else if(result==2) {
                            alert("先登录才可以执行此操作")
                        }else {
                            alert("删除失败，请刷新页面重试。")
                        }
                    },
                    error: function (data) {
                        alert("后台出错,请联系管理员");
                    }
                });
            });
        });
    }
    //点击更新操作
    function updateMobileType(){
        $("a[name='updateMobileType']").each(function (index, elem) {
            $(elem).unbind("click");
            $(elem).bind("click", function () {
                var mobiId = $(this).attr("mobileTypeId");
                var mobiBrand = $(this).attr("mobiBrand");
                var mobiSeries = $(this).attr("mobiSeries");
                var mobiType = $(this).attr("mobiType");
                var mobiImg = $(this).attr("mobiImg");
                var mobiDescription = $(this).attr("mobiDescription");
                $("#inputmobiId").val(mobiId);
                $("#inputmobiBrand").val(mobiBrand);
                $("#inputmobiSeries").val(mobiSeries);
                $("#inputmobiType").val(mobiType);
                $("#mobileTypeHeadId").attr("src",mobiImg);
                $("#hiddenmobiImg").val(mobiImg);
                $("#inputmobiDescription").val(mobiDescription);
                $("#mobileTypeSubmitBtn").html("修改");
                <%--var url = "${pageContext.request.contextPath}/mobileType/" + mobileTypeId + "/ajaxDelete";--%>

            });
        });
    }

    $(function () {
        //当前页
        var pageNo = ${mobilePagination.pageNo};
        //总页数
        var pageCount = ${mobilePagination.pagesAllNo};
        var pageSize =${mobilePagination.pageSize};
        var preUrl = "${pageContext.request.contextPath}/user/mobileTypeManager";
        var data = {
//                        author: $("#authorParamData").val(),
//                        title: $("#titleParamData").val()
        };
        var html = generatePostsPagesHtml(pageNo, pageSize, pageCount, preUrl, null);
        $("#allMobileTypePagesUl").html(html);
        deleteMobileType();
        updateMobileType();
        var uploadImgUrl="${pageContext.request.contextPath}/upload/uploadMobileTypeHead";
        uploadImg(uploadImgUrl);
        uploadImgFormValidator();
    });

</script>

</body>
</html>
