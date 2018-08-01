<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 16033
  Date: 2018/6/2
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改标准</title>
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
    <style>
        body {
            margin: 0 auto;
        }

        h2 {
            width: 200px;
            margin: 5px auto;
        }

        table {
            border-collapse: collapse;
            width: 500px;
            margin: 5px auto;
            height: 220px;
        }

        table td {
            border: 1px solid #cccccc;
        }

        .t1 {
            text-align: right;
            width: 150px;
            font-size: 15px;
        }

        .t2 {
            text-align: left;
            width: 200px;
        }

        .t3 {
            text-align: center;
        }

        input {
            height: 25px;
            margin-left: 5px;
        }

        span {
            color: red;
        }
    </style>
</head>
<body>
<h2>修改标准信息</h2>
<sf:form action="${pageContext.request.contextPath}/updateStandard" modelAttribute="standard"
         enctype="multipart/form-data"
         method="POST">
    <sf:hidden path="id" value="${standard.id}"/>
    <table>
        <tr>
            <td class="t1"><span>*</span>标准号：</td>
            <td class="t2">
                <sf:input path="std_num" value="${standard.std_num}" readonly="true"/>
                <sf:errors path="std_num" cssStyle="color: red;font-size: 12px"/>
            </td>
        </tr>
        <tr>
            <td class="t1"><span>*</span>中文名称：</td>
            <td class="t2">
                <sf:input path="zhname" value="${standard.zhname}"/>
                <sf:errors path="zhname" cssStyle="color: red;font-size: 12px"/>
            </td>
        </tr>
        <tr>
            <td class="t1"><span>*</span>版本：</td>
            <td class="t2">
                <sf:input path="version" value="${standard.version}"/>
                <sf:errors path="version" cssStyle="color: red;font-size: 12px"/>
            </td>
        </tr>
        <tr>
            <td class="t1"><span>*</span>关键字/词：</td>
            <td class="t2">
                <sf:input path="keys" value="${standard.keys}"/>
                <sf:errors path="keys" cssStyle="color: red;font-size: 12px"/>
            </td>
        </tr>
        <tr>
            <td class="t1">实施日期(yyyy-MM-dd)：</td>
            <td class="t2">
                <input type="text" name="impl_date" id="date" value="${implDate}"/>
            </td>
        </tr>
        <tr>
            <td class="t1">附件：</td>
            <td class="t2">
                <input type="file" class="file" name="multipartFile"/>
                <span class="error"></span>
            </td>
        </tr>
        <td class="t3" colspan="2">
            <input type="submit" class="submit" value="保存">
            <input type="button" class="back" value="取消">
        </td>
    </table>
</sf:form>

<script>
    $(function () {
        $(".back").click(function () {
            location.href = "/standard/list";
        });

        //判断日期格式
        $("#date").bind("blur", function () {
            var releaseDate = $("#date").val().trim();
            var reg = /^[1-9]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
            // var regExp = new RegExp(reg);
            if (!reg.test(releaseDate)) {
                alert("日期格式不正确，正确格式为：yyyy-MM-dd");
                $("#date").attr("result", "fail");
                $("#date").focus();
            } else {
                $("#date").attr("result", "succ");
            }
        });
    })
</script>
</body>
</html>
