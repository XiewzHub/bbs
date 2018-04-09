<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/10
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

</head>
<body>

<!--导航栏-->
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">手机论坛</a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="">
                    <a href="${pageContext.request.contextPath}/mobileType/list">板块</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/posts/list">全部帖子</a>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        操作<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/posts/add">我要发帖</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/myPostsManager">我的帖子</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/manager">个人中心</a>
                        </li>

                        <li class="divider">
                        </li>
                        <c:if test="${user.isAdmin == 'Y'}">
                            <li>
                                <a href="${pageContext.request.contextPath}/user/allPostsManager">帖子管理</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/user/mobileTypeManager">手机管理</a>
                            </li>
                        </c:if>
                    </ul>
                </li>
            </ul>


            <ul class="nav navbar-nav navbar-right">

                <!--搜索框-->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="glyphicon glyphicon-search"></i></a>
                    <ul class="dropdown-menu" style="padding:12px;">
                        <form class="form-inline" method="post" action="${pageContext.request.contextPath}/posts/search">
                            <button type="submit" class="btn btn-default pull-right"><i
                                    class="glyphicon glyphicon-search"></i></button>
                            <input id="searchHinddenParamDada" name="search"  class="form-control pull-left" placeholder="帖子标题/内容" type="text">
                        </form>
                    </ul>
                </li>

            </ul>

        </div>

    </div>
</nav>
<%--导航栏结束--%>
<br><br><br><%--换行--%>
<script type="text/javascript">
    $(function () {
//        userLogout();
    });

</script>
</body>
</html>
