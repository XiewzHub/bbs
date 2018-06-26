<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>帖子列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginSign.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/css/bootstrapValidator.css">

    <script src="${pageContext.request.contextPath}/js-me/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/js/bootstrapValidator.min.js"></script>


</head>

<body>
<%--设置背景--%>
<div id="bg">
    <img src="${pageContext.request.contextPath}/images/backgroundImg.jpg" alt="背景图">
</div>

<jsp:include page="../common/navbarPage.jsp"></jsp:include>

<header class="masthead">
    <div class="container">
        <div class="row ">
            <div class="col-md-8">
                <div class="well pull-left" style="padding: 0px">
                    <%--全部帖子就放默认图片--%>
                    <c:if test="${message=='all'}">
                        <img src="${pageContext.request.contextPath}/images/mobile/homeMobile.jpg" width="150"
                             height="150">
                    </c:if>
                    <c:if test="${message!='all'}">
                        <img src="${pageContext.request.contextPath}${mobileType.mobiImg}" width="150" height="150">
                    </c:if>
                </div>

                <h2 class="title-margin-space">
                    <c:if test="${message=='all'}">
                        <a class="mobile-title-style"
                           href="${pageContext.request.contextPath}/posts/list?"
                           title="本站全部帖子">本站全部帖子</a>
                    </c:if>
                    <c:if test="${message!='all'}">
                        <a class="mobile-title-style"
                           href="${pageContext.request.contextPath}/posts/list?mobiId=${mobileType.mobiId}"
                           title="${mobileType.mobiBrand}${mobileType.mobiSeries}${mobileType.mobiType}论坛">${mobileType.mobiBrand}${mobileType.mobiSeries}${mobileType.mobiType}</a>
                    </c:if>
                </h2>
                <c:if test="${message=='all'}">
                    <p class="title-small-space">这里可以查看所有人发布的帖子哦</p>
                </c:if>
                <c:if test="${message!='all'}">
                    <p class="title-small-space">${mobileType.mobiDescription}</p>
                </c:if>
            </div>
        </div>
    </div>
</header>


