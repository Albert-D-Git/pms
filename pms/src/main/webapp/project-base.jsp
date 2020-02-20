<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="frm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>项目信息管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>基本信息管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/project-base-add.jsp';" value='添加新项目' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='${pageContext.request.contextPath}/pro/search' method='get'>
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>项目名称</option>
          	<option value='2'>项目经理</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderby' style='width:120px'>
            <option value='0'>排序...</option>
            <option value='1'>立项时间</option>
            <option value='2'>计划完成时间</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;项目信息列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="4%">序号</td>
	<td width="10%">项目名称</td>
	<td width="10%">客户公司名称</td>
	<td width="10%">客户方负责人</td>
	<td width="5%">项目经理</td>
	<td width="8%">开发人员数</td>
	<td width="8%">立项时间</td>
	<td width="8%">开始时间</td>
	<td width="8%">计划完成时间</td>
	<td width="8%">备注</td>
	<td width="10%">操作</td>
</tr>

    <c:forEach items="${list}" var="project" varStatus="i">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
            <td><input name="id" type="checkbox" id="id" value="${project.pid}" class="np"></td>
            <td>${i.count}</td>
            <td align="left"><a href=''><u>${project.pname}</u></a></td>
            <td>${project.customer.comname}</td>
            <td>${project.comper}</td>
            <td>${project.employee.ename}</td>
            <td>${project.empcount}</td>
            <td>
                <frm:formatDate value="${project.buildtime}" pattern="yyyy-MM-dd"/>
            </td>
            <td><frm:formatDate value="${project.starttime}" pattern="yyyy-MM-dd"/></td>
            <td><frm:formatDate value="${project.endtime}" pattern="yyyy-MM-dd"/></td>
            <td>${project.remark}</td>
            <td><a href="${pageContext.request.contextPath}/pro/edit/${project.pid}">编辑</a> | <a href="${pageContext.request.contextPath}/pro/detail/${project.pid}">查看详情</a></td>
        </tr>
    </c:forEach>



<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:selectAll()" class="coolbg">全选</a>
	<a href="javascript:disSelect()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:batchDel()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<%--<tr align="right" bgcolor="#EEF4EA">--%>
<%--	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>--%>
<%--</tr>--%>
    <jsp:include page="paging.jsp"/>
</table>

</form>
<script type="text/javascript">
    function selectAll() {
        // $('input[ type=checkbox]').attr("checked",true);
        $("input:checkbox").attr("checked",true);
    }

    function disSelect() {

        $("input:checkbox").each(function () {
            var status = $(this).attr("checked");
            $(this).attr("checked",!status);
        });
    }

    function batchDel() {
        //拼接批量选中的id
        var ids=[];
        $("input:checked").each(function () {
            //获取Id
            var id = $(this).val();
            ids.push(id);
        })
        if(confirm("确认删除吗？")){
            $.ajax({
                url:"${pageContext.request.contextPath}/pro/batchDel",
                type:"post",
                data:{"_method":"delete","ids":ids},
                dataType:"json",
                success:function (obj) {
                    if(obj.status==200){
                        alert(obj.message);
                        window.location.href="${pageContext.request.contextPath}/pro/list"
                    }else{
                        alert(obj.message);
                    }
                }
            })
        }
    }
</script>
</body>
</html>