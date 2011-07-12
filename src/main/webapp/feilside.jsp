<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/styles.css" type="text/css"/>
	<title>Feil</title>
	</head>
	<body>
		<div class="greyboxError">
					
			 		 <%=request.getAttribute("feilmelding") %>
		</div>
	</body>
</html>