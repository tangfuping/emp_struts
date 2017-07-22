<%@ page language="java" import="java.util.*,entity.Employee"
	pageEncoding="utf-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>emplist</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div id="wrap">
		<div id="top_content">
			<div id="header">
				<div id="rightheader">
					<p>
						当前的用户：
						<%-- <%=session.getAttribute("username") %> --%>
						<!--  el表达式 -->
						${sessionScope.user.username}<br />
					</p>
					<p>
						<%-- 当前的用户：
						<%=session.getAttribute("username") %>
						<!--  el表达式 -->
						${sessionScope.user.username}<br /> --%>
						<%@include file="head.jsp"%>
						<br />
					</p>
				</div>
				<div id="topheader">
					<h1 id="title">
						<!-- <a href="#">main</a> -->
						<form action="emp_findByName.action" method="post">
							<input type="text" class="inputgri" name="sqlname"
								value="请输入你要查询 的姓名" /> <input type="submit" class="button"
								value="查询" />
						</form>
					</h1>
				</div>
				<div id="navigation"></div>
			</div>
			<div id="content">
				<p id="whereami"></p>
				<h1>Welcome!</h1>
				<table class="table">
					<tr class="table_header">
						<td>ID</td>
						<td>Name</td>
						<td>Salary</td>
						<td>Age</td>
						<td>Operation</td>
					</tr>

					<%
						List<Employee> employees = (List<Employee>) request
								.getAttribute("employeeByName");
						for (Employee e : employees) {
					%>

					<tr class="row1">
						<td><%=e.getT_id()%></td>
						<td><%=e.getEmpname()%></td>
						<td><%=e.getEmpsalary()%></td>
						<td><%=e.getEmpage()%></td>
						<td><a href="emp_del?id=<%=e.getT_id()%>"
							onclick="return confirm('是否要删除<%=e.getEmpname()%>');">删除</a>&nbsp;<a
							href="emp_update?id=<%=e.getT_id()%>">修改</a></td>
					</tr>

					<%
						}
					%>
				</table>
				<p>
					<input type="button" class="button" value="添加员工"
						onclick="location='emp_toAddPage'" />
				</p>
			</div>
		</div>
		<div id="footer">
			<div id="footer_bg">ABC@126.com</div>
		</div>
	</div>
</body>
</html>

