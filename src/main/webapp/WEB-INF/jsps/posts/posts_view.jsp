<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Smart帖子详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginSign.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/css/bootstrapValidator.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/editormd/css/editormd.min.css"/>

    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />--%>

    <%--<script src="${pageContext.request.contextPath}/js-me/jquery.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js-me/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/editormd/editormd.js"></script>
    <script src="${pageContext.request.contextPath}/editormd/lib/marked.min.js"></script>
    <script src="${pageContext.request.contextPath}/editormd/lib/flowchart.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/js/bootstrapValidator.min.js"></script>


    <%--<script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>--%>


</head>

<body>
<div id="bg">
    <img src="${pageContext.request.contextPath}/images/backgroundImg.jpg" alt="背景图">
</div>
<!--导航栏-->
<jsp:include page="../common/navbarPage.jsp"></jsp:include>
<br>
<!--帖子的详细内容-->
<div class="container">
    <div class="row clearfix">
        <%--<div class="col-sm-1"></div>--%>
        <div class="col-md-3 ">
            <div class="panel">
                <div class="panel-heading panel-head-color">
                    <c:if test="${empty user}">登录</c:if>
                </div>
                <c:if test="${empty user}">
                    <div id="panelBodyContent" class="panel-body">
                            <%--登录页--%>
                        <jsp:include page="../user/childPage/loginPage1.jsp" flush="true"></jsp:include>
                    </div>
                </c:if>
                <c:if test="${not empty user}">
                    <div id="" class="panel-body" style="padding: 0px;height: 223px;">
                            <%--用户名片--%>
                        <jsp:include page="../user/childPage/userCard.jsp" flush="true"></jsp:include>
                    </div>
                </c:if>
            </div>
        </div>
        <%--左边一列--%>

        <div class="col-md-9">
            <div class="panel">

                <div class="panel-heading ">
                    <input id="postsId1" value="${posts.id}" hidden>
                    <div id="postsTitle1" style="font-size: x-large;padding-bottom: 20px">
                        ${posts.title}
                    </div>
                    <div class="introduce-font-style" <%--style="color: #888"--%>>
                        <span style="float: left;margin-left: 10px;">${posts.date}</span>
                        <span style="float: left">&nbsp;|&nbsp;
                            ${posts.user.name}
                        </span>
                        <c:if test="${posts.user.name==user.name}">
                        <span style="float: left">&nbsp;|&nbsp;
                            <a href="${pageContext.request.contextPath}/posts/${posts.id}/edit">编辑</a>
                        </span>
                            <span style="float: left">&nbsp;|&nbsp;
                            <a href="${pageContext.request.contextPath}/posts/${posts.id}/delete">删除</a>
                        </span>
                        </c:if>
                        <c:if test="${user.isAdmin=='Y'}">
                            <c:if test="${posts.postsCarousel.postsId!=posts.id}">
                        <span style="float: left">&nbsp;|&nbsp;<a href="javascript:void(0);" data-toggle="modal"
                                                                  data-target="#myModal">放至主页轮播</a></span>
                            </c:if>
                            <c:if test="${posts.postsCarousel.postsId==posts.id}">
                                <span style="float: left">&nbsp;|&nbsp;<a id="deleteCarousel"
                                                                          href="javascript:void(0);">删除轮播</a></span>
                            </c:if>
                        </c:if>

                        <span class="eye-space glyphicon glyphicon-eye-open">${posts.viewNum}</span>
                        <span class="comment-space glyphicon glyphicon-comment">${posts.commentNum}</span>
                    </div>
                </div>

                <div class="panel-body" style="">
                    <div class="editormd-preview "
                         style="display: block;padding: 15px;position: inherit;width: 100% /*width: 571px; top: 87px; height: 612px;*/">
                        <div class="markdown-body editormd-preview-container" previewcontainer="true"
                             style="padding: 20px;">
                            ${posts.text}
                        </div>
                    </div>
                </div>

                <div class="panel-footer">
                    <div id="commentAreaDiv">
                        ${paginationHtml}
                        <%--评论区域--%>
                        <ul class="remove-li-point">
                            <c:forEach items="${commentPagination.postsCommentList}" var="postsComment"
                                       varStatus="status">
                                <!--一条完整的信息-->
                                <div class="fade-in">
                                    <li class="row">
                                        <!--头像-->
                                        <div class="col-md-2">
                                            <a href="javascript:void(0);"><img src="${postsComment.user.head}"
                                                                               class="head-style-set"
                                                                               style="width: 80px;height: 80px;">
                                            </a>
                                        </div>

                                        <div class="col-md-10" style="float: left;">
                                            <div class="row" style="padding-right: 10px;">
                                                <div style="font-size: small;color: #888;float:left;">
                                                    <span>评论者:${postsComment.user.name}</span>
                                                    <span>|</span>
                                                    <span>评论时间:${postsComment.commentDate}</span>
                                                </div>
                                                    <%--楼层--%>
                                                <div style="font-size: small;color: #888;float:right;">
                                                    <span>${((commentPagination.pageNo-1)*commentPagination.pageSize)+status.count}楼</span>
                                                </div>
                                                    <%--评论内容--%>
                                                <div style="float: left;text-align: left;padding: 18px;padding-left: 1px;width: 100%;">
                                                    <span style="font-size: 16px;color: #000"><p>${postsComment.commentText}</p></span>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <!--分割线-->
                                    <ol class="divider"></ol>
                                </div>
                            </c:forEach>
                        </ul>
                        ${paginationHtml}
                    </div>
                    <%--分页跳转--%>
                    <script src="${pageContext.request.contextPath}/js-me/commentPagination.js"></script>
                    <script type="text/javascript">
                        $(function () {
                            var contextPath = "${pageContext.request.contextPath}";
                            paginationControl(contextPath);
                        });
                    </script>

                    <form id="commentForm" method="post" role="form"
                          action="${pageContext.request.contextPath}/postsComment/addComment">
                        <input hidden name="postsId" value="${posts.id}"/>
                        <div class="form-group" style="text-align: left">
                            <label for="commentTextId"></label>
                            <textarea placeholder="回复内容不能为空" id="commentTextId" name="commentText" class="form-group"
                                      style="width: 77%;height: 160px;margin-left: 130px;"></textarea>
                        </div>
                        <div class="form-group" style="text-align: left">
                            <button id="submitCommentTextId" disabled="disabled" type="submit" class="btn btn-info"
                                    style="float:right;margin-top: -65px;">
                                回复
                            </button>
                        </div>
                    </form>
                    <script type="text/javascript">

                        function commentValidator() {
                            $("#commentTextId").unbind('change')
                            $("#commentTextId").bind('change', function () {
                                var temp = $("#commentTextId").val();
                                if (temp.trim() == '') {
                                    $("#submitCommentTextId").attr("disabled", "disabled");
                                } else {
                                    $("#submitCommentTextId").removeAttr("disabled");
                                }
                            });
                        }
                        $(function () {
                            commentValidator();
                        });

                    </script>

                </div>
            </div>
        </div>
        <%--右边一列，存放帖子内容--%>
    </div>
    <%--行元素--%>
