<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑需求分析信息</title>
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
    当前位置:项目管理>>编辑需求分析信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form2">
<input id="id" name="id" type="hidden">
	<input type="hidden" name="_method" value="put">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;编辑需求信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">项目：</td>
	<td id="proname" align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22"></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="title" name="title" /></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">简单描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea id="simpledis" name="simpledis" rows=10 cols=130></textarea>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">详细描述：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea id="detaileddis" name="detaileddis" rows=15 cols=130></textarea>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea id="remark" name="remark" rows=10 cols=130></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:save()" class="coolbg">保存</a>
	<a href="javascript:history.go(-1)" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  <script type="text/javascript">
	  $(function() {
		  //window.location.search可以获取所有传过来的参数
		  var params = window.location.search;
		  params=params.substring(1);
		  var id = params.split("=")[1];
		  $("#id").val(id);
		  $.ajax({
			  type:"put",
			  url:"${pageContext.request.contextPath}/analyze/edit/"+id,
			  data:{"_method":"put"},
			  success:function (obj) {
					$("#proname").text(obj.proname);
					$("#title").val(obj.title);
					$("#simpledis").val(obj.simpledis);
					$("#detaileddis").val(obj.detaileddis);
					$("#remark").val(obj.remark);
			  }
		  });
	  });

	  function save() {
			$.ajax({
				url:"${pageContext.request.contextPath}/analyze/edit",
				type:"post",
                data:$("#form2").serialize(),
				success:function (obj) {
					if(obj.status==200){
						alert(obj.msg);
						window.location.href="${pageContext.request.contextPath}/project-need.jsp";
					}else {
						alert(obj.msg);
					}
				}
			});
	  }
  </script>

</body>
</html>