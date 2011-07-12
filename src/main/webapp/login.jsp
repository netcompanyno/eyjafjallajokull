<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="java.util.Iterator" %>
    <%@ page import="no.mesan.ejafjallajokull.pojo.Huskelapp" %>
	<jsp:useBean id="huskelapper" type="ArrayList<Huskelapp>" scope="request" />
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/styles.css" type="text/css"/> 
	<title>Startside for usikker.no</title>
	</head>
	<body>
		<div align="center" class="greyboxLogin" > 
			<form method="POST" action="LoginServlet">
				<table class="noborderTable">
					<tr>
						<td>Brukernavn: </td>
						<td><input type="text" name="brukerid" ></td>
					</tr>
					<tr>
						<td>Passord: </td>
						<td><input type="password" name="passord" ></td>
					</tr>
					<tr>
						<td align="right" colspan="2"><input type="submit"  value="Logg inn"></td>
						
					</tr>
				</table>
			</form>
		</div>
		<br/>
		<br/>
		
		<% 
			Iterator<Huskelapp> it = huskelapper.iterator();
			
			while(it.hasNext()){
				Huskelapp h = new Huskelapp();
				h = (Huskelapp)it.next();
				%>
				<div class="huskelapp">
				<% 
				out.print("<div class='huskelappHeader'>" + h.getTittel() + "<br/></div>");
				out.print("<div class='huskelappBody'>" + h.getInnhold() + "<br/></div>");
				out.print("<div class='huskelappBody'>" + h.getBrukernavn() + "<br/></div>");
				%>
				</div>
				<p>
				<%
			}
		%>
	</body>
</html>