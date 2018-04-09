<!DOCTYPE html>
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
<html lang="en">
<html>
<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userCard.css">
</head>
<body>
<%--用户资料名片--%>
<div class="user-info">
    <a href="${pageContext.request.contextPath}/user/manager"><img src="${pageContext.request.contextPath}${user.head}" width="100" height="100"
                                                                   style="float: left;border-radius: 50%;"></a>
    <p class="">${user.name}</p>
    <c:if test="${user.isAdmin=='Y'}">
        <p style="margin-top: 61px;margin-left: 60px;color: seashell;font-size: large;">管理员</p>
    </c:if>
    <c:if test="${user.isAdmin=='N'}">
        <p style="margin-top: 61px;margin-left: 45px;color: seashell;font-size: large;">本站会员</p>
    </c:if>
</div>
<ul id="" class="user-op" style="">
    <li class="fr"><a href="${pageContext.request.contextPath}/user/myPostsManager"><span id="PostCount">${user.postsNum}</span>发帖</a>
    <li><a href="${pageContext.request.contextPath}/user/manager"><span id="FansMyCount"><i class="glyphicon glyphicon-home"></i></span>个人中心</a></li>
    <li><a href="${pageContext.request.contextPath}/user/myPostsManager"><span id="FollowsMyCount"><i class="glyphicon glyphicon-th"></i></span>帖子管理</a></li>
    </li>
</ul>

</body>
</html>
