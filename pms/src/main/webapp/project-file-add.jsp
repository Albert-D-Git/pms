<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加附件</title>
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
    当前位置:项目管理>>添加附件
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form2" action="${pageContext.request.contextPath}/attachment/save" method="post" enctype="multipart/form-data">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加附件&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">选择项目：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="proFk" name="proFk">

		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="attname" name="attname" size="26"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件信息描述：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="attdis" name="attdis" size="52"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">附件：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input if="file" name="file" type="file"/></td>
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
	$(function () {
		$.ajax({
			url:"${pageContext.request.contextPath}/pro/listAtt",
			type:"get",
			success:function (obj) {
				$(obj).each(function (index,item) {
					$("#proFk").append("<option value='"+item.pid+"'>"+item.pname+"</option>");
				})
			}
		});
	});

	function save() {
		$("#form2").submit();
	}

	//异步上传文件 这里没使用
	function upload() {
		var formdata = new FormData();//encytype="multipart/form-data"
		formdata.append("proFk",$("#proFk").val());
		formdata.append("attname",$("#attname").val());
		formdata.append("attdis",$("#attdis").val());
		formdata.append("remark",$("#remark").val());
		var file =  $("#file")[0].files[0];//jquery对象转成js对象 .后面获取上传文件的第一个文件
		formdata.append("file",file);

		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/attachment/save",
			data:formdata,
			processData:false,//不让ajax对上传的formdata对象进行处理
			contentType:false,//ajax不要对formdate数据设置内容编码
			cache:false,//文件上传不需要缓存
			success:function (obj) {

			}
		});
	}
</script>

</body>
</html>