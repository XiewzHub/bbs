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
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/js/bootstrapValidator.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/css/bootstrapValidator.css">
    <%--汉化一定要在后面--%>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap-datetimepicker.css">

    <style>
        .baseInfo-table {
            margin-top: 5px;

        }

        .baseInfo-table tr > th {
            padding: 10px 20px;
            height: 36px;
            border-bottom: 1px solid #f8f8f8;
            background: #f0f0f0;
            font-weight: normal;
        }

        .baseInfo-table tr td {
            padding: 10px 20px;
            height: 36px;
            border-bottom: 1px solid #f8f8f8;

        }
    </style>
</head>
<body>

<span style="width: 100%">账户设置</span>
<div style="background-color: #ddd;padding-bottom: 1px;width: 100%"></div>
<script type="text/javascript">
    $(function () {
        $('#userUpdateForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                sex: {
                    message: '性别验证失败',
                    validators: {
                        notEmpty: {
                            message: '请选择性别'
                        }
                    }
                },
                phonenum: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        stringLength: {
                            min:11,
                            max: 11,
                            message: '不能超过11位'
                        },
                        regexp:{
                            regexp: /^[0-9]+$/,
                            message: '手机号只能包含数字'
                        }
                    }
                },
                mail: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        regexp:{
                            regexp:/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/,
                            message:'邮箱格式错误'
                        }
                    }
                }

            }
        });
    });
</script>
<form id="userUpdateForm" class="form-horizontal" role="form" method="post"
      action="${pageContext.request.contextPath}/user/updateUser">
    <input name="id" value="${user.id}" hidden>
    <table class="baseInfo-table">
        <colgroup>
            <col width="20%">
            <col width="80%">
        </colgroup>
        <tbody>
        <tr>
            <th>
                <span>头像：</span>
            </th>
            <td>
                <%--直接存数据库--%>
                <img id="userHeadId" src="${pageContext.request.contextPath}${user.head}" width="80"
                     height="80"
                     style="border-radius: 50%">
                <button class="btn btn-default" onclick="clickUserHiddenfile()">更换</button>
                <input id="userHiddenfile" name="uploadUserHeadImg"  type="file" style="display: none;">
                <input id="hiddenUserHeadImg" name="head" value="${user.head}" hidden >
            </td>
        </tr>
        <tr>
            <th>
                <span>用户名：</span>
            </th>
            <td>
                <div>${user.name}</div>
                <input name="name" value="${user.name}" hidden>
            </td>
        </tr>
        <tr>
            <th>
                <span>手机号码：</span>
            </th>
            <td>
                <div class="row">
                    <input name="phonenum" class="form-control col-md-2" value="${user.phonenum}" style="width: 150px;"/>
                    <%--<button class="btn btn-default" value="修改">更换手机号码</button>--%>
                </div>
            </td>
        </tr>
        <tr>
            <th>
                <span>邮箱：</span>
            </th>
            <td>
                <div class="row">
                    <input name="mail" class="form-control col-md-2" value="${user.mail}" style="width: 150px;"/>
                    <%--<button class="btn btn-default" value="修改">更换手机号码</button>--%>
                </div>
            </td>
        </tr>

        </tbody>
    </table>
    <br>
    <br>
    <span>个人设置</span>
    <div style="background-color: #ddd;padding-bottom: 1px;width: 100%"></div>
    <table class="baseInfo-table">
        <colgroup>
            <col width="20%">
            <col width="80%">
        </colgroup>
        <tbody>
        <tr>
            <th>
                <span>性别：</span>
            </th>
            <td>
                <input type="radio" name="sex" value="男" <c:if test="${user.sex=='男'}">checked</c:if>/>男
                <input type="radio" name="sex" value="女" <c:if test="${user.sex=='女'}">checked</c:if>/>女
            </td>
        </tr>
        <tr>
            <th>
                <span>生日：</span>
            </th>
            <td>
                <div class="">
                    <div class="input-group date form_date">
                        <input name="birthday" class="form-control" size="16" type="text" value="${user.birthday}"
                               readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                </div>
            </td>
        </tr>

        </tbody>
    </table>

    <div>
        <button type="submit" class="btn btn-primary" style="float: right">提交</button>
    </div>
</form>
<script src="${pageContext.request.contextPath}/js-me/jquery.form.js"></script>
<%--<script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>--%>
<script src="${pageContext.request.contextPath}/js-me/MyUploadImg.js"></script>
<script type="text/javascript">
    $(function () {
        var url = "${pageContext.request.contextPath}/upload/uploadUserHead";
        uploadUserHeadImg(url);
    });
</script>
<script type="text/javascript">

    $('.form_date').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        weekStart: 0,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

</script>
</body>
</html>
