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

<form class="form-horizontal" role="form" method="post">
    <div class="form-group">
        <label for="inputUserName" class="col-sm-2 control-label padding-LR-0per ">
            <span class="font-set-pub"><i class="glyphicon glyphicon-user"></i></span></label>
        <div class="col-sm-10">
            <input name="name" class="form-control" id="inputUserName" placeholder="用户名/手机号"/>
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label padding-LR-0per font-set-pub"
        ><i class="glyphicon glyphicon-lock"></i></label>
        <div class="col-sm-10">
            <input name="password" class="form-control" id="inputPassword3" type="password" placeholder="登录密码"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-0 col-sm-12">
            <button type="button" class="btn btn-primary btn-block" onclick="loginSubmit()" >登录
            </button>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-6">
        </div>

        <div class="col-sm-offset-1 col-sm-4 padding-top-7px">
            <a href="#">
                <label>忘记密码？</label>
            </a>
        </div>
    </div>
    <script type="text/javascript">

        function loginSubmit() {
            var user = {
                name: $("#inputUserName").val(),
                password: $("#inputPassword3").val()
            };

            $.ajax({
                url: "${pageContext.request.contextPath}/user/login",
                type: "POST",
                data: user,
                dataType: "json",
                success: function (data) {
                    var sucflag = data.success;

                    if (sucflag == '0') {
//                      登录失败 用户名 密码输入错误
                        alert(data.message);

                    }else{
//                      登陆成功跳转到主页
                        window.location.href = data.url;

                    }

                    /* alert(data.success);
                     alert(data.url);*/

                }
            });
            return false;
        }
    </script>

</form>

</body>
</html>
