<%@page import="com.raystec.proj4.controller.LoginCtl"%>
<%@page import="com.raystec.proj4.util.Datautility"%>
<%@page import="com.raystec.proj4.util.ServletUtility"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<center>

		<form action="<%=ORSView.LOGIN_CTL%>" method="post">

			<%@ include file="Header.jsp"%>

			<jsp:useBean id="bean" class="com.raystec.proj4.bean.UserBean"
				scope="request"></jsp:useBean>


			<h1>Login</h1>

			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th>LoginId*</th>
					<td><input type="text" name="login" size=30
						value="<%=Datautility.getStringData(bean.getLogin())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<th>Password*</th>
					<td><input type="password" name="password" size=30
						value="<%=Datautility.getStringData(bean.getPassword())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp; <input
						type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>">
						&nbsp;</td>
				</tr>
				<tr>
					<th></th>
					<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget
								my password</b></a>&nbsp;</td>
				</tr>
			</table>
		</form>
		<%@ include file="Footer.jsp"%>
	</center>
</body>
</html>