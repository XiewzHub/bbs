/**
 * Created by Administrator on 2017/5/17.
 */

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
            mobileId: {
                message: '手机id认证失败',
                validators: {
                    notEmpty: {
                        message: '请正确选择手机型号'
                    }
                }
            }

        }
    });
}

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
            url: "/smart_froum/mobileType/findMobileSeries",
            data: requestData,
            dataType: "json",
            success: function (data) {
                if (data.result == '成功') {
                    // $("#mobileId").val('');
                    $("#seriesDiv").html(data.html);
                    $("#typeDiv").html('');
                    $("#submitModifyBtn").attr('disabled','disabled');
                    selectMobiSeries();
                    addPageValidator();
                } else {//失败后处理

                }
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
            url: "/smart_froum/mobileType/findMobileType",
            data: requestData,
            dataType: "json",
            success: function (data) {
                if (data.result == '成功') {
                    $("#typeDiv").html(data.html);
                    $("#submitModifyBtn").attr('disabled','disabled');
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
            url: "/smart_froum/mobileType/findMobileTypeId",
            data: requestData,
            dataType: 'json',
            success: function (data) {
                if (data.result == '成功') {
                     $("#hiddenMobiId").val(data.mobiId);
                    // alert($("#hiddenMobiId").val());
                } else {
                    $("#hiddenMobiId").val("");
                    // alert($("#hiddenMobiId").val());

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

