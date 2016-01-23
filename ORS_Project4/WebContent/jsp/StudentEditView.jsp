<%@page import="com.raystec.proj4.controller.MarksheetMeritListCtl"%>
<%@page import="com.raystec.proj4.bean.CollegeBean"%>
<%@page import="com.raystec.proj4.controller.StudentCtl"%>
<%@page import="com.raystec.proj4.util.HTMLUtility"%>
<%@page import="com.raystec.proj4.bean.DropdownListBean"%>
<%@page import="com.raystec.proj4.controller.BaseCtl"%>
<%@page import="com.raystec.proj4.util.Datautility"%>
<%@page import="com.raystec.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>


<html>
<body>
	<center>
		<form action="<%=ORSView.STUDENTEDIT_CTL%>" method="post">
			<%@ include file="Header.jsp"%>
			<script type="text/javascript" src="../js/calendar.js"></script>
			<jsp:useBean id="bean" class="com.raystec.proj4.bean.StudentBean"
				scope="request"></jsp:useBean>

			<%
				List l = (List) request.getAttribute("collegeList");
				//out.println(l);
			%>

			<center>
				<h1>Update Student</h1>

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
						<th>College*</th>
						<td><%=HTMLUtility.getList("collegeId", String.valueOf(bean.getCollegeId()), l)%></td>

					</tr>
					<tr>
						<th>First Name*</th>
						<td><input type="text" name="firstName"
							value="<%=Datautility.getStringData(bean.getFirstName())%>"
							<%=(bean.getId() > 0) ? "readonly" : ""%>><font
							color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
					</tr>
					<tr>
						<th>Last Name*</th>
						<td><input type="text" name="lastName"
							value="<%=Datautility.getStringData(bean.getLastName())%>"><font
							color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
					</tr>
					<tr>
						<th>Date Of Birth (mm/dd/yyyy)</th>
						<td><input type="text" name="dob" readonly="readonly"
							value="<%=Datautility.getDateString(bean.getDob())%>"> <a
							href="javascript:getCalendar(document.forms[0].dob);"> <img
								src="../img/cal.jpg" width="16" height="15" border="0"
								alt="Calender">
						</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
					</tr>
					<tr>
						<th>MobileNo*</th>
						<td><input type="text" name="mobileNo"
							value="<%=Datautility.getStringData(bean.getMobileNo())%>"><font
							color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
					</tr>
					<tr>
						<th>Email ID*</th>
						<td><input type="text" name="email"
							value="<%=Datautility.getStringData(bean.getEmail())%>"><font
							color="red"> <%=ServletUtility.getErrorMessage("email", request)%></font></td>
					</tr>


					<tr>
						<th></th>

						<td colspan="2"><input type="submit" name="operation"
							value="<%=StudentCtl.OP_SAVE%>"> <%
 	if (bean.getId() > 0) {
 %> &emsp;<input type="submit" name="operation"
							value="<%=StudentCtl.OP_DELETE%>"> <%
 	}
 %>&emsp; <input type="submit" name="operation"
							value="<%=StudentCtl.OP_CANCEL%>"></td>
					</tr>
				</table>
			</center>
		</form>

		<%@ include file="Footer.jsp"%>
</body>
</html>