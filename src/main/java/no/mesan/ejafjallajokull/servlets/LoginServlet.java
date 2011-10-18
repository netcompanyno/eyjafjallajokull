package no.mesan.ejafjallajokull.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.mesan.ejafjallajokull.pojo.Huskelapp;
import no.mesan.ejafjallajokull.utils.ServletUtil;


/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();

    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String brukerid = request.getParameter("brukerid");
		String passord = request.getParameter("passord");
		//hvis ikke innlogget, finn bruker, finn huskelapper.
		if( request.getSession().getAttribute("loggedIn") == null || !((Boolean)request.getSession().getAttribute("loggedIn")).booleanValue() ){
			String sql = "SELECT * FROM BRUKER WHERE BRUKERNAVN='" + brukerid + "' AND PASSORD='" + passord + "'";
			Connection con = null;
			ResultSet rs = null;
			try {
				con = ServletUtil.initializeDBCon();
				
				ServletUtil.beginTransaction(con);
				Statement stm = con.createStatement();
				rs = stm.executeQuery(sql);
				if (rs.next()) {
					out.println("Bruker " + rs.getString("navn") + " er logget inn, gratulerer!");
					request.getSession().setAttribute("navn", rs.getString("navn"));
					request.getSession().setAttribute("brukernavn", rs.getString("brukernavn"));
					request.getSession().setAttribute("erAdmin", convertIntToBoolean(rs.getInt("eradmin")));
					request.getSession().setAttribute("loggedIn", true);
					request.setAttribute("huskelapper", getHuskelapper(brukerid, (Boolean)request.getSession().getAttribute("erAdmin")));
				} else {
					request.setAttribute("feilmelding", "Bruker id og/eller passord var feil.");
					ServletUtil.gotoFeilSide(request, response);
				
				}
				ServletUtil.endTransaction(con);
			} catch (SQLException e) {
				ServletUtil.rollbackTransaction(con);
				request.setAttribute("feilmelding", "Innloggingssjekk mot databasen feilet med følgende melding : " + e.getMessage());
				ServletUtil.gotoFeilSide(request, response);
			} finally {
				ServletUtil.cleanupTransaction(con);
				ServletUtil.cleanupDBConn(rs, con);
			}
		}else{
			//Bruker allerede innlogget, oppdater huskelapplisten.
			request.setAttribute("huskelapper", getHuskelapper(request.getSession().getAttribute("brukernavn").toString(),
																(Boolean)request.getSession().getAttribute("erAdmin") ));
		}
		String nextJSP = "/brukerside.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}
	
	private Boolean convertIntToBoolean(int int1) {
		if(int1 == 1){
			return new Boolean(true);
		};
		
		return new Boolean(false);
	}

	

	/**
	 * Henter ut alle huskelappene til en bruker, om brukeren er admin, hentes alle huskelapper
	 * @param <Huskelapp>
	 * @param brukerid
	 * @return
	 */
	private List<Huskelapp> getHuskelapper(String brukerid, Boolean erAdmin) {
		ArrayList<Huskelapp> huskelappListe = new ArrayList<Huskelapp>();
		String SQL = "";
		if(erAdmin.booleanValue()){
			SQL = "SELECT * FROM HUSKELAPP H ORDER BY TIMESTAMP DESC";
		}else{
			SQL = "SELECT TITTEL, INNHOLD, ERUTFORT, EROFFENTLIG, H.BRUKERNAVN, TIMESTAMP "+//, B.BRUKERNAVN AS BRUKERID " +
				  "FROM HUSKELAPP H, BRUKER B WHERE H.BRUKERNAVN = B.BRUKERNAVN AND B.BRUKERNAVN='" + brukerid + "' and H.BRUKERNAVN='" + brukerid + 
				  "' ORDER BY TIMESTAMP DESC";
		}
		Connection con = null;
		ResultSet rs = null;
		try {
			System.out.println(SQL);
			con = ServletUtil.initializeDBCon();
			ServletUtil.beginTransaction(con);
			Statement stm = con.createStatement();
			rs = stm.executeQuery(SQL);
			while (rs.next()) {
				Huskelapp huskelapp = new Huskelapp(rs.getString("tittel"),rs.getString("innhold"),rs.getString("brukernavn"),rs.getLong("timestamp"));
				huskelappListe.add(huskelapp);
				System.out.println("h");
			} 
			ServletUtil.endTransaction(con);
		} catch (SQLException e) {
		     ServletUtil.rollbackTransaction(con);
			 System.out.println("Kunne ikke hente ut huskelapper for bruker : " + e.getMessage());
		} finally {
			ServletUtil.cleanupTransaction(con);
			ServletUtil.cleanupDBConn(rs, con);
		}
		
	
		return huskelappListe;
	}
}
