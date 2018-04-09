<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>测试页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginSign.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/css/bootstrapValidator.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap-datetimepicker.css">

    <script src="${pageContext.request.contextPath}/js-me/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/js/bootstrapValidator.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>


    <script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>


</head>

<body>
<form method="post" action="${pageContext.request.contextPath}/search">
    品牌<input name="mobiBrand" type="text">
    系列<input name="mobiSeries" type="text">
    型号<input name="mobiType" type="text">
    <div class="form-group" >
        <label for="dtp_input2" class="col-md-1 control-label" style="width:5%;padding-right: 0%">日期：</label>

        <div class="input-group date form_date col-md-3" >
            <input  name="mobiImg" class="form-control" size="16" type="text" value="2017-05-10" readonly>
            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
        <input type="hidden" id="dtp_input2" value="" /><br/>
    </div>
    <%--<input name="mobiImg" value="" type="hidden">--%>
    <button type="submit">提交</button>
</form>
<c:forEach items="${mobileTypeList}" var="mobileType">
    <%--${mobileType.}--%>
    >>手机<input name="mobiId" value="${mobileType}">
    <br>
</c:forEach>
<script type="text/javascript">

    $('.form_date').datetimepicker({
        language:  'zh-CN',
        format:'yyyy-mm-dd',
        weekStart: 0,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

</script>

</body>
</html>