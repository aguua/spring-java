package pl.edu.ug;

import java.net.ConnectException;
import java.sql.*;

public class App {

   
    final String CREATE_TABLE_OSOBA = "create table osoba (nr int, imie varchar(30), nazwisko varchar(30), waga double)";
    final String SELECT_ALL_OSOBA = "SELECT * FROM OSOBA";
    final String INSERT_OSOBA = "INSERT INTO OSOBA VALUES";
    final String UPDATE_OSOBA_WAGA = "UPDATE OSOBA SET WAGA =";

    
	public static Connection GetConnection()  throws SQLException {
		final String DB_URL = "jdbc:hsqldb:hsql://localhost/workdb";
		try{
			DriverManager.getConnection(DB_URL);
		}catch (SQLException e) {
	        e.printStackTrace();
		}
		return DriverManager.getConnection(DB_URL);
	}
	
	public static void CreateTable(Connection connection)  throws SQLException{
	    PreparedStatement pstmt_create = connection.prepareStatement("create table osoba "
	    		+ "(nr int, imie varchar(30), nazwisko varchar(30), waga double)");
		    pstmt_create.executeUpdate();
		    pstmt_create.close();
	}
	
	public static void InsertInto(Connection connection, int numer, String imie, String nazwisko, double waga) throws SQLException{

			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO OSOBA VALUES (?,?,?,?)");
			pstmt.setInt(1, numer);
			pstmt.setString(2, imie);
			pstmt.setString(3, nazwisko);
			pstmt.setDouble(4, waga);
			pstmt.executeUpdate();
			pstmt.close();
	}
	
	public static void DropTable(Connection connection) throws SQLException{
	    final String DROP_TABLE = "DROP TABLE OSOBA";
	    PreparedStatement pstmt_drop = connection.prepareStatement(DROP_TABLE);
		pstmt_drop.executeUpdate();
	    pstmt_drop.close();
	}
	
	public static void ReadAllData(Connection connection) throws SQLException {
		PreparedStatement pstmt_select = connection.prepareStatement("SELECT * FROM OSOBA");
		ResultSet rs_prepared = pstmt_select.executeQuery();
	    while (rs_prepared.next()) {
	    	int nr = rs_prepared.getInt("nr");
	    	String imie = rs_prepared.getString("imie");
	    	String nazwisko = rs_prepared.getString("nazwisko");
	    	double waga = rs_prepared.getDouble("waga");
	    	  System.out.println(nr + " - " + imie+" - "+nazwisko + " - " + waga+ "\n");
	    	}
	    pstmt_select.close();
	}
	

		
	
  public static void main(String[] args) throws SQLException {

	  		Connection con = GetConnection();
	  		//CreateTable(con);
	  		InsertInto(con, 10, "Maria", "Darosz", 54.2);
	  		ReadAllData(con);

	  		DropTable(con);
	  		con.close();
	  		
	  		

  } 
    
     
  }
 