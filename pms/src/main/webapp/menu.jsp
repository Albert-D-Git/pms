<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>menu</title>
<link rel="stylesheet" href="skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="skin/css/menu.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script language='javascript'>
	var curopenItem = '1';
</script>
<script language="javascript" type="text/javascript"
	src="skin/js/frame/menu.js"></script>
<script type="text/javascript" src="js/jquery-1.8.1.js"></script>
<base target="main" />
</head>
<body target="main">
	<table  width='99%' height="100%" border='0' cellspacing='0' cellpadding='0' >
        <tr><td style='padding-left:3px;padding-top:8px' valign='top' id="menuss">


			<c:forEach items="${sessionScope.secondSources}" var="source" varStatus="index">
				<c:if test="${index.count == 1}">
					<dl class='bitem'>
						<dt onclick=showHide("items${index.count}_1")><b>${source.name}</b></dt><%--二级菜单--%>
						<dd style='display:block' class='sitem' id=items${index.count}_1>
							<ul class='sitemu' id=${index.index}>
								<c:forEach items="${source.children}" var="third">
									<li><a href='${third.url}' target='main'>${third.name}</a> </li>
								</c:forEach>
							</ul>
						</dd>
					</dl>
				</c:if>
				<c:if test="${index.count != 1}">
					<dl class='bitem'>
						<dt onclick=showHide("items${index.count}_1")><b>${source.name}</b></dt><%--二级菜单--%>
						<dd style='display:none' class='sitem' id=items${index.count}_1>
							<ul class='sitemu' id=${index.index}>
								<c:forEach items="${source.children}" var="third">
									<li><a href='${third.url}' target='main'>${third.name}</a> </li>
								</c:forEach>
							</ul>
						</dd>
					</dl>

				</c:if>

			</c:forEach>


        
		</td>
		</tr>
	</table>
</body>
</html>