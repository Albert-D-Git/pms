<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>附件管理</title>
    <link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/paging.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
    <tr>
        <td height="26" background="skin/images/newlinebg3.gif">
            <table width="58%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        当前位置:个人报销管理>>报销列表
                    </td>
                    <td>
                        <input type='button' class="coolbg np" onClick="location='mybaoxiao-add.jsp';" value='添加报销'/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<form name='form3' action='javascript:void(0)' method='get'>
    <table width='98%' border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center"
           style="margin-top:8px">
        <tr bgcolor='#EEF4EA'>
            <td background='skin/images/wbg.gif' align='center'>
                <table border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td width='90' align='center'>搜索条件：</td>
                        <td width='160'>
                            <select id="cid" name='search_cid' style='width:150px'>
                                <option value='0'>选择类型...</option>
                                <option value='1'>驳回</option>
                                <option value='2'>已审批</option>
                                <option value='3'>未审批</option>
                                <option value='4'>已付款</option>
                            </select>
                        </td>
                        <td width='70'>
                            关键字：
                        </td>
                        <td width='160'>
                            <input type='text' name='search_keyword' value='' style='width:120px'/>
                        </td>

                        <td>
                            &nbsp;&nbsp;&nbsp;<input name="imageField" onclick="query()" type="image"
                                                     src="${pageContext.request.contextPath}/skin/images/frame/search.gif"
                                                     width="45" height="20" border="0" class="np"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>


<!--  内容列表   -->
<form name="form2" action="javascript:void(0)">

    <table width="98%" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
        <tr bgcolor="#E7E7E7">
            <td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
        </tr>
        <tr align="center" bgcolor="#FAFAF1" height="22">
            <td width="4%">选择</td>
            <td width="20%">编号</td>
            <td width="6%">总金额</td>
            <td width="10%">使用时间</td>
            <td width="40%">备注信息</td>
            <td width="10%">审批状态</td>
            <td width="10%">操作</td>
        </tr>

<%--        同步分页--%>
        <%--<c:forEach items="${page.list}" var="bs">
            <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';"
                onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
                <td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
                <td>${bs.bxid}</td>
                <td>${bs.totalmoney}</td>
                <td align="center"><a href=''><u> <fmt:formatDate value="${bs.bxtime}"
                                                                  pattern="yyyy-MM-dd"></fmt:formatDate> </u></a></td>
                <td>${bs.bxremark}</td>

                <c:if test="${bs.bxstatus == 0}">
                    <td>未审批</td>
                    <td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}">编辑</a></td>
                </c:if>
                <c:if test="${bs.bxstatus == 1}">
                    <td>驳回</td>
                    <td><a href="javascript:void(0)" style="pointer-events:none;color: grey">编辑</a></td>
                </c:if>
                <c:if test="${bs.bxstatus == 2}">
                    <td>已审批</td>
                    <td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}">编辑</a></td>
                </c:if>
                <c:if test="${bs.bxstatus == 3}">
                    <td>已付款</td>
                    <td><a href="javascript:void(0)" style="pointer-events:none;color: grey">编辑</a></td>
                </c:if>

            </tr>
        </c:forEach>--%>
<%--同步分页--%>
<%--        <jsp:include page="paging.jsp"/>--%>
        <tr id="pageTr" align="right" bgcolor="#EEF4EA">
            <td height="36" colspan="12" align="center"><!--翻页代码 -->
                <div id="pagediv">

                </div>
            </td>
        </tr>

    </table>

</form>

<script type="text/javascript">
	$(function () {
	    //原始
		/*$.ajax({
			type:"get",
            url:"${pageContext.request.contextPath}/bx/list",
            success:function(msg){
                $("tr[name='list-tr']").remove();
                $("#pagediv").html("");
                //1.遍历列表数据
                //2.追加分页信息
                showList(msg);
            }
		});*/
        //提取后
        queryDate("${pageContext.request.contextPath}/bx/list");
	});



    function query() {
        var cid = $("#cid").val();
        var keyword = $("input[name='search_keyword']").val()

        queryDate("${pageContext.request.contextPath}/bx/list?search_cid="+cid+"&search_keyword="+keyword);
    }

</script>

</body>
</html>