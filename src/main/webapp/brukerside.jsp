<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="no.mesan.ejafjallajokull.pojo.Huskelapp"%>
<jsp:useBean id="huskelapper" type="ArrayList<Huskelapp>" scope="request" />
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<title>Brukerside</title>
</head>
<body>
	<div class="greyboxBruker">
		<div class="welcomeMessage">
			Velkommen,
			<%=session.getAttribute("navn") %>!
		</div>
		<div class="logoutMessage">
			<a href="index.jsp">Logg ut</a>
		</div>
		 <br /> <br /> <br /> Lage ny huskelapp:
		<p>
		<div align="center" class="nyhuskelapp">
			<form method="POST" action=huskelapp>
				<table class="noborderTable">
					<tr>
						<td>Tittel:</td>
						<td><input type="text" name="tittel" size="17">
						</td>
					</tr>
					<tr>
						<td style="vertical-align: top">Innhold:</td>
						<td><textarea rows="10" cols="50" name="innhold"> </textarea>
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap">Er offentlig</td>
						<td style="text-align: left"><input type="checkbox"
							name="erOffentlig">
						</td>
					</tr>


					<tr>
						<td align="right" colspan="2"><input type="submit"
							value="Legg til">
						</td>

					</tr>
				</table>
			</form>
		</div>
		<br /> <br /> <br />
		<%
			if(((Boolean)session.getAttribute("erAdmin")).booleanValue()){
				out.print("Følgende huskelapper er registrert i hele systemet fra alle brukere:");
			}else{
				out.print("Følgende huskelapper er registrert:");
			}
		%>
		<br /> <br />
		<% 
			Iterator<Huskelapp> it = huskelapper.iterator();
			
			while(it.hasNext()){
				Huskelapp h = new Huskelapp();
				h = (Huskelapp)it.next();
				%>
		<div class="huskelapp">
			<% 
						out.print("<div class='huskelappHeader'>");
				%>
			<form method="POST" action="huskelapp">
				<input type="submit" value="x">
				<%=h.getTittel() %>
				<input type="hidden" id="tittel" value="<%=h.getTittel()%>"
					name="tittel"> <input type="hidden"
					value="<%=h.getBrukernavn() %>" name="brukernavn"> <input
					type="hidden" value="<%=h.getTimestamp()%>" name="timestamp">
				<input type="hidden" value="sletthuskelapp" name="sletthuskelapp">
			</form>
			<% 
						out.print("<br/></div>");
						out.print("<div class='huskelappBody'>" + h.getInnhold() + "<br/>");
						if(((Boolean)session.getAttribute("erAdmin")).booleanValue()){
							out.print("<b>Eier: " + h.getBrukernavn() + "</b>");
						}
				%>
		</div>
	</div>
	<p>
		<%
			}
		%>
	
	</div>
</body>
</html>