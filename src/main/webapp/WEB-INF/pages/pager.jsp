<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose><%--如果没有记录页码页数应该显示为0/0--%>
    <c:when test="${!(pager.totalCounts eq 0)}">
        共<span class="totalCounts">${pager.totalCounts}</span>条记录
        &nbsp;&nbsp;&nbsp;&nbsp;
        <span class="pageNo">${pager.pageNo}</span>/<span class="pageCounts">${pager.pageCounts}</span>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <%--JSTL中if语句--%>
        <c:if test="${pager.pageNo gt 1}"><%--gt指大于号--%>
            <a class="firstPage">首页</a>
            <a class="previousPage"
               onclick="goListForm(${pager.pageNo-1})">上一页</a>
            &nbsp;&nbsp;
        </c:if>
        <c:if test="${pager.pageNo lt pager.pageCounts}"><%--lt小于号--%>
            <a class="nextPage"
               onclick="goListForm(${pager.pageNo+1})">下一页</a>
            <a class="lastPage">尾页</a>
        </c:if>
        <c:if test="${pager.pageCounts gt 1}">
            &nbsp;&nbsp;跳转到<input class="goPage" type="text" size="3">页
            <input type="button" class="go" value="跳转">
        </c:if>
    </c:when>
</c:choose>

<script>
    $(function () {
        // var categoryId = $("#categoryId").val().trim();

        $(".firstPage").click(function () {
            goListForm(1);
        });

        $(".lastPage").click(function () {
            goListForm(${pager.pageCounts});
        });

        $(".go").click(function () {
            var pageNo = $(".goPage").val().trim();//获取类名为GoPage的值
            if (pageNo != "") {
                var reg = /^[1-9][0-9]*$/;
                //判断输入的数字
                if (!reg.test(pageNo)) {
                    alert("请输入正确的数字格式！");
                    $(".goPage").focus();
                } else if (parseInt(pageNo) > parseInt($(".pageCounts").text())) {
                    alert("超出最大页码！");
                    $(".goPage").focus();
                } else {
                    // location.href = "list?pageNo=" + parseInt($(".goPage").val()) + "&pageSize=" + parseInt($("#pageSize").val());
                    goListForm(parseInt(pageNo));
                    //清空文本域/框
                    $(".goPage").val("");
                }
            }
        });
    });

    //指定针对某个表单跳转
    function goListForm(pageNo) {
        $("#pageNo").val(pageNo);
        // document.forms[0].submit();//表单提交第一个
        $("form:first").submit();//触发页面上第一个表达提交事件（与上面等效）
    }

</script>
