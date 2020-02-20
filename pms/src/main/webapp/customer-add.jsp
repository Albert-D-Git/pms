<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加客户信息</title>
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
    当前位置:客户信息管理>>添加客户信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form2" method="post" action="${pageContext.request.contextPath}/cust/insertCustomer">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加客户&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="comname" name="comname" onblur="validateComname()"/>&nbsp;&nbsp;<span id="comname_span"></span>
	</td>

</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司联系人：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="companyperson" name="companyperson"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司地址：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="comaddress" name="comaddress" size="60"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">联系电话：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="comphone" name="comphone"/>&nbsp;&nbsp;<span id="comphone_span"></span>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">座机：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="camera" name="camera" placeholder="xxx-xxxxxxx"/>&nbsp;&nbsp;<span id="camera_span"></span>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司简介：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea id="present" name="present" rows=15 cols=130></textarea></td>
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
<%--	<a href="javascript:history.go(-1)" class="coolbg">返回</a>--%>
	<a href="${pageContext.request.contextPath}/cust/list" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  
<script type="text/javascript">
	function validateComname(){
		var comname = $("#comname").val();
		if(comname != ""){
			$.ajax({
				url:"${pageContext.request.contextPath}/cust/validateComname",
				data:{"comname":comname},
				type:"get",
				dataType:"json",
				success:function (obj) {
					if (obj.status == 200){
						$("#comname_span").css({"color":"green"});
						$("#comname_span").html("✔");
					}else{
						$("#comname_span").css({"color":"red"});
						$("#comname_span").html("该公司名不可用");
						$("#comname").focus();
					}
				}
			})
		}else{
			$("#comname_span").css({"color":"red"});
			$("#comname_span").html("请输入公司名");
		}
	}

	function validateComphone() {
		var comphone = $("#comphone").val();
		var phone = /^[1][3,4,5,7,8][0-9]{9}$/;
		if(!phone.test(comphone)){
			$("#comphone_span").css({"color":"red"});
			$("#comphone_span").html("手机号不可用！");
			return false;
		}else{
			$("#comphone_span").css({"color":"green"});
			$("#comphone_span").html("手机号可用！");
			return true;
		}
	}

	function validateCamera() {
		var camera=$("#camera").val();
		var number = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
		if (!number.test(camera)){
			$("#camera_span").css({"color":"red"});
			$("#camera_span").html("座机号不合法！");
			return false;
		}else{
			$("#camera_span").css({"color":"green"});
			$("#camera_span").html("座机号可用！");
			return true;
		}
	}

	function save() {

		//var comphoneStatus = validateComphone();
		//var cameraStatus = validateCamera();
		var comname=$("#comname").val();
		var companyperson=$("#companyperson").val();
		var comaddress=$("#comaddress").val();
		var present=$("#present").val();
		if(comname==""){
			alert("公司名不能为空");
			return;
		}else if(!validateComphone()){
			return;
		}else if (!validateCamera()){
			return;
		}else if(companyperson==""){
			alert("联系人不能为空");
			return;
		}else if (comaddress==""){
			alert("公司地址不能为空");
			return
		}else if(present==""){
			alert("公司简介不能为空");
			return;
		}else{
			$("#form2").submit();
		}
	}
</script>
</body>

</html>