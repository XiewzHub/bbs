<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎来到Smart论坛首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginSign.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/css/bootstrapValidator.css">

    <script src="${pageContext.request.contextPath}/js-me/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/js/bootstrapValidator.min.js"></script>


    <%--<script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>--%>


</head>

<body>
<div id="bg">
    <img src="${pageContext.request.contextPath}/images/backgroundImg.jpg" alt="背景图">
</div>

<%--固定导航栏页面--%>
<jsp:include page="common/navbarPage.jsp" flush="true"></jsp:include>

<%--轮播窗口--%>
<div class="container" style="margin-bottom: 10px">
    <div id="myCarousel" class="carousel slide">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <c:forEach items="${carouselList}" var="carousel" varStatus="status">
                <li data-target="#myCarousel" data-slide-to="${status.index}" data-ride="carousel"
                        <c:if test="${status.index==0}"> class="active"</c:if> ></li>
            </c:forEach>
        </ol>

        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <c:forEach items="${carouselList}" var="carousel" varStatus="status">
                <div class="item <c:if test="${status.index==0}">active</c:if>">
                    <center>
                        <a href="${pageContext.request.contextPath}${carousel.postsUrl}"><img
                                src="${pageContext.request.contextPath}${carousel.imgAddress}"
                                alt="First slide" height="500px">
                        </a>
                    </center>
                        <%--<div class="carousel-caption">标题 1</div>--%>
                </div>
            </c:forEach>
        </div>

        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#myCarousel"
           data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel"
           data-slide="next">&rsaquo;</a>
    </div>
</div>
<%--轮播框--%>
<script>
    $(function () {
        // 初始化轮播
        $("#myCarousel").carousel({
            //2秒循环一次
            interval: 2000
        });
        /* $(".start-slide").click(function(){

         });*/

        // 停止轮播
        /*$(".pause-slide").click(function(){
         $("#myCarousel").carousel('pause');
         });
         // 循环轮播到上一个项目
         $(".prev-slide").click(function(){
         $("#myCarousel").carousel('prev');
         });
         // 循环轮播到下一个项目
         $(".next-slide").click(function(){
         $("#myCarousel").carousel('next');
         });
         // 循环轮播到某个特定的帧
         $(".slide-one").click(function(){
         $("#myCarousel").carousel(0);
         });
         $(".slide-two").click(function(){
         $("#myCarousel").carousel(1);
         });
         $(".slide-three").click(function(){
         $("#myCarousel").carousel(2);
         });*/
    });
</script>

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
                    <nav role="navigation">
                        <div class="container">
                            <div class="collapse navbar-collapse">
                                <ul class="nav navbar-nav">
                                    <li><a style="color: #000000" href="${pageContext.request.contextPath}/mobileType/list">选择机型</a></li>
                                    <%--<li><a style="color: #000000" href="#">测试1</a></li>
                                    <li><a style="color: #000000" href="#">测试1</a></li>--%>
                                </ul>
                            </div>
                        </div>
                    </nav>

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
                                                <a href="#">${posts.user.name}</a>
                                                <span class="left-space">${posts.date}</span>
                                                <c:if test="${posts.setTop==1}">
                                                    <span class="left-space">置顶</span>
                                                </c:if>

                                                <span class="eye-space glyphicon glyphicon-eye-open">${posts.viewNum}</span>
                                                <span class="comment-space glyphicon glyphicon-comment">${posts.commentNum}</span>
                                            </small>
                                            <%--<input name="id" value="" hidden>--%>
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
                    <script src="${pageContext.request.contextPath}/js-me/myFunction.js" ></script>
                    <script type="text/javascript">
                        $(function () {
                            //当前页
                            var pageNo = ${postsPages.pageNo};
                            //总页数
                            var pageCount = ${postsPages.pagesAllNo};
                            <%--var contextPath= "${pageContext.request.contextPath}";--%>
                            var pageSize=${postsPages.pageSize};
                            var preUrl="${pageContext.request.contextPath}/homePosts";

                            var html = generatePostsPagesHtml(pageNo,pageSize, pageCount,preUrl, null);

                            $("#postsPagesUl").html(html);
                        });
                    </script>
                    <%--<div>底部，可以设置分页信息</div>--%>
                </div>
            </div>
        </div>

        <!--第二列-->
        <div class="col-md-4 column">
            <!--面板1-->
            <div class="panel ">
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

                    </h3>
                </div>

                <c:if test="${empty user}">
                    <div class="panel-body">
                        <!--登录-->
                        <div class="panel panel-default padding-10per" id="loginMenu">
                            <jsp:include page="user/childPage/loginPage1.jsp" flush="true"></jsp:include>
                        </div>

                    </div>
                </c:if>
                <%--名片--%>
                <c:if test="${not empty user}">
                    <div class="panel-body" style="padding: 0px;height: 223.5px;">
                        <jsp:include page="user/childPage/userCard.jsp" flush="true"></jsp:include>
                    </div>
                </c:if>
                <%--<div class="panel-footer">

                </div>--%>
            </div>
<%--            <!--面板3 登录成功的信息面板-->
            <div class="panel panel-default">
                <div class="panel-heading" style="background-color: #888888;color: #fff;">
                    &nbsp;
                    <h3 class="panel-title">
                        Panel title
                        ${user.name}
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


</body>
</html>