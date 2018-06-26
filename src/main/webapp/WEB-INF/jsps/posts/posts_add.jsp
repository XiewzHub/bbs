<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <%--<meta charset="UTF-8">--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>这里可以发布你的Smart帖子</title>
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
    <script src="${pageContext.request.contextPath}/editormd/lib/marked.min.js"></script>
    <script src="${pageContext.request.contextPath}/editormd/lib/flowchart.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7/dist/js/bootstrapValidator.min.js"></script>


    <script src="${pageContext.request.contextPath}/js-me/myFunction.js"></script>

</head>

<body>
<div id="bg">
    <img src="${pageContext.request.contextPath}/images/backgroundImg.jpg" alt="背景图">
</div>

<jsp:include page="../common/navbarPageManager.jsp" flush="true"></jsp:include>

<!--编辑框-->
<form id="forumForm" method="post" role="form" class="form-horizontal"
      action="${pageContext.request.contextPath}/posts/add">

    <%--输入标题--%>
    <div class="row ">
        <div class="form-group input-group" style="margin-left: 16%;width: 40%;float: left">
            <label for="title" class="input-group-addon control-label">
                <span class="font-set-pub">标题</span>
            </label>

            <input name="title" type="text" size="100px" class="form-control" id="title">
        </div>
        <div class="form-group col-md-1" style="margin-left: 2px">
            <select name="mobiBrand" class="form-control" style="padding: 0px;">
                <option value="">---手机品牌---</option>
                <c:forEach items="${mobileBrandList}" var="mobileBrand">
                    <option value="${mobileBrand}">---${mobileBrand}</option>
                </c:forEach>
            </select>
        </div>
        <div id="seriesDiv" class="form-group col-md-1" style="margin-left: 2px">

        </div>
        <div id="typeDiv" class="form-group col-md-1" style="margin-left: 2px">

        </div>
        <input id="mobiId" name="mobiId" value="" hidden>
    </div>
    <%--输入内容--%>
    <div id="test-editormd">
        <textarea class="editormd-markdown-textarea"
                  name="srcText" <%--name="test-editormd-markdown-doc"--%>></textarea>
        <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
        <textarea class="editormd-html-textarea" name="text"></textarea>
    </div>
    <%--提交按钮--%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-1">
            <button id="submitAddBtn" type="submit" name="" class="btn btn-primary btn-block">确认发布</button>
        </div>
    </div>
</form>
<script type="text/javascript">
    var testEditor;
    $(function () {
        //初始化编辑界面
        editormdInit();
        //选择手机型号
        selectMobileType();
        //页面表单验证
        addPageValidator();
    });

    function selectMobileType() {
        $("select[name = 'mobiBrand']").unbind('change');
        $("select[name = 'mobiBrand']").bind('change', function (index, elem) {
            var mobiBrand = $(this).val();
            if(mobiBrand==''){
                $("#seriesDiv").html('');
                $("#typeDiv").html('');
            }
            var requestData = {
                mobiBrand: mobiBrand
            };
            //根据手机品牌查询手机系列
            $.ajax({
                url: "${pageContext.request.contextPath}/mobileType/findMobileSeries",
                data: requestData,
                dataType: "json",
                success: function (data) {
                    // alert(data);
                    if (data.result == '成功') {
                        $("#seriesDiv").html(data.html);
                        $("#typeDiv").html('');
                        $("#submitAddBtn").attr('disabled','disabled');
                        selectMobiSeries();
                        addPageValidator();
                    } else {//失败后处理

                    }
                },
                error:function (data) {
                    // alert(data);
                }
            });
        });
    }
    //生成手机系列并且成功后控制点击手机型号事件
    function selectMobiSeries() {
        $("select[name = 'mobiSeries']").unbind('change');
        $("select[name = 'mobiSeries']").bind('change', function (index, elem) {
            var mobiSeries = $(this).val();
            var mobiBrand = $("select[name = 'mobiBrand']").val();
//            alert(mobiBrand);
            if(mobiSeries==''){
                $("#typeDiv").html('');
            }
            var requestData = {
                mobiBrand: mobiBrand,
                mobiSeries: mobiSeries
            };
            $.ajax({
                url: "${pageContext.request.contextPath}/mobileType/findMobileType",
                data: requestData,
                dataType: "json",
                success: function (data) {
                    if (data.result == '成功') {
                        $("#typeDiv").html(data.html);
                        $("#submitAddBtn").attr('disabled','disabled');
                        selectMobiTypeToFindId();
                        addPageValidator();
                    }
                }
            });
        });
    }

    function selectMobiTypeToFindId() {
        $("select[name='mobiType']").unbind('change');
        $("select[name='mobiType']").bind('change', function (index, elem) {
            var mobiType = $(this).val();
            var mobiBrand = $("select[name = 'mobiBrand']").val();
            var mobiSeries = $("select[name = 'mobiSeries']").val();
            var requestData = {
                mobiBrand: mobiBrand,
                mobiSeries: mobiSeries,
                mobiType: mobiType
            };
            $.ajax({
                url: "${pageContext.request.contextPath}/mobileType/findMobileTypeId",
                data: requestData,
                dataType: 'json',
                success: function (data) {
                    if (data.result == '成功') {
                        $("#mobiId").val(data.mobiId);
                    } else {
                        $("#mobiId").val("");
                    }
                    addPageValidator();
                },
                error: function (data) {
                    alert(data.status);
                    alert(data.readyState);
                }
            });
        });
    }

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
            imageUploadURL: "${pageContext.request.contextPath}/upload/uploadimage",
            saveHTMLToTextarea: true,  //保存html
            tocStartLevel : 2 // 导航
        });


    }

    function addPageValidator() {
        $('#forumForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                title: {
                    message: '标题验证失败',
                    validators: {
                        notEmpty: {
                            message: '标题不能为空'
                        },
                        stringLength: {
                            max: 80,
                            message: '标题必须少于80个字'
                        }
                    }
                },
                mobiBrand: {
                    message: '手机品牌验证失败',
                    validators: {
                        notEmpty: {
                            message: '请选择手机品牌'
                        }
                    }
                },
                mobiSeries: {
                    message: '手机品牌验证失败',
                    validators: {
                        notEmpty: {
                            message: '请选择手机系列'
                        }

                    }
                },
                mobiType: {
                    message: '手机型号验证失败',
                    validators: {
                        notEmpty: {
                            message: '请选择手机型号'
                        }
                    }
                },
                mobiId:{
                    message:'手机id认证失败',
                    validators:{
                        notEmpty:{
                            message:'请正确选择手机型号'
                        }
                    }
                }

            }
        });
    }
</script>

</body>
</html>