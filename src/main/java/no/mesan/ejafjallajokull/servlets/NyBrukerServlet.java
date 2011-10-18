package no.mesan.ejafjallajokull.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.mesan.ejafjallajokull.utils.ServletUtil;

@SuppressWarnings("serial")
public class NyBrukerServlet extends HttpServlet {

	public NyBrukerServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextJSP = "/ny.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String navn = request.getParameter("navn");
		String brukerid = request.getParameter("brukerid");
		String passord = request.getParameter("passord");
		
		String sql = "INSERT INTO bruker VALUES('" + brukerid + "', '" + passord + "', '" + navn + "', '" + 0 + "')";
		System.out.println(sql);
		Connection con = null;
		try {
			con = ServletUtil.initializeDBCon();
			ServletUtil.beginTransaction(con);
			Statement  stm = con.createStatement();
			stm.executeUpdate(sql);
			ServletUtil.endTransaction(con);
		} catch (Exception e) {
			ServletUtil.rollbackTransaction(con);
			request.setAttribute("feilmelding", "Kunne ikke registrere bruker: " + e.getMessage());
			e.printStackTrace();
			ServletUtil.gotoFeilSide(request, response);
		} finally {
			ServletUtil.cleanupTransaction(con);
			ServletUtil.cleanupDBConn(con);
		}
		String nextJSP = "/index.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
		
	}
}
