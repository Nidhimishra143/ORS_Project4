<%@page import="com.raystec.proj4.controller.MarksheetMeritListCtl"%>
<%@page import="com.raystec.proj4.controller.UserCtl"%>
<%@page import="java.util.List"%>
<%@page import="com.raystec.proj4.controller.BaseCtl"%>
<%@page import="com.raystec.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.raystec.proj4.util.Datautility"%>
<%@page import="com.raystec.proj4.util.ServletUtility"%>
<html>
<body>
<center>
	<form action="<%=ORSView.USEREDIT_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="com.raystec.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("roleList");
		%>

		<center>
			<h1>Update User</h1>

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th>First Name*</th>
					<td><input type="text" name="firstName"
						value="<%=Datautility.getStringData(bean.getFirstName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th>Last Name*</th>
					<td><input type="text" name="lastName"
						value="<%=Datautility.getStringData(bean.getLastName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th>LoginId*</th>
					<td><input type="text" name="login"
						value="<%=Datautility.getStringData(bean.getLogin())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th>Password*</th>
					<td><input type="password" name="password"
						value="<%=Datautility.getStringData(bean.getPassword())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th>Confirm Password*</th>
					<td><input type="password" name="confirmPassword"
						value="<%=Datautility.getStringData(bean.getPassword())%>"><font
						color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>
				<tr>
					<th>Gender</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("M", "Male");
							map.put("F", "Female");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(),
									map);
						%> <%=htmlList%> <b>Role :</b> <%=HTMLUtility.getList("roleId",
					String.valueOf(bean.getRoleId()), l)%></td>
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
					<th></th>
					
					<td colspan="2"><input type="submit" name="operation"
						value="<%=UserCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=UserCtl.OP_CANCEL%>">
						
						</td>
				</tr>
			</table>
			</center>
			
	</form>
		<%@ include file="Footer.jsp"%>
</body>
</html>