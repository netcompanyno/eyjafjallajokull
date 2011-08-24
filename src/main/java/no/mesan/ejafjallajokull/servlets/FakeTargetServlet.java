package no.mesan.ejafjallajokull.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FakeTargetServlet extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		PrintWriter w = response.getWriter();
		w.write("<html><table><tr><td>fake destination for logins..</td><td>Denne siden kunne enkelt ha tilhørt en angriper!</td></tr>");
		w.write("<tr><td>brukernavn:</td><td>"+request.getParameter("brukerid")+"</td></tr>");
		w.write("<tr><td>Passord:</td><td>"+request.getParameter("passord")+"</td></tr></table></html>");
		
		w.close();
	}
}
