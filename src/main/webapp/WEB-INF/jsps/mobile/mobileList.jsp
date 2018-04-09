<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>机型选择</title>
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
<jsp:include page="../common/navbarPageManager.jsp" flush="true"></jsp:include>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobilePage.css">
<div class="container">
    <%--<div class="col-md-3"></div>--%>
    <div class="col-md-10">
        <div id="" class="mobileType">
            <ul>
                <li><p id="" class="mobileHead" style="font-size: 32px">机型专区</p></li>
                <li>
                    <a href="${pageContext.request.contextPath}/mobileType/list"
                       <c:if test="${empty currentMobileBrand or currentMobileBrand==''}">class="active"</c:if>
                    >全部手机</a>
                </li>
                <c:forEach items="${mobileBrandList}" var="mobileBrand">
                    <li>
                        <a href="${pageContext.request.contextPath}/mobileType/list?mobileBrand=${mobileBrand}"
                           <c:if test="${currentMobileBrand==mobileBrand}">class="active"</c:if>
                        >${mobileBrand}专区</a>
                    </li>
                </c:forEach>

            </ul>
        </div>

        <div id="mobileContent" class="mobileContent">
            <table border="0">
                <c:forEach items="${mobilePagination.mobileTypeList}" var="mobileType" varStatus="status">
                <c:if test="${status.index%3==0}">
                <tr></c:if>
                    <td>
                        <a href="${pageContext.request.contextPath}/posts/1/5/list?mobiId=${mobileType.mobiId}">
                            <img src="${pageContext.request.contextPath}${mobileType.mobiImg}" width="160px" height="160px"/>
                            <p id="">${mobileType.mobiBrand}${mobileType.mobiSeries}${mobileType.mobiType}</p>

                            <p style='width: 300px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;'>
                                    ${mobileType.mobiDescription}</p>

                        </a>
                    </td>
                    </c:forEach>

            </table>
            <div style="background-color: snow;width: 120%;">
                <ul id="postsPagesUl" class="pagination" style="margin-left: 270px;">

                </ul>
            </div>
            <input id="mobileBrandHiddenParamData" value="${currentMobileBrand}" hidden>
            <script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>
            <script type="text/javascript">
                $(function () {
                    //当前页
                    var pageNo = ${mobilePagination.pageNo};
                    //总页数
                    var pageCount = ${mobilePagination.pagesAllNo};
                    <%--var contextPath= "${pageContext.request.contextPath}";--%>
                    var pageSize =${mobilePagination.pageSize};
                    var preUrl = "${pageContext.request.contextPath}/mobileType/";
                    <%--var currentMobileBrand = "${currentMobileBrand}";--%>
                    <%--alert(currentMobileBrand);--%>
                    var data = {
                        mobileBrand: "${currentMobileBrand}"
                    };
                    var html = generatePostsPagesHtml(pageNo, pageSize, pageCount, preUrl, null, data);

                    $("#postsPagesUl").html(html);
                });
            </script>
        </div>
    </div>
</div>
</body>
</html>