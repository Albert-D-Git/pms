<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
</head>
<body>
<tr align="right" bgcolor="#EEF4EA">
    <td height="36" colspan="12" align="center"><!--翻页代码 -->
        <div>
            <a href="${requestURI}?pageNo=1${queryStr}">首页</a>
            <a href="${requestURI}?pageNo=${page.pageNum-1}${queryStr}">上一页</a>
            <c:forEach items="${page.navigatepageNums}" var="pageNum">

                <c:if test="${page.pageNum == pageNum}">
                    [<span style="color: red">${pageNum}</span>]
                </c:if>
                <c:if test="${page.pageNum != pageNum}">
                    <a href="${requestURI}?pageNo=${pageNum}${queryStr}">${pageNum}</a>
                </c:if>


            </c:forEach>
            <a href="${requestURI}?pageNo=${page.pageNum+1}${queryStr}">下一页</a>

            <a href="${requestURI}?pageNo=${page.pages}${queryStr}">末页</a>

            &nbsp;&nbsp;<input id="search-input" size="1px" onchange="search()">
        </div>
    </td>
</tr>
</body>
<script type="text/javascript">
    function search() {
        var num = $("#search-input").val();
        var pattern = /(^[1-9]\d*$)/;
        if(!pattern.test(num)){
            alert("请输入正整数！");
            return;
        }else {
            window.location.href="${requestURI}?pageNo="+num+"${queryStr}";
        }
    }
</script>
</html>
