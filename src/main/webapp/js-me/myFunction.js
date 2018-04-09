/**
 * Created by Administrator on 2017/4/21.
 */
/**
 * 用于拼接查询条件的
 * @type {{author: (*), title: (*), mobileBrand: (*), mobiId: (*)}}
 */
var data = {
    author: $("#authorParamData").val(),
    title: $("#titleParamData").val(),
    mobileBrand: $("#mobileBrandHiddenParamData").val(),
    mobiId: $("#mobileIdHiddenParamData").val(),
    search: $("#searchHinddenParamDada").val()
};

/**
 * 创建分页导航的html
 *
 * @param pageNo 当前页        必需
 * @param pageSize 每页显示数  必需
 * @param pageCount 总页数     必需
 * @param preUrl url的前面部分 必须要传入
 * @param displayNo 展示可以点击的页数 如果不传就默认5页
 * @param data 查询参数
 * @param suffixUrl 查询url后缀
 * @returns {string} 返回分页
 */
function generatePostsPagesHtml(pageNo, pageSize, pageCount, preUrl, displayNo, data, suffixUrl) {
    if (suffixUrl == null || suffixUrl == '') {
        suffixUrl = "list";
    }

    //url设置
    if ((preUrl.lastIndexOf('/') + 1) == preUrl.length) {
        preUrl = preUrl.substring(0, preUrl.lastIndexOf('/'));
    }

    //正常情况生成5页
    if (displayNo == null) {
        displayNo = 5;
    }
    //第一页不生成上一页
    var html = "";
    if (pageNo != 1) {
        html += "<li><a href='" + preUrl + "/" + (pageNo - 1) + "/" + pageSize + "/" + suffixUrl + "?";
        html += buildParam(data);

        html += "'>上一页</a></li>";
    }

    //总页数大于显示的页数
    if (pageCount > displayNo) {
        //得到中间的页数
        var midPageNo;
        //开始的页数
        var startPageNo;
        //结束的页数
        var endPageNo;
        if (displayNo % 2 == 0) {
            //如果显示页数是双数，就取半数作为中间，如显示4页的中间也是第2页
            midPageNo = displayNo / 2;

        } else {
            //单数就取最中间的数字，共5页就取第3页
            midPageNo = Math.ceil(displayNo / 2);
        }

        //当前页大于等于中间页，就可以用公式计算出开始页
        startPageNo = pageNo - midPageNo + 1;
        if (startPageNo <= 1) {
            startPageNo = 1;
        }
        //结束页=开始页+展示页
        endPageNo = startPageNo + displayNo;
        //结束页大于等于总页数
        if (endPageNo >= pageCount) {
            endPageNo = pageCount;
        }
        //如果结束页-开始页<展示页
        if ((endPageNo - startPageNo) < displayNo) {
            //开始页变为
            startPageNo = endPageNo - displayNo + 1;
        }

        for (var i = 0; i < displayNo; i++) {
            //如果是当前页，则标记
            if ((i + startPageNo) == pageNo) {
                html += "<li class='active'><a href='javascript:void (0);'>" + pageNo + "</a></li>";
            } else {
                //如果
                html += "<li><a href='" + preUrl + "/" + (startPageNo + i) + "/" + pageSize + "/" + suffixUrl + "?"
                html += buildParam(data);
                html +="'>" + (startPageNo + i) + "</a></li>";
            }
        }

    } else {
        //总页数小于等于显示页数
        for (var i = 1; i <= pageCount; i++) {
            if (i == pageNo) {
                html += "<li class='active'><a href='javascript:void (0);'>" + pageNo + "</a></li>";
            } else {
                html += "<li><a href='" + preUrl + "/" + i + "/" + pageSize + "/" + suffixUrl + "?"
                html += buildParam(data);
                html += "'>" + i + "</a></li>";
            }
        }
    }

    //最后一页不生成下一页
    //只有当前页码不等于总页数而且总页数不等于0才生成下一页
    if (pageNo != pageCount && pageCount != 0) {
        html += "<li><a href='" + preUrl + "/" + (pageNo + 1) + "/" + pageSize + "/" + suffixUrl + "?"
        html += buildParam(data);
        html += "'>下一页</a></li>";
    }

    if (pageCount != 0) {
        html += "<li> <input  class='inputPageNo form-control' style='color: black;margin-right:6px;margin-left: 12px;float: left;padding: 6px 12px;width: 12%;'type='text'> </li>";
        html += "<li><a onclick='skipPages(" + pageSize + "," + pageCount + ",\"" + preUrl + "\"" + ")' href='javascript:void(0);'>跳转</a></li>";
    }
    html += "<li><span>共" + pageCount + "页</span></li>";
    return html;
}


function buildParam(data) {
    var html = "";
    if (data != null ) {
        if (data.author != null && data.author != '') {
            html += "author=" + data.author + "&&";
        }
        if (data.title != null) {
            html += "title=" + data.title + "&&";
        }
        if (data.mobileBrand != null && data.mobileBrand != '') {
            html += "mobileBrand=" + data.mobileBrand + "&&";
        }
        if (data.mobiId != null && data.mobiId != '') {
            html += "mobiId=" + data.mobiId + "&&";
        }
        if (data.search != null && data.search != '') {
            html += "search=" + data.search + "&&";
        }
    }
    return html;
}


/**
 * 点击跳转执行的方法
 * 此方法运行必须在页面上放置隐藏域，用于加载参数data，否则无法正确跳转到指定页面
 * @param pageSize  每页展示数
 * @param pageCount 总页数
 * @param preUrl    url的前面部分
 */
function skipPages(pageSize, pageCount, preUrl) {
    // alert($("#inputPageNo").val());
    var pageNo;
    $(".inputPageNo").each(function () {
        pageNo = $(this).val();
        // alert(pageNo);
        if (pageNo != "") {
            if (pageNo == "" || !isInteger(pageNo)) {
                alert("请输入正确页码");
                $(".inputPageNo").val("");
            } else {
                if (pageNo <= 0) {
                    pageNo = 1;
                } else if (pageNo > pageCount) {
                    pageNo = pageCount;
                }
                window.location.href = preUrl + "/" + pageNo + "/" + pageSize + "/list?" + buildParam(data);
            }
        }
    });

}

/**
 * 判断参数是不是整数
 * @param obj
 * @returns {boolean}
 */
function isInteger(obj) {
    return obj % 1 === 0;
}

