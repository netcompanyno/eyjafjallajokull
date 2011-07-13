package no.mesan.ejafjallajokull.test;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;



public class TestDbUnit extends DatabaseTestCase {

    public static final String TABLE_NAME = "bruker";

    private FlatXmlDataSet loadedDataSet;
	/**
     * Provide a connection to the database
     */
    protected IDatabaseConnection getConnection() throws Exception
    {
        Class driverClass = Class.forName("org.sqlite.JDBC");
        Connection jdbcConnection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/secret.db");
        return new DatabaseConnection(jdbcConnection);
    }

    /**
     * Load the data which will be inserted for the test
     */
	protected IDataSet getDataSet() throws Exception
    {
		FileInputStream file = new FileInputStream("fulldataset.xml");
		loadedDataSet = new FlatXmlDataSetBuilder().build(file);
		return loadedDataSet;
    }


	//Tester at vi har fire registrerte brukere
	public void testCheckDataLoaded() throws Exception{
		 assertNotNull(loadedDataSet);
	        int rowCount = loadedDataSet.getTable(TABLE_NAME).getRowCount();
	        assertEquals(4, rowCount);
		
	}

}
