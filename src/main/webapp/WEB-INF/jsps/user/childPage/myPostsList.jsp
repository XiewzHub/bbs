<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
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
<html>
<%--<head>
    <title>Title</title>
</head>--%>
<body>

<table class="table table-hover">
    <thead>
    <tr>
        <th>
            作者
        </th>
        <th>
            标题
        </th>
        <th>
            发布时间
        </th>
        <th>
            操作
        </th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${empty postsList}">
        哎，找不到~~~~~
    </c:if>
    <c:forEach items="${postsList}" var="posts" varStatus="status">

        <tr <c:if test="${status.index%2==0}">class="success"</c:if>>
            <td>
                ${posts.author}
            </td>
            <td style="width: 60%">
                <a href="${pageContext.request.contextPath}/posts/${posts.id}/view">${posts.title}</a>
            </td>
            <td>
                ${posts.date}
            </td>
            <td>
                <a name="deleteMyPosts" class="btn btn-danger" style="padding: 5px" href="javascript:void(0);" postsId="${posts.id}">删除</a>
                <a name="editMyPosts" class="btn btn-primary" style="padding: 5px" href="${pageContext.request.contextPath}/posts/${posts.id}/edit" postsId="${posts.id}">编辑</a>
            </td>
        </tr>
    </c:forEach>

    <tr>
        <td colspan="4">
            <ul id="myPostsPagesUl" class="pagination">

            </ul>
            <script src="${pageContext.request.contextPath}/js-me/myFunction.js" ></script>
            <script type="text/javascript">
                function deleteMyPosts() {
                    $("a[name='deleteMyPosts']").each(function (index, elem) {
                        $(elem).unbind("click");
                        $(elem).bind("click", function () {
                            var postsId = $(this).attr("postsId");
//                            alert(postsId);
                            var url = "${pageContext.request.contextPath}/posts/" + postsId + "/ajaxDelete";

                            $.ajax({
                                type: 'POST',
                                url: url,
                                dataType: 'json',
                                data: {"info": 1},
                                success: function (data) {
//                                    alert(data.result);
                                    if (data.result == 1) {
                                        var newUrl = window.location.href;
                                        if(newUrl.search("myPostsManager")>=0) {
                                            newUrl = newUrl.replace("allPostsManager", "myPostsManager");
                                        }else if(newUrl.search("manager")>=0){
                                            newUrl = newUrl.replace("manager", "myPostsManager");
                                        }else if(newUrl.search("mobileTypeManager")>=0){
                                            newUrl = newUrl.replace("mobileTypeManager","myPostsManager");
                                        }
                                        window.location.href = newUrl;
                                    } else {
                                        alert("删除失败，请刷新页面重试。")
                                    }
                                },
                                error: function (data) {
                                    alert("失败,请联系管理员");
//                                    alert(data);
                                }
                            });
                        });
                    });
                }
                $(function () {
                    //当前页
                    var pageNo = ${postsPages.pageNo};
                    //总页数
                    var pageCount = ${postsPages.pagesAllNo};

                    <%--var contextPath= "${pageContext.request.contextPath}";--%>
                    var pageSize=${postsPages.pageSize};
                    var preUrl="${pageContext.request.contextPath}/user/myPostsManager";

                    var data = {
                        title:$("#titleParamDataMy").val()
                    };
                    var html = generatePostsPagesHtml(pageNo, pageSize, pageCount, preUrl, null,data);

                    $("#myPostsPagesUl").html(html);

                    deleteMyPosts();
                });
            </script>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