</div>
<%--页面容器--%>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    请上传一张图片作为轮播图片
                </h4>
            </div>
            <%--模态框 主体--%>
            <div class="modal-body">
                <form id="uploadImgForm" enctype="multipart/form-data" method="post">
                    <button type="button" value="选择图片" class="btn btn-success" onclick="clickHiddenfile()">选择本地图片
                    </button>
                    <span class="" style="float:left;width:15%;margin-top: 7px;">图片地址：</span>
                    <input id="showSrc" type="text" class="form-control" disabled
                           style="width: 50%;float: left;margin-left: 0px;">
                    <%--隐藏的图片上传input--%>
                    <input name="uploadImg" id="hiddenfile" type="file" style="display: none">
                    <button id="submitImg" name="submitImg" hidden>提交</button>
                    <div id="imgDiv" style="padding: 1%;"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" style="float: left;"
                        data-dismiss="modal">关闭
                </button>
                <form method="post">
                    <input id="showSrc1" value="" name="imgAddress" hidden>
                    <input id="postsId" name="postsId" value="${posts.id}" hidden>
                    <input id="postsTitle" name="postsTitle" value="${posts.title}" hidden>
                    <input id="postsUrl" name="postsUrl" value="/posts/${posts.id}/view" hidden>
                    <button id="addCarousel" type="button" class="btn btn-primary">
                        提交更改
                    </button>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="${pageContext.request.contextPath}/js-me/jquery.form.js"></script>
<script type="text/javascript">
    $(function () {
        uploadImg();
        //提交模态框数据，轮播数据
        addCarousel();
        //删除
        deleteCarousel();
    });

    /**
     * 删除方法
     */
    function deleteCarousel() {
        $("#deleteCarousel").unbind('click');
        $("#deleteCarousel").bind('click', function () {
            /* var postsId = $("#postsId1").val();
             alert(postsId);*/
            var carousel = {
                postsId: $("#postsId1").val()
            };

            $.ajax({
                url: "${pageContext.request.contextPath}/carousel/delete",
                data: carousel,
                dataType: "json",
                success: function (data) {
                    alert(data.message);
                    window.location.href=window.location.href;
                }
            });
        });

    }

    function addCarousel() {
        $("#addCarousel").unbind("click");
        $("#addCarousel").bind("click", function () {
            if ($("#showSrc1").val() == "") {
                alert("请选择图片");
                return;
            }
            var carousel = {
                imgAddress: $("#showSrc1").val(),
                postsId: $("#postsId").val(),
                postsTitle: $("#postsTitle").val(),
                postsUrl: $("#postsUrl").val()
            };

            $.ajax({
                url: "${pageContext.request.contextPath}/carousel/add",
                type: "POST",
                data: carousel,
                dataType: "json",
                success: function (data) {
                    alert(data.message);
                    $("#myModal").modal('hide');
                    window.location.href=window.location.href;
                }
            });
        });
    }

    function uploadImg() {
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
                        url: "${pageContext.request.contextPath}/upload/uploadCarouselImg",
                        dataType: "json",
                        success: function (data) {
                            var imgUrl = data.url;

                            $("#showSrc").val(imgUrl);
                            $("#showSrc1").val(imgUrl);
                            $("#imgDiv").html("<img style='height: 200px;margin: 20px' src='" + imgUrl + "'></img>");
                        }
                    });

                }


            }
        );

    }

    <%--图片上传，点击按钮转为点击<input type="file">的功能--%>
    function clickHiddenfile() {
        $("#hiddenfile").click();
    }

</script>

</body>
</html>