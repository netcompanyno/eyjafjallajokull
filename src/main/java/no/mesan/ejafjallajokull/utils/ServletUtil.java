/**
 * 
 */
package no.mesan.ejafjallajokull.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * H�ndterer fellesfunksjonalitet for servletene.
 * @author helino
 *
 */
public class ServletUtil {
	private static Connection con;
	
	public static Connection initializeDBCon() {
		 try
		  {
	        Class.forName("org.sqlite.JDBC");
		  }catch(ClassNotFoundException e){
			  System.out.println("JDBC for SQLITE feil : " + e.getMessage());
		  }
		  
		try {
			con = DriverManager.getConnection("jdbc:sqlite:src/main/resources/secret.db");
		} catch (SQLException e) {
			System.out.println("JDBC fikk ikke kontakt med databasen : " + e.getMessage());
		}
		return con;
		
	}
	
	/**
	 * Lukker resultset og databasekobling.
	 * @param rs
	 * @param con
	 */
	public static void CleanupDBConn(ResultSet rs, Connection con){
		try {
			if(!rs.isClosed()){
				rs.close();
			}
			if(!con.isClosed()){
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Feil ved closing av connection og resultset : " + e.getMessage());
		}
	}
	
	/**
	 * Lukker databasekobling
	 * @param con2
	 */
	public static void CleanupDBConn(Connection con2) {
		try {
			if(!con.isClosed()){
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Feil ved closing av connection : " + e.getMessage());
		}
	}
	
	/**
	 * Navigerer til feilside for visning av feilmelding.
	 * @param request
	 * @param response 
	 * @return 
	 */
	public static void gotoFeilSide(HttpServletRequest request, HttpServletResponse response) {
		String nextJSP = "/feilside.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
		try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * Konverterer sjekkboks verdi til integer verdi.
	 * @param parameter
	 * @return
	 */
	public static int convertCheckBox(String parameter) {
		if(parameter != null && !parameter.equals("") && parameter.equals("on")){
			return 1;
		}
		return 0;
	}
	
	

}
