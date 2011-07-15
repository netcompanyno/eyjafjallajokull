<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/styles.css" type="text/css"/> 
	<title>Ny bruker</title>
	</head>
	<body>
		<div align="center" class="greyboxLogin" > 
			<form method="POST" action="NyBrukerServlet">
				<table class="noborderTable">
					<tr>
						<td>Navn: </td>
						<td><input type="text" name="navn" ></td>
					</tr>
					<tr>
						<td>Brukernavn: </td>
						<td><input type="text" name="brukerid" ></td>
					</tr>
					<tr>
						<td>Passord: </td>
						<td><input type="password" name="passord" ></td>
					</tr>
					<tr>
						<td align="left" ><a href="/login.jsp">Registrer ny</a></td>
						<td align="right" ><input type="submit"  value="Registrer"></td>
					</tr>
				</table>
			</form>
		</div>
		<br/>
		<br/>
	</body>
</html>