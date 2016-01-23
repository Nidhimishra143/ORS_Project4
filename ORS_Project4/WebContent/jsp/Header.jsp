<%@page import="com.raystec.proj4.bean.UserBean"%>
<%@page import="com.raystec.proj4.controller.ORSView"%>
<html>
<body>
	<table width="100%" border="0">
		<tr>
			<td>
				<table>
					<tr>
						<td width="80%"><a href="<%=ORSView.WELCOME_CTL%>"><b>Welcome</b></a>&nbsp;
							<%
								UserBean user = (UserBean) session.getAttribute("user");
								if (user == null) {
							%> <a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a>&nbsp;&nbsp;&nbsp;
							<%
								}
							%> <%
 	UserBean user1 = (UserBean) session.getAttribute("user");
 	System.out.println("Login:" + user);
 	if (user1 != null) {
 		if (user.getRoleId() == 1) {
 %> <a href="<%=ORSView.MARKSHEET_CTL%>"><b>Add Marksheet</b></a>&nbsp;&nbsp;&nbsp;
							<a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>Marksheet
									List</b></a>&nbsp;&nbsp;&nbsp; <a
							href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet
									Merit List</b></a>&nbsp; <a href="<%=ORSView.USER_CTL%>"><b>Add
									User</b></a>&nbsp;&nbsp;&nbsp; <a href="<%=ORSView.USER_LIST_CTL%>"><b>User
									List</b></a>&nbsp;&nbsp;&nbsp; <a href="<%=ORSView.COLLEGE_CTL%>"><b>Add
									College</b></a>&nbsp; <a href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College
									List</b></a>&nbsp; <a href="<%=ORSView.STUDENT_CTL%>"><b>Add
									Student</b></a>&nbsp; <a href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student
									List</b></a>&nbsp; <a href="<%=ORSView.ROLE_CTL%>"><b>Add Role</b></a>&nbsp;
							<a href="<%=ORSView.ROLE_LIST_CTL%>"><b>Role List</b></a>&nbsp; <a
							href="<%=ORSView.Java_DOC_VIEW%>"><b>Java Doc</b></a>&nbsp; <a
							href="<%=ORSView.LOGOUT_CTL%>?operation=logout"><b>Logout</b></a>&nbsp;&nbsp;&nbsp;
							<br> <br>

							<h3>
								Hello :
								<%=user.getFirstName()%>(<%=session.getAttribute("role")%>)
							</h3> <%
 	}
 		if (user.getRoleId() == 2) {
 %> <a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget
									Password</b></a>&nbsp; <a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>Get
									Marksheet</b></a>&nbsp; <a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet
									Merit List</b></a>&nbsp; <a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a>&nbsp;
							<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change
									Password</b></a>&nbsp; <a href="<%=ORSView.Java_DOC_VIEW%>"><b>Java
									Doc</b></a>&nbsp; <a href="<%=ORSView.LOGIN_CTL%>?operation=logout"><b>Logout</b></a>&nbsp;&nbsp;&nbsp;

							<h3>
								Hello :
								<%=user.getFirstName()%>(<%=session.getAttribute("role")%>)
							</h3> <%
 	}
 	} else {

 	}
 %></td>
						<td width="20%">
							<h1 align="Right">
								<img src="../img/customLogo.jpg" width="318" height="90">
							</h1>
						</td>
					</tr>
				</table>
				<hr>
			</td>
		</tr>
	</table>
</body>
</html>