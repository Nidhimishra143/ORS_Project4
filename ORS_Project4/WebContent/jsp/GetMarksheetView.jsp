<%@page import="com.raystec.proj4.controller.GetMarksheetCtl"%>
<%@page import="com.raystec.proj4.controller.BaseCtl"%>
<%@page import="com.raystec.proj4.util.Datautility"%>
<%@page import="com.raystec.proj4.util.ServletUtility"%>
<html>
<body>
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="bean" class="com.raystec.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>


	<h1>Get Marksheet</h1>

	<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
	</font>

	<H2>
		<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font>
	</H2>

	<form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">

		<input type="hidden" name="id" value="<%=bean.getId()%>">
		<table>
			<label>RollNo :</label>&emsp;
			<input type="text" name="rollNo"
				value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
			<input type="submit" name="operation"
				value="<%=GetMarksheetCtl.OP_GO%>">
			<br>
			<font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>

			<%
				if (bean.getRollNo() != null
						&& bean.getRollNo().trim().length() > 0) {
			%>

			<tr>
				<td>Rollno :</td>
				<td><%=Datautility.getStringData(bean.getRollNo())%></td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><%=Datautility.getStringData(bean.getName())%></td>
			</tr>
			<tr>
				<td>Physics :</td>
				<td><%=Datautility.getStringData(bean.getPhysics())%></td>
			</tr>
			<tr>
				<td>Chemistry :</td>
				<td><%=Datautility.getStringData(bean.getChemistry())%></td>
			</tr>
			<tr>
				<td>Maths :</td>
				<td><%=Datautility.getStringData(bean.getMaths())%></td>

			</tr>
			<tr>
				</td>
			</tr>
		</table>
		<%
			}
		%>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>