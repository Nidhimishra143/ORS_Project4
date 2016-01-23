
<%@page import="com.raystec.proj4.controller.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="com.raystec.proj4.util.HTMLUtility"%>
<%@page import="com.raystec.proj4.controller.BaseCtl"%>
<%@page import="com.raystec.proj4.util.Datautility"%>
<%@page import="com.raystec.proj4.util.ServletUtility"%>
<html>
<body>
	<center>
		<%@ include file="Header.jsp"%>
		<jsp:useBean id="bean" class="com.raystec.proj4.bean.MarksheetBean"
			scope="request"></jsp:useBean>
		<%
			List l = (List) request.getAttribute("studentList");
		
		%>
		<h1>Add Marksheet</h1>
		<H2>
			<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font>
		</H2>
		<H2>
			<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
			</font>
		</H2>

		<form action="<%=ORSView.MARKSHEET_CTL%>"method="post">


			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th>Rollno*</th>
					<td><input type="text" name="rollNo"
						value="<%=Datautility.getStringData(bean.getRollNo())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>> <font
						color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
					</font>
					</td>
				</tr>
				<tr>
					<th>Name*</th>
					<td><%=HTMLUtility.getList("studentId", String.valueOf(bean.getStudentId()), l)%>
					<font
						color="red"> <%=ServletUtility.getErrorMessage("studentId", request)%></font></td>
				</tr>
				<tr>
					<th>Physics</th>
					<td><input type="text" name="physics"
						value="<%=Datautility.getStringData(bean.getPhysics())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
				</tr>
				<tr>
					<th>Chemistry</th>
					<td><input type="text" name="chemistry"
						value="<%=Datautility.getStringData(bean.getChemistry())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
				</tr>
				<tr>
					<th>Maths</th>
					<td><input type="text" name="maths"
						value="<%=Datautility.getStringData(bean.getMaths())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>

				</tr>
				<tr>
					<th></th>
					
					<td colspan="2"><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_SAVE%>"> <%
 	if (bean.getId() > 0) {
 %><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_DELETE%>"> <%
 	}
 %> <input type="reset" name="operation"
						value="<%=MarksheetCtl.OP_CANCEL%>"></td>

				</tr>
			</table>
	</center>
	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>