<!--帖子放置位置-->
<div class="container">
    <!--一个行-->
    <div class="row clearfix">
        <!--第一列-->
        <div class="col-md-8 column">
            <!--设置面板-->
            <div class="panel">
                <!--头部-->
                <div class="panel-heading panel-head-color">
                    <ul id="postsPagesU2" class="pagination">
                        <%--<li><a href="#">前一页</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">后一页</a></li>

                        <li>
                            <input style="margin-right:6px;margin-left: 12px;float: left;padding: 6px 12px;width: 10%;"
                                   type="text">
                        </li>
                        <li><a href="#">GO!</a></li>--%>
                    </ul>

                </div>

                <div class="panel-body">

                    <ul class="remove-li-point">
                        <c:if test="${empty postsList}">
                            当前还没有任何帖子，快来发布你的第一条帖子吧！
                        </c:if>

                        <c:forEach items="${postsList}" var="posts">
                            <!--一条完整的信息-->
                            <div class="fade-in">
                                <li class="row">
                                    <!--头像-->
                                    <div class="col-md-2">
                                        <a href="${pageContext.request.contextPath}/posts/${posts.id}/view">
                                            <img src="${pageContext.request.contextPath}${posts.user.head}"
                                                 class="head-style-set">
                                        </a>
                                    </div>

                                    <!--帖子内容-->
                                    <div class="col-md-pull-10">
                                        <!--标题-->
                                        <div>
                                            <a href="${pageContext.request.contextPath}/posts/${posts.id}/view"
                                               class="forum-title">${posts.title}</a>
                                        </div>
                                        <!--帖子相关信息-->
                                        <div class="row introduce-style" style="margin-left: 17%">
                                            <small class="introduce-font-style">
                                                <a href="javascript:void(0);">${posts.user.name}</a>
                                                <span class="left-space">${posts.date}</span>
                                                <c:if test="${posts.setTop==1}">
                                                    <span class="left-space">置顶</span>
                                                </c:if>

                                                <span class="eye-space glyphicon glyphicon-eye-open">${posts.viewNum}</span>
                                                <span class="comment-space glyphicon glyphicon-comment">${posts.commentNum}</span>
                                            </small>
                                            <input name="id" value="" hidden>
                                        </div>
                                    </div>
                                    <!--帖子标题与作者-->

                                </li>
                                <!--第一条信息结束-->
                                <ol class="divider"></ol>
                                <!--一条记录到此完成才能分割线-->
                            </div><%--包含一条分割线的一条记录--%>
                        </c:forEach>

                    </ul>

                </div>
                <div class="panel-footer">

                    <ul id="postsPagesUl" class="pagination">

                    </ul>
                    <c:if test="${message!='all'}">
                        <input id="mobileIdHiddenParamData" value="${mobileType.mobiId}" type="number" hidden>
                    </c:if>

                    <script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>
                    <script type="text/javascript">
                        $(function () {
                            //当前页
                            var pageNo = ${postsPages.pageNo};
                            //总页数
                            var pageCount = ${postsPages.pagesAllNo};
                            var pageSize =${postsPages.pageSize};
                            var preUrl = "${pageContext.request.contextPath}/posts/";
                            var search = $("#searchHinddenParamDada").val();
                            var suffixUrl = "";
                            if(search!=''){
                                suffixUrl = "search";
                            }
                            var data = {
                                mobiId: $("#mobileIdHiddenParamData").val(),
                                search:$("#searchHinddenParamDada").val()
                            };

                            var html = generatePostsPagesHtml(pageNo, pageSize, pageCount, preUrl, null, data,suffixUrl);

                            $("#postsPagesUl").html(html);
                            $("#postsPagesU2").html(html);
                        });
                    </script>

                </div>
            </div>
        </div>

        <!--第二列-->
        <div class="col-md-4 column">
            <!--面板1-->
            <div class="panel ">
                <!--<div class="panel-heading panel-head-color" >-->
                <!--<h3 class="panel-title">-->
                <!--Panel title-->
                <!--</h3>-->
                <!--</div>-->
                <div class="panel-body">

                    <a href="${pageContext.request.contextPath}/posts/add" type="button"
                       class="btn btn-lg btn-primary btn-block">
                        <span class="glyphicon glyphicon-edit">我要发帖</span>
                    </a>
                </div>
                <div class="panel-footer">

                </div>
            </div>

            <!--面板2 登录-->
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color: #888888;color: #fff;">
                    &nbsp;
                    <h3 class="panel-title">
                        <c:if test="${empty user}">
                            <span style="margin: 141px;">登录</span>
                        </c:if>
                    </h3>
                </div>
                <c:if test="${empty user}">
                    <div class="panel-body">
                        <!--登录-->
                        <div class="panel panel-default padding-10per" id="loginMenu">
                            <jsp:include page="../user/childPage/loginPage1.jsp" flush="true"></jsp:include>
                        </div>
                    </div>
                </c:if>
                <%--名片--%>
                <c:if test="${not empty user}">
                    <div class="panel-body" style="padding: 0px;height: 223px">
                        <jsp:include page="../user/childPage/userCard.jsp" flush="true"></jsp:include>
                    </div>
                </c:if>

            </div>
            <!--面板3 登录成功的信息面板-->
            <%--<div class="panel panel-default">
                <div class="panel-heading" style="background-color: #888888;color: #fff;">
                    &nbsp;
                    <h3 class="panel-title">
                        Panel title
                    </h3>
                </div>

                <div class="panel-body">
                    Panel content
                </div>
                <div class="panel-footer">
                    Panel footer
                </div>
            </div>--%>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                userName: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '邮箱地址不能为空'
                        },
                        emailAddress: {
                            message: '邮箱输入不正确'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                }
            }
        });
    });
</script>

</body>
</html>