package pl.edu.ug.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class WypozyczalniaJDBC 
{
    
	public static Connection GetConnection()  throws SQLException {
		final String DB_URL = "jdbc:hsqldb:hsql://localhost/wypozyczalnia";
		try{
			DriverManager.getConnection(DB_URL);
		}catch (SQLException e) {
	        e.printStackTrace();
		}
		return DriverManager.getConnection(DB_URL);
	}
	public static boolean TableExists(Connection connection, String table_name)  throws SQLException{
		Statement st_new = connection.createStatement();
	    ResultSet rs_new = connection.getMetaData().getTables(null, null, null, null);	
	    while (rs_new.next()) {
	      if (table_name.equalsIgnoreCase(rs_new.getString("TABLE_NAME"))) {
	        //System.out.println( "Table " + table_name + " already exists!" );
	        return true; 
	      }
	    }
	    return false;
	}

	
	public static void DropTable(Connection connection, String table_name) throws SQLException{
	    Statement st_drop = connection.createStatement();
	    if(TableExists(connection, table_name))
	    	st_drop.executeQuery("DROP TABLE " + table_name + " CASCADE");
	}
	
	public static void DropAllTables(Connection connection) throws SQLException{    
	    DropTable(connection, "wypozyczenia");
	    DropTable(connection, "klienci");
	    DropTable(connection, "auta");    
	    System.out.println( "All dropped." );
	}
	
}
