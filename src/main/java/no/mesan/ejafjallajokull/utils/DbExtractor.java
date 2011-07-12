/**
 * 
 */
package no.mesan.ejafjallajokull.utils;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 * @author oleh
 * 
 * Dumper en database til xml, for bruk i DBUnit.
 *
 */
public class DbExtractor {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        IDatabaseConnection connection = new DatabaseConnection(ServletUtil.initializeDBCon());

        // Perform full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("fulldataset.xml"));
 	}

}
