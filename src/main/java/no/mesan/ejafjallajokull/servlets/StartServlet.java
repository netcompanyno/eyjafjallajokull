package no.mesan.ejafjallajokull.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.mesan.ejafjallajokull.pojo.Huskelapp;
import no.mesan.ejafjallajokull.utils.ServletUtil;

/**
 * Henter ut alle offentlige huskelapper og forwarder til loginside.
 */
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartServlet() {
		super();
	}

	/**
	 * Henter ut alle offentlige huskelapper slik at de kan listes ut p�
	 * loginsiden
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("loggedIn") != null) {
			request.getSession().setAttribute("loggedIn", false);
		}
		
		ArrayList<Huskelapp> huskelappListe = new ArrayList<Huskelapp>();
		Connection con = ServletUtil.initializeDBCon();
		ResultSet rs = null;
		String SQL = "SELECT * FROM HUSKELAPP H,  BRUKER B where H.EROFFENTLIG=1 AND B.BRUKERNAVN=H.BRUKERNAVN";
		try {
			ServletUtil.beginTransaction(con);
			Statement stm = con.createStatement();
			rs = stm.executeQuery(SQL);
			while (rs.next()) {
				Huskelapp huskelapp = new Huskelapp(rs.getString(1), rs.getString(2), rs.getString(9), rs.getLong(6));
				huskelappListe.add(huskelapp);
			}
			ServletUtil.endTransaction(con);
			
			request.setAttribute("huskelapper", huskelappListe);

			String nextJSP = "/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			ServletUtil.rollbackTransaction(con);
			request.setAttribute("feilmelding", "Kunne ikke hente ut huskelapper : " + e.getMessage());
			ServletUtil.gotoFeilSide(request, response);
		} finally {
			ServletUtil.cleanupTransaction(con);
			ServletUtil.cleanupDBConn(rs, con);	
		}
	}

}