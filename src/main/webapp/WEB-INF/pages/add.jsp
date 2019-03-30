<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增标准</title>
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

        .file{
            width: 148px;
        }
        .error{
            color: red;
            font-size: 12px;
        }

        .implDate{
            width: 147px;
        }
    </style>
</head>
<body>
<%--标准号重复/附件为空 提醒--%>
${result}
<h2>增加标准信息</h2>
<sf:form action="${pageContext.request.contextPath}/addStandard" modelAttribute="standard" enctype="multipart/form-data"
         method="POST">
    <table>
        <tr>
            <td class="t1"><span>*</span>标准号：</td>
            <td class="t2">
                <sf:input path="std_num" cssClass="stdNum" />
                <sf:errors path="std_num" cssStyle="color: red;font-size: 12px"/>
            </td>
        </tr>
        <tr>
            <td class="t1"><span>*</span>中文名称：</td>
            <td class="t2">
                <sf:input path="zhname"/>
                <sf:errors path="zhname" cssStyle="color: red;font-size: 12px"/>
            </td>
        </tr>
        <tr>
            <td class="t1"><span>*</span>版本：</td>
            <td class="t2">
                <sf:input path="version"/>
                <sf:errors path="version" cssStyle="color: red;font-size: 12px"/>
            </td>
        </tr>
        <tr>
            <td class="t1"><span>*</span>关键字/词：</td>
            <td class="t2">
                <sf:input path="keys"/>
                <sf:errors path="keys" cssStyle="color: red;font-size: 12px"/>
            </td>
        </tr>
        <tr>
            <td class="t1">实施日期(yyyy-MM-dd)：</td>
            <td class="t2">
                <sf:input path="impl_date" type="date" cssClass="implDate"/>
                <sf:errors path="impl_date" cssStyle="color:red;font-size: 12px"/>
            </td>
        </tr>
        <tr>
            <td class="t1"><span>*</span>附件：</td>
            <td class="t2">
                <input type="file" class="file" name="multipartFile"/>
                <span class="error">${file}</span>
            </td>
        </tr>
        <td class="t3" colspan="2">
            <input type="submit" class="submit" value="保存">
            <input type="button" <%--class="back" --%>onclick="history.back()" value="取消">
        </td>
    </table>
</sf:form>

<script>
    $(function () {

        $(".back").click(function () {
            location.href = "/standard/list";
        });

        /*$(".stdNum").blur(function () {
            var stdNum = $(".stdNum").val();

            $.get("isExists",{"stdNum":stdNum},function (data) {
                if (data === "err"){
                    alert("标准号重复！")
                }
            })
        });*/

        /*$(".stdNum").bind("blur",function(){
            var stdNum = $(".stdNum").val();
            $.ajax({
                type : "post",
                url : "isExists",
                data : {
                    "stuNum" : stdNum
                },
                dataType : "json",
                success : function(data) {
                    if (data == "ok") {
                        alert("标准号可以使用!");
                        $(".stdNum").attr("result", "succ");
                    } else {
                        alert("标准号重复!");
                        $(".stdNum").attr("result", "fail");
                    }
                },
                error : function() {
                    alert("error!");
                    $(".stdNum").attr("result", "fail");
                }

            });
        });*/


    })
</script>
</body>
</html>
