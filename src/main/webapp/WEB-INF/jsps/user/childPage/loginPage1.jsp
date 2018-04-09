<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/10
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

</head>
<body>
<div id="">
    <c:if test="${empty user}">
        <form class="form-horizontal" role="form" method="post">
            <!--用户名-->
            <div class="form-group">
                <label for="inputUserName" class="col-sm-2 control-label padding-LR-0per ">
                    <span class="font-set-pub"><i class="glyphicon glyphicon-user"></i></span></label>
                <div class="col-sm-10">
                    <input name="userName" type="text" class="form-control" id="inputUserName" placeholder="用户名/手机号"/>
                </div>
            </div>
            <!--密码-->
            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 control-label padding-LR-0per font-set-pub"
                ><i class="glyphicon glyphicon-lock"></i></label>
                <div class="col-sm-10">
                    <input name="password" class="form-control" id="inputPassword" type="password" placeholder="登录密码"/>
                </div>
            </div>
            <!--记住密码-->
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-5">
                    <div class="checkbox">
                        <label><input type="checkbox"/>记住我</label>
                    </div>

                </div>

                <div class="col-sm-offset-1 col-sm-5 padding-top-7px">
                    <a href="#">
                        <label>忘记密码？</label>
                    </a>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-0 col-sm-12">
                    <button type="button" class="btn btn-primary btn-block" onclick="loginSubmit1()">登录</button>
                    <a href="${pageContext.request.contextPath}/user/register" type="button" class="btn btn-primary btn-block">注册</a>
                </div>
            </div>
        </form>
    </c:if>
<%--    <c:if test="${not empty user}">
        <jsp:include page="userCard.jsp" flush="true"></jsp:include>
    </c:if>--%>
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
<script type="text/javascript">
    function loginSubmit1() {
        var user = {
            name: $("#inputUserName").val(),
            password: $("#inputPassword").val()
        };

        $.ajax({
            url: "${pageContext.request.contextPath}/user/login",
            type: "POST",
            data: user,
            dataType: "json",
            success: function (data) {
                var sucflag = data.success;

                if (sucflag == '0') {
//                  用户名或密码错误登录失败
                    alert(data.message);

                } else {
//登陆成功跳转到主页
//                    alert(data.message);
                    window.location.href = window.location.href;

                }

                /* alert(data.success);
                 alert(data.url);*/

            }
        });
        return false;
    }
</script>

</body>
</html>
