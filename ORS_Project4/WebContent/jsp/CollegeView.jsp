
<%@page import="com.raystec.proj4.controller.CollegeCtl"%>
<%@page import="com.raystec.proj4.controller.BaseCtl"%>
<%@page import="com.raystec.proj4.util.Datautility"%>
<%@page import="com.raystec.proj4.util.ServletUtility"%>
<html>
<body>
	<center>
		<form action="<%=ORSView.COLLEGE_CTL%>" method="post">
			<%@ include file="Header.jsp"%>

			<jsp:useBean id="bean" class="com.raystec.proj4.bean.CollegeBean"
				scope="request"></jsp:useBean>


			<h1>Add College</h1>

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
					<th>Address*</th>
					<td><input type="text" name="address"
						value="<%=Datautility.getStringData(bean.getAddress())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
				</tr>
				<tr>
					<th>State*</th>
					<td><input type="text" name="state"
						value="<%=Datautility.getStringData(bean.getState())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>
				<tr>
					<th>City*</th>
					<td><input type="text" name="city"
						value="<%=Datautility.getStringData(bean.getCity())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
				</tr>
				<tr>
					<th>PhoneNo*</th>
					<td><input type="text" name="phoneNo"
						value="<%=Datautility.getStringData(bean.getPhoneNo())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>

				<tr>
					<th></th>

					<td colspan="2"><input type="submit" name="operation"
						value="<%=CollegeCtl.OP_SAVE%>"> <%
 	if (bean.getId() > 0) {
 %> &emsp;<input type="submit" name="operation"
						value="<%=CollegeCtl.OP_DELETE%>"> <%
 	}
 %>&emsp; <input type="reset" name="operation"
						value="<%=CollegeCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
		</form>
		<%@ include file="Footer.jsp"%>
	</center>

</body>
</html>