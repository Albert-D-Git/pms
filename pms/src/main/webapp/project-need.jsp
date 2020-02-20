<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>需求分析管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/moment.js"></script>
    <link rel="stylesheet" type="text/css" href="skin/css/base.css">

</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
    <tr>
        <td height="26" background="skin/images/newlinebg3.gif">
            <table width="58%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        当前位置:项目管理>>需求分析管理
                    </td>
                    <td>
                        <input type='button' class="coolbg np" onClick="location='project-need-add.jsp';"
                               value='添加新项目需求'/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='javascript:void(0)' method='get'>
    <input type='hidden' name='dopost' value=''/>
    <table width='98%' border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center"
           style="margin-top:8px">
        <tr bgcolor='#EEF4EA'>
            <td background='skin/images/wbg.gif' align='center'>
                <table border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td width='90' align='center'>搜索条件：</td>
                        <td width='160'>
                            <select id='cid' name='cid' style='width:150'>
                                <option value='0'>选择类型...</option>
                                <option value='1'>项目名称</option>
                                <option value='2'>标题</option>
                            </select>
                        </td>
                        <td width='70'>
                            关键字：
                        </td>
                        <td width='160'>
                            <input type='text' id='keyword' name='keyword' value='' style='width:120px'/>
                        </td>
                        <td width='110'>
                            <select id='orderby' name='orderby' style='width:120px'>
                                <option value='0'>排序...</option>
                                <option value='1'>添加时间</option>
                                <option value='2'>修改时间</option>
                            </select>
                        </td>
                        <td>
                            &nbsp;&nbsp;&nbsp;<input name="imageField" onclick="search()" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif"
                                                     width="45" height="20" border="0" class="np"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<!--  内容列表   -->
<form name="form2" id="form2">

    <table id="table1" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center"
           style="margin-top:8px">
        <tr bgcolor="#E7E7E7">
            <td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
        </tr>
        <tr align="center" bgcolor="#FAFAF1" height="22">
            <td width="4%">选择</td>
            <td width="6%">序号</td>
            <td width="10%">标题</td>
            <td width="10%">项目名称</td>
            <td width="8%">添加时间</td>
            <td width="8%">修改时间</td>
            <td width="10%">操作</td>
        </tr>


        <tr bgcolor="#FAFAF1" id="appendPoint">
            <td height="28" colspan="12">
                &nbsp;
                <a href="" class="coolbg">全选</a>
                <a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:del()" class="coolbg">&nbsp;删除&nbsp;</a>
                <a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
            </td>
        </tr>
        <tr align="right" bgcolor="#EEF4EA">
            <td height="36" colspan="12" align="center"><!--翻页代码 --></td>
        </tr>
    </table>

</form>


</body>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/analyze/getAnalysisList",
            type: "get",
            success: function (obj) {
                $(obj).each(function (index, item) {

                    $("#appendPoint").before("<tr name='deltr' align='center' bgcolor='#FFFFFF' onMouseMove=\"javascript:this.bgColor=\"#FCFDEE\";\" onMouseOut=\"javascript:this.bgColor=\"#FFFFFF\";' height='22' >\n" +
                        "\t<td><input name='id' type='checkbox' id='id' value='" + item.id + "' class='np'></td>\n" +
                        "\t<td>" + (index+1) + "</td>\n" +
                        "\t<td>" + item.title + "</td>\n" +
                        "\t<td align='center'><a href=\"\"><u>" + item.project.pname + "</u></a></td>\n" +
                        "\t<td>" + moment(item.addtime).format('YYYY-MM-DD') + "</td>\n" +
                        "\t<td>" + moment(item.updatetime).format('YYYY-MM-DD') + "</td>\n" +
                        "\t<td><a href='${pageContext.request.contextPath}/project-need-edit.jsp?id="+item.id+"'>编辑</a> | <a href='${pageContext.request.contextPath}/project-need-look.jsp?id="+item.id+"'>查看详情</a></td>\n" +
                        "\t</tr>");
                });
            }
        });
    });

    function search() {
        var cid = $("#cid").val();
        var keyword= $("#keyword").val();
        var orderby = $("#orderby").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/analyze/search",
            type: "get",
            data:{"cid":cid,"keyword":keyword,"orderby":orderby},
            success:function (obj) {
                $("[name='deltr']").remove();
                $(obj).each(function (index,item) {
                    $("#appendPoint").before("<tr name='deltr' align='center' bgcolor='#FFFFFF' onMouseMove=\"javascript:this.bgColor=\"#FCFDEE\";\" onMouseOut=\"javascript:this.bgColor=\"#FFFFFF\";' height='22' >\n" +
                        "\t<td><input name='id' type='checkbox' id='id' value='" + item.id + "' class='np'></td>\n" +
                        "\t<td>" + (index+1) + "</td>\n" +
                        "\t<td>" + item.title + "</td>\n" +
                        "\t<td align='center'><a href=\"\"><u>" + item.project.pname + "</u></a></td>\n" +
                        "\t<td>" + moment(item.addtime).format('YYYY-MM-DD') + "</td>\n" +
                        "\t<td>" + moment(item.updatetime).format('YYYY-MM-DD') + "</td>\n" +
                        "\t<td><a href='${pageContext.request.contextPath}/project-need-edit.jsp?id="+item.id+"'>编辑</a> | <a href='${pageContext.request.contextPath}/project-need-look.jsp?id="+item.id+"'>查看详情</a></td>\n" +
                        "\t</tr>");
                })
            }
        });
    }

    function del() {
        var ids=[];
        $("input:checked").each(function () {
            var id = $(this).val();
            ids.push(id);
        });
        if(confirm("确认删除吗？")){
            $.ajax({
                url:"${pageContext.request.contextPath}/analyze/del",
                type:"post",
                data:{"_method":"delete","ids":ids},
                dataType:"json",
                success:function (obj) {
                    if(obj.map.statusCodes==200){
                        alert(obj.map.message);
                        window.location.href="${pageContext.request.contextPath}/project-need.jsp";
                    }else{
                        alert(obj.map.message);
                    }
                }
            });
        }
    }
</script>
</html>