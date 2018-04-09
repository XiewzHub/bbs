<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>你好管理员</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginSign.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexStyle.css">


    <script src="${pageContext.request.contextPath}/js-me/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>
    <script src="${pageContext.request.contextPath}/js-me/MyUploadImg.js"></script>

</head>

<body>
<div id="bg">
    <%--<img src="${pageContext.request.contextPath}/images/backgroundImg.jpg" alt="背景图">--%>
</div>

<!--导航栏-->
<jsp:include page="../common/navbarPageManager.jsp" flush="true"></jsp:include>


<div class="container">
    <div class="row clearfix">
        <div class="col-md-3 column" style="">
            <%--用户卡片--%>
            <jsp:include page="childPage/userCard.jsp" flush="true"></jsp:include>
        </div>

        <div class="col-md-9 column" style="">
            <ul id="myTab" class="nav nav-tabs" style="position: fixed;width: 53%">

                <li <c:if test="${info == 'baseData'}">class="active"</c:if>>
                    <a href="#baseData" data-toggle="tab">
                        基本资料
                    </a>
                </li>
                <li <c:if test="${info == 'myPosts'}">class="active"</c:if>>
                    <a href="#myPosts" data-toggle="tab">我的帖子</a></li>
                <c:if test="${user.isAdmin == 'Y'}">
                    <li <c:if test="${info == 'allPosts'}">class="active"</c:if>>
                        <a href="#allPosts" data-toggle="tab">全部帖子管理</a></li>
                    <li <c:if test="${info == 'mobileTypeManager'}">class="active"</c:if>>
                        <a href="#mobileTypeManager" data-toggle="tab">手机分类管理</a>
                    </li>
                </c:if>

            </ul>
            <div id="myTabContent" class="tab-content">
                <%--基本资料--%>
                <div class="tab-pane fade <c:if test="${info == 'baseData'}">in active</c:if>" id="baseData">
                    <div style="margin-top: 55px">
                        <jsp:include page="childPage/baseInfo.jsp" flush="true"></jsp:include>
                    </div>
                </div>
                <%--我的帖子--%>
                <div class="tab-pane fade <c:if test="${info == 'myPosts'}">in active</c:if>" id="myPosts">
                    <div style="margin-top: 55px">
                        <%-- <p>我的帖子。</p>--%>
                        <div class="form-group row">
                            <label for="titleParamDataMy" class="col-sm-1"
                                   style="margin-bottom: 0px;margin-top: 6.7px;">
                                标题
                            </label>
                            <input id="titleParamDataMy" class="form-control col-sm-4" style="width: inherit" placeholder="标题"
                                   value="${postsPages.posts.title}">
                            <div>
                                <button class="btn btn-primary" style="margin-left: 85px"
                                        onclick="searchByConditionMy()">
                                    搜索
                                </button>
                            </div>
                            <script type="text/javascript">
                                function searchByConditionMy() {
                                    var pageSize =${postsPages.pageSize};
                                    var preUrl = "${pageContext.request.contextPath}/user/myPostsManager";
                                    var data = {
                                        author: $("#authorParamDataMy").val(),
                                        title: $("#titleParamDataMy").val()
                                    };
                                    var url = preUrl + "/" + "1/" + pageSize + "/list?" + buildParam(data);
                                    window.location.href = url;
                                }
                            </script>
                        </div>
                        <jsp:include page="childPage/myPostsList.jsp" flush="true"></jsp:include>
                    </div>
                </div>

                <c:if test="${user.isAdmin == 'Y'}">
                    <%--帖子管理 管理员选项--%>
                    <div class="tab-pane fade <c:if test="${info == 'allPosts'}">in active</c:if>" id="allPosts">
                        <div style="margin-top: 55px">
                                <%--<p>您是管理员，您可以查看并删除所有的帖子。</p>--%>
                            <div class="form-group row">
                                <label for="authorParamDataMy" class="col-sm-1"
                                       style="margin-bottom: 0px;margin-top: 6.7px;">
                                    作者
                                </label>
                                <input id="authorParamData" class="form-control col-sm-4" style="width: inherit" placeholder="作者"
                                       value="${postsPagesAll.posts.author}">
                                <label for="titleParamDataMy" class="col-sm-1"
                                       style="margin-bottom: 0px;margin-top: 6.7px;">
                                    标题
                                </label>
                                <input id="titleParamData" class="form-control col-sm-4" style="width: inherit" placeholder="标题"
                                       value="${postsPagesAll.posts.title}">
                                <div>
                                    <button class="btn btn-primary" style="margin-left: 85px"
                                            onclick="searchByCondition()">
                                        搜索
                                    </button>
                                </div>
                                <script type="text/javascript">
                                    function searchByCondition() {
                                        var pageSize =${postsPagesAll.pageSize};
                                        var preUrl = "${pageContext.request.contextPath}/user/allPostsManager";
                                        var data = {
                                            author: $("#authorParamData").val(),
                                            title: $("#titleParamData").val()
                                        };
                                        var url = preUrl + "/" + "1/" + pageSize + "/list?" + buildParam(data);
                                        window.location.href = url;
                                    }
                                </script>
                            </div>
                            <jsp:include page="childPage/allPostsList.jsp" flush="true"></jsp:include>
                        </div>
                    </div>
                    <%--手机管理 管理员选项--%>
                    <div class="tab-pane fade <c:if test="${info == 'mobileTypeManager'}">in active</c:if>"
                         id="mobileTypeManager">
                        <div style="margin-top: 55px">
                            <p>您可以添加、删除以及修改手机型号与信息</p>
                            <jsp:include page="childPage/allMobileManageList.jsp" flush="true"></jsp:include>

                        </div>
                    </div>
                </c:if>


            </div>
        </div>
    </div>
</div>

</body>
</html>