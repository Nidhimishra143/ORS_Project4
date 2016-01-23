<%@page import="com.raystec.proj4.controller.MarksheetMeritListCtl"%>
<%@page import="com.raystec.proj4.controller.RoleCtl"%>
<%@page import="com.raystec.proj4.controller.BaseCtl"%>
<%@page import="com.raystec.proj4.util.Datautility"%>
<%@page import="com.raystec.proj4.util.ServletUtility"%>
<html>
<body>
	<center>
		<form action="<%=ORSView.ROLEEDIT_CTL%>" method="post">
			<%@ include file="Header.jsp"%>

			<jsp:useBean id="bean" class="com.raystec.proj4.bean.RoleBean"
				scope="request"></jsp:useBean>

			<h1>Update Role</h1>
			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th>Name*</th>
					<td><input type="text" name="name"
						value="<%=Datautility.getStringData(bean.getName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th>Description*</th>
					<td><input type="text" name="description"
						value="<%=Datautility.getStringData(bean.getDescription())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>
				<tr>
					<th></th>

					<td colspan="2"><input type="submit" name="operation"
						value="<%=RoleCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=RoleCtl.OP_CANCEL%>"></td>

				</tr>
			</table>
	</center>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>