<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增标准</title>
</head>
<body>
${result}
<form action="${pageContext.request.contextPath}/downloadFile">
    附件下载到：<input type="text" name="path2" value="${path2}">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="hidden" value="${id}" name="id">
    <input type="hidden" name="path1" value="${path1}">
    <input type="submit" value="下载">
    <input type="button" id="back" value="返回">
</form>

<script>
    window.onload = function () {
        var oBtn = document.getElementById("back");
        oBtn.onclick = function () {
            location.href = "/standard/list";
        }
    }
</script>
</body>
</html>
