package pl.edu.ug.service;

import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.ug.domain.Klient;

public class KlientServiceJDBC implements KlientService {

	private final String DB_URL = "jdbc:hsqldb:hsql://localhost/wypozyczalnia";
	 private final String CREATE_TABLE = " CREATE TABLE KLIENCI(\r\n" + 
	    		"id_klient int  identity not null,\r\n" + 
	    		"imie nvarchar(50),\r\n" + 
	    		"nazwisko nvarchar(50),\r\n" + 
	    		"pesel varchar(11),\r\n" + 
	    		"primary key (id_klient)\r\n" + 
	    		")";
	  private final String INSERT = "INSERT INTO klienci(imie, nazwisko, pesel) VALUES (?,?,?)";
	  private final String SELECT= "SELECT * FROM klienci";
	  private final String UPDATE = "UPDATE KLIENCI SET imie=?, nazwisko=?, pesel=? where id_klient=?";
	  private final String DELETE = "DELETE FROM KLIENCI WHERE id_klient =?";

	  private Connection connection;

	  private PreparedStatement insertPST;
	  private PreparedStatement selectPST;
	  private PreparedStatement updatePST;
	  private PreparedStatement deletePST;
	  
	  public KlientServiceJDBC() {
		  try{
			  
		  connection = DriverManager.getConnection(DB_URL);
		  ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
	      connection.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
	      boolean tableExists = false;

	      while (rs.next()) {
	        if ("Klienci".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
	          tableExists = true;
	          break;
	        }
	      }

	      if (!tableExists) {
	        PreparedStatement createTablePST = connection.prepareStatement(CREATE_TABLE);
	        createTablePST.executeUpdate();
	      }

	      insertPST = connection.prepareStatement(INSERT);
	      selectPST = connection.prepareStatement(SELECT);
	      updatePST = connection.prepareStatement(UPDATE);
	      deletePST = connection.prepareStatement(DELETE);

		  }catch (SQLException e) {
		        e.printStackTrace();
		        }
	}
	  
	  public void addKlient(Klient klient) {
		  try {
			  insertPST.setString(1, klient.getImie());
			  insertPST.setString(2,klient.getNazwisko());
			  insertPST.setString(3, klient.getPesel());
			  
			  insertPST.executeUpdate();
			  
		  }catch (SQLException e) {
		      e.printStackTrace();
		      }
	  }
		public void deleteKlient(int id) {
			try {
				deletePST.setInt(1, id);
				deletePST.executeUpdate();
				
			}catch (SQLException e) {
			      e.printStackTrace();
			      }
		}
		
		public void updateKlient( String imie, String nazwisko, String pesel, int id_stary) {
			try {
				updatePST.setString(1, imie);
				updatePST.setString(2, nazwisko);
				updatePST.setString(3, pesel);
				updatePST.setInt(4, id_stary);
				updatePST.executeUpdate();

			} catch (SQLException e) {
			      e.printStackTrace();
			      }	
	}
		
	  public List<Klient> getKlienci() {

		    List<Klient> result = new ArrayList<Klient>();

		    try {
		      ResultSet rs = selectPST.executeQuery();

		      while (rs.next()) {
		    	    int nr = rs.getInt("id_klient");
		    	    String imie = rs.getString("imie");
			    	String nazwisko = rs.getString("nazwisko");
			    	String pesel = rs.getString("pesel");


			    Klient klient = new Klient(nr, imie,nazwisko, pesel);

		        result.add(klient);
		      }


		    } catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return result;
		  }
	  
		public void dropKlienci() {
			boolean tableExists = false;
			try{
				ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			
		      while (rs.next()) {
		        if ("Klienci".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
		          tableExists = true;
		          break;
		        }
		      }
		      
		      if(tableExists) {
		        PreparedStatement dropTablePST = connection.prepareStatement("DROP TABLE KLIENCI CASCADE");
		        dropTablePST.executeUpdate();
		      }
			} catch (SQLException e) {
		        e.printStackTrace();
		        }
		}
}
