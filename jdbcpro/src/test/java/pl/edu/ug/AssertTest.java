package pl.edu.ug;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import org.hamcrest.core.CombinableMatcher;
import org.junit.Test;

public class AssertTest {

	  @Test
	  public void testGetConnection() throws SQLException {
	    Connection con = App.GetConnection();
	    assertTrue(con instanceof Connection);
	    assertNotNull(con);
	    con.close();

	  }

	  @Test
	  public void testCreateTable()throws SQLException  {
		  Connection con = App.GetConnection();
		  App.CreateTable(con);
		  DatabaseMetaData meta = con.getMetaData();
		  ResultSet res = meta.getTables(null, null, "osoba", null);
		 /// while res.next(){
			//  to do
		  ///}
		  
		  
		  
		  con.close();
		  
		  
		  
		}
}