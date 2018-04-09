<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/css/bootstrap.min.css">
    <!--导入自己编写的css文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginSign.css">
    <script src="${pageContext.request.contextPath}/js-me/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/css/bootstrapValidator.css">
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/js/bootstrapValidator.min.js"></script>

</head>
<body>
<div id="bg">
    <img src="${pageContext.request.contextPath}/images/backgroundImg.jpg" alt="背景图">
</div>

<jsp:include page="../common/navbarPageManager.jsp" flush="true"></jsp:include>
<div class="container ">

    <div class="row clearfix">
        <div class="col-md-4 column">
        </div>
        <div class="col-md-4 column intervalUpDown">
            <!--登录与注册的切换导航栏-->
            <ul class="nav nav-tabs">
                <li id="loginLi" <c:if test="${info!='register'}"> class="active"</c:if>>
                    <a href="#loginMenu" id="loginTab" data-toggle="tab">登录</a>
                </li>

                <li id="registerLi" <c:if test="${info=='register'}"> class="active"</c:if>>
                    <a href="#registerMenu" id="registerTab" data-toggle="tab">注册</a>
                </li>
            </ul>
            <div id="myTabContent" class="tab-content">

                <!--登录-->
                <div class="tab-pane padding-10per fade <c:if test="${info!='register'}"> in active</c:if>" id="loginMenu">
                    <jsp:include page="childPage/loginPage.jsp" flush="true"></jsp:include>
                </div>


                <!--注册页面-->
                <div id="registerMenu" class="tab-pane padding-10per fade <c:if test="${info=='register'}"> in active</c:if>">
                    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user/register" method="post">
                        <div class="form-group">
                            <label for="inputUserName1" class="col-sm-3 control-label padding-LR-0per ">
                                <span class="font-set-pub">用户名</span></label>
                            <div class="col-sm-9">
                                <input name="name" class="form-control" id="inputUserName1" placeholder="用户名"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword4" class="col-sm-3 control-label padding-LR-0per font-set-pub"
                            >密码</label>
                            <div class="col-sm-9">
                                <input name="password" class="form-control" id="inputPassword4" type="password" placeholder="用户密码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword4Re" class="col-sm-3 control-label padding-LR-0per font-set-pub"
                            >确认密码</label>
                            <div class="col-sm-9">
                                <input name="rePassword" class="form-control" id="inputPassword4Re" type="password" placeholder="确认密码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputMail" class="col-sm-3 control-label padding-LR-0per ">
                                <span class="font-set-pub">邮箱</span></label>
                            <div class="col-sm-9">
                                <input name="mail" type="email" class="form-control" id="inputMail" placeholder="673275656@qq.com"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-1 col-sm-10">
                                <button name="" type="submit" class="btn btn-primary btn-block">注册</button>
                            </div>
                        </div>

                        <input id="registerInfo" value="${registerInfo}" hidden></input>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-4 column">
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
                name: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                mail: {
                    validators: {
                        notEmpty: {
                            message: '邮箱地址不能为空'
                        },
                        emailAddress: {
                            message: '邮箱输入格式不正确'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                },
                rePassword: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        identical: {
                            field: 'password',
                            message: '两次密码不一致'
                        }
                    }
                }
            }
        });
    });
</script>

<script type="text/javascript">
    $(function () {
        var registerInfo = $("#registerInfo").val();
        if(registerInfo!=''){
            alert(registerInfo);
        }
    })
</script>
</body>
</html>