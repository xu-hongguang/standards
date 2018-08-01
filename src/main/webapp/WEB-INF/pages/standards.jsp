<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>标准信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/index.css">
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
</head>
<body>
<%--后台响应消息--%>
${result}
<div id="body">
    <h2>标准信息列表</h2>
    <form action="${pageContext.request.contextPath}/list" method="get">
        <table>
            <thead>
            <td class="thead" colspan="7">
                <input type="text" value="${likeName}" name="std_num">
                <input type="submit" value="提交搜索">&nbsp;&nbsp;
                <input type="button" class="add" value="新增标准">
                <input type="button" onclick="deleteStandard()" value="删除标准">
                <input type="hidden" id="pageNo" value="${pager.pageNo}" name="pageNo">
            </td>
            <tr>
                <%--全选/全不选按钮--%>
                <td><input type="checkbox" id="checkAllOrNo"></td>
                <td>标准号</td>
                <td>中文名称</td>
                <td>版本</td>
                <td>发布日期</td>
                <td>实施日期</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${!empty pager.rows}">
                    <c:forEach items="${pager.rows}" var="standard">
                        <tr class="tbody">
                            <td>
                                <%--复选框--%>
                                <input type="checkbox" name="id" value="${standard.id}">
                                <%--<div class="id">${standard.id}</div>--%>
                            </td>
                            <td>${standard.std_num}</td>
                            <td>${standard.zhname}</td>
                            <td>${standard.version}</td>
                            <td><fmt:formatDate value="${standard.release_date}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${standard.impl_date}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <a href="/standard/download?id=${standard.id}">下载</a>
                                <%--<a href="/standard/download/${standard.id}">下载</a>--%>
                                <a onclick="update(${standard.id})">修改</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="7" style="height: 50px;size: 30px">没有您查询的图书</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            <td class="tbody" colspan="7">
                <a onclick="goListForm(1)">首页</a>|
                <c:if test="${pager.pageNo gt 1}">
                    <a onclick="goListForm(${pager.pageNo-1})">上一页</a>|
                </c:if>
                <c:if test="${pager.pageNo lt pager.pageCounts}">
                    <a onclick="goListForm(${pager.pageNo+1})">下一页</a>|
                </c:if>
                <a onclick="goListForm(${pager.pageCounts})">尾页</a>
                <c:if test="${pager.pageCounts gt 1}">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <span>第${pager.pageNo}页</span>/<span>共${pager.pageCounts}页</span>
                </c:if>
            </td>
            </tbody>
        </table>
    </form>
</div>

<script src="${pageContext.request.contextPath}/statics/js/index.js"></script>
</body>
</html>
