<%@page import="com.raystec.proj4.controller.MyProfileCtl"%>
<%@page import="com.raystec.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.raystec.proj4.util.Datautility"%>
<%@page import="com.raystec.proj4.util.ServletUtility"%>
<html>
<body>

	<form action="<%=ORSView.MY_PROFILE_CTL%>"method="post">

		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="com.raystec.proj4.bean.UserBean"
			scope="request"></jsp:useBean>


		<h1>My Profile</h1>

		<H2>
			<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font>
		</H2>
		<input type="hidden" name="id" value="<%=bean.getId()%>">

		<table>
			<tr>
				<th>LoginId*</th>
				<td><input type="text" name="login"
					value="<%=Datautility.getStringData(bean.getLogin())%>"
					readonly="readonly"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
			</tr>

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
				<th>Gender</th>
				<td>
					<%
						HashMap map = new HashMap();
					
						map.put("M", "Male");
						map.put("F", "Female");

						String htmlList = HTMLUtility.getList("gender", bean.getGender(),
								map);
					%> <%=htmlList%>
				</td>
			</tr>
			<tr>
				<th>Mobile No*</th>
				<td><input type="text" name="mobileNo"
					value="<%=Datautility.getStringData(bean.getMobileNo())%>"><font
					color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
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

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>

			<tr>
				<th></th>
				<td colspan="2"><input type="submit" name="operation"
					value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>"> &nbsp; <input
					type="submit" name="operation" value="<%=MyProfileCtl.OP_SAVE%>">
					&nbsp;</td>
			</tr>
		</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>