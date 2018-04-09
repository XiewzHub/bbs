<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Smart帖子修改</title>
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
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/js/bootstrapValidator.min.js"></script>


    <script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>


</head>

<body>
<div id="bg">
    <img src="${pageContext.request.contextPath}/images/backgroundImg.jpg" alt="背景图">
</div>

<jsp:include page="../common/navbarPageManager.jsp" flush="true"></jsp:include>

<!--编辑框  帖子发表-->
<form id="forumForm" method="post" role="form" class="form-horizontal"
      action="${pageContext.request.contextPath}/posts/modify">
    <input name="date" value="${posts.date}"/>
    <%--输入标题--%>
    <div class="row">
        <div class="form-group input-group" style="margin-left: 16%;width: 40%;float: left">
            <label for="title" class="input-group-addon control-label">
                <span class="font-set-pub">标题</span>
            </label>
            <%--隐藏id--%>
            <input hidden name="setTop" value="${posts.setTop}" />
            <input hidden type="text" name="id" value="${posts.id}">
            <input hidden type="text" name="commentNum" value="${posts.commentNum}">
            <input hidden type="text" name="viewNum" value="${posts.viewNum}">
            <input name="title" type="text" size="100px" class="form-control" id="title" value="${posts.title}">
        </div>
        <div class="form-group col-md-1" style="margin-left: 2px">
            <select name="mobiBrand" class="form-control" style="padding: 0px;">
                <option value="">---手机品牌---</option>
                <c:forEach items="${mobileBrandList}" var="mobileBrand">

                <option <c:if test="${mobileType.mobiBrand==mobileBrand}">selected</c:if>
                        value="${mobileBrand}">---${mobileBrand}</option>
                </c:forEach>
            </select>
        </div>
        <div id="seriesDiv" class="form-group col-md-1" style="margin-left: 2px">
            <select name="mobiSeries" class="form-control" style="padding: 0px;">
                <option value="">---手机系列---</option>
                <option selected value="${mobileType.mobiSeries}">---${mobileType.mobiSeries}</option>
            </select>
        </div>
        <div id="typeDiv" class="form-group col-md-1" style="margin-left: 2px">
            <select name="mobiType" class="form-control" style="padding: 0px;">
                <option value="">---手机型号---</option>
                <option selected value="${mobileType.mobiType}">---${mobileType.mobiType}</option>
            </select>
        </div>
        <input id="hiddenMobiId" name="mobileId" value="${mobileType.mobiId}" hidden>
    </div>

    <%--输入内容--%>
    <div id="test-editormd">
        <textarea class="editormd-markdown-textarea" name="srcText">${posts.srcText}</textarea>
        <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
        <textarea class="editormd-html-textarea" name="text"></textarea>
    </div>

    <%--提交按钮--%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-1">
            <button id="submitModifyBtn" type="submit" name="" class="btn btn-primary btn-block">确认修改</button>
        </div>
    </div>

</form>

<script src="${pageContext.request.contextPath}/js-me/postsEditPage.js"></script>
<script type="text/javascript">
    var testEditor;
    $(function () {
        editormdInit();
        selectMobileType();
        addPageValidator();
    });

    function editormdInit() {
        testEditor = editormd("test-editormd", {
            width: "71%",
            height: 700,
            syncScrolling: "single",
            autoHeight: false,
            tex  : true, // 公式
            flowChart:true,//流程图
            sequenceDiagram : true,//时序图
            path: "${pageContext.request.contextPath}/editormd/lib/",
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL: "${pageContext.request.contextPath}/upload/${posts.id}/uploadimage",
            saveHTMLToTextarea: true,  //保存html
            tocStartLevel : 2 // 导航
        });
        testEditor.config({
            tocm : true,
            tocContainer : "",
            tocDropdown   : false
        });
    }

</script>

</body>
</html>