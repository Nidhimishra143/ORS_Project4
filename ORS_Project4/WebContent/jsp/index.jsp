<%@page import="com.raystec.proj4.bean.UserBean"%>

<%@page import="com.raystec.proj4.controller.ORSView"%>
<html>
<body>
	
	
	<form action="">
	
	
	<table width="100%" border="0">
		<tr>
			<td>
				<table>
					<tr>
						<td width="80%">
							<a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a>&nbsp;&nbsp;&nbsp;
					

 	</td>
						<td width="20%">
							<h1 align="Right">
								<img src="img/customLogo.jpg" width="318" height="90">
							</h1>
						</td>
					</tr>
				</table>
				<hr>
			
		<h1 align="Center">
			<font size="10px" color="red">Welcome to ORS </font>
		</h1>
		<br> <br> <a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Click
				Here </b></a>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>
