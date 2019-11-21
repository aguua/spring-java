package pl.edu.ug;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
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
	
	
    public static void main( String[] args ) throws SQLException
    {
        System.out.println( "Hello World!" );
        Connection con = GetConnection();
    }
}
