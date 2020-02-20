<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加角色</title>
	<script src="${pageContext.request.contextPath}/static/js/ztree/jquery-2.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/script/docs.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/ztree/jquery.ztree.all-3.5.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/ztree/zTreeStyle.css">
	<script type="text/javascript">
		$(function(){
			//异步请求获取数据
			var setting = {
				async: {
					enable: true,
					url:"${pageContext.request.contextPath}/source/list",
					autoParam:["id", "name=n", "level=lv"]
				},
				check: {
					enable: true,
				}
			};
			//初始化
			$.fn.zTree.init($("#permissionTree"), setting);

		});

		function save() {
			var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
			var nodes = treeObj.getCheckedNodes(true);
			var sourcesId=[]
			for (var i = 0; i <nodes.length ; i++) {
				sourcesId.push(nodes[i].id);
			}
			$("#sourcesId").val(sourcesId);
			$("#form2").submit();
		}

	</script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:权限管理>>添加角色信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" id="form2" action="${pageContext.request.contextPath}/role/save" method="post">
	<input type="hidden" id="sourcesId" name="sourcesId">
	<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
	<tr bgcolor="#E7E7E7">
		<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加角色&nbsp;</td>
	</tr>

	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">角色名称：</td>
		<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
			<input name="rolename"/></td>
	</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">状态：</td>
		<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
			<select name="status">
				<option value=1>启用</option>
				<option value=0>禁用</option>
			</select>
		</td>
	</tr>
	<tr >
		<td align="right" bgcolor="#FAFAF1" height="22">赋菜单资源：</td>
		<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">

			<div class="panel-body">
				<ul id="permissionTree" class="ztree"></ul>
			</div>


		</td>
	</tr>

	<tr >
		<td align="right" bgcolor="#FAFAF1" >备注：</td>
		<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
			<textarea rows=10 cols=130 name="roledis"></textarea>
		</td>
	</tr>


	<tr bgcolor="#FAFAF1">
	<td height="28" colspan=4 align=center>
		&nbsp;
		<a href="javascript:save()" class="coolbg">保存</a>
		<a href="role.jsp" class="coolbg">返回</a>
	</td>
	</tr>
	</table>

</form>
  

</body>
</html>