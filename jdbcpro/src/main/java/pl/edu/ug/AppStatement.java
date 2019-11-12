package pl.edu.ug;

import java.net.ConnectException;
import java.sql.*;

public class AppStatement {

  public static void main(String[] args) throws SQLException {

    final String DB_URL = "jdbc:hsqldb:hsql://localhost/workdb";
    final String CREATE_TABLE_OSOBA = "create table osoba (nr int, imie varchar(30), nazwisko varchar(30), waga double)";
    final String SELECT_ALL_OSOBA = "SELECT * FROM OSOBA";
    final String INSERT_OSOBA = "INSERT INTO OSOBA VALUES";
    final String UPDATE_OSOBA_WAGA = "UPDATE OSOBA SET WAGA =";
    final String DROP_TABLE = "DROP TABLE OSOBA";
    
 // 1 implemantacja operacji CRUD do utworzonej tabeli z użyciem interfejsu Statement.
    try {
	    Connection connection = DriverManager.getConnection(DB_URL);
	     
	    //CREATE TABLE 
	    Statement st_new = connection.createStatement();
	    ResultSet rs_new = connection.getMetaData().getTables(null, null, null, null);
	
	    boolean tableExists = false;
	
	    while (rs_new.next()) {
	      if ("Osoba".equalsIgnoreCase(rs_new.getString("TABLE_NAME"))) {
	        tableExists = true;
	        break;
	      }
	    }
	
	    if (!tableExists)
	      st_new.executeUpdate(CREATE_TABLE_OSOBA);
	    
	    //INSERT 
	    Statement st_insert = connection.createStatement();
	    st_insert.executeQuery(INSERT_OSOBA + "(1, 'Agnieszka', 'Graj', 55.1), (2, 'Paweł', 'Witkowski', 87.21), (3, 'Maja', 'Swatek', 43.2)");
	    
	    //READ DATA FROM TABLE
	    
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(SELECT_ALL_OSOBA);
	    ResultSetMetaData rsmd = rs.getMetaData();
	
	    int columnsNumber = rsmd.getColumnCount();
	    while (rs.next()) {
	    	int nr = rs.getInt("nr");
	    	String imie = rs.getString("imie");
	    	String nazwisko = rs.getString("nazwisko");
	    	double waga = rs.getDouble("waga");
	    	  System.out.println(nr + "  | " +imie+" | "+nazwisko+ " | "+ waga+ "\n");
	    	}
	    
	    
	    //UPDATE TABLE
	    Statement st_up = connection.createStatement();
	    st_up.executeQuery(UPDATE_OSOBA_WAGA+ "50 where imie = 'Maja'");
	
	    //DROP TABLE
	    //Statement st_drop = connection.createStatement();
	    //ResultSet rs_drop = st_drop.executeQuery(DROP_TABLE);
	    
	    
	    //PreparedStatement 
	    /*
	    //CREATE
	    PreparedStatement pstmt_create = connection.prepareStatement("create table osoba "
	    		+ "(nr int, imie varchar(30), nazwisko varchar(30), waga double)");

		    pstmt_create.executeUpdate();
		    pstmt_create.close();
		 */ 	
	    
	    //INSERT
	    PreparedStatement pstmt = connection.prepareStatement("INSERT INTO OSOBA VALUES (?,?,?,?)");
	    		pstmt.setInt(1, 8);
	    		pstmt.setString(2, "Marek");
	    		pstmt.setString(3, "Czarek");
	    		pstmt.setDouble(4, 87.22);
	    		pstmt.executeUpdate();
	    		pstmt.close();
	    
	    //DROP
	    /*
	      PreparedStatement pstmt_drop = connection.prepareStatement(DROP_TABLE);
	     
	    		pstmt_drop.executeUpdate();
	    		pstmt_drop.close();
	    */

	   //READ DATA FROM TABLE 
	    PreparedStatement pstmt_select = connection.prepareStatement("SELECT * FROM OSOBA");
	    		ResultSet rs_prepared = pstmt_select.executeQuery();
			    while (rs_prepared.next()) {
			    	int nr = rs_prepared.getInt("nr");
			    	String imie = rs_prepared.getString("imie");
			    	double waga = rs_prepared.getDouble("waga");
			    	  System.out.println(nr + " - " + imie+" - "+ waga+ "\n");
			    	}
			    pstmt_select.close();
	    		
    } catch (SQLException e) {
        e.printStackTrace();
    }
    		


     
  }
 
}
