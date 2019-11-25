package pl.edu.ug.service;

import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.ug.domain.Auto;

public class AutoServiceJDBC implements AutoService{

	 private final String DB_URL = "jdbc:hsqldb:hsql://localhost/wypozyczalnia";
	 private final String CREATE_TABLE = "CREATE TABLE AUTA(\r\n" + 
	    		"nr_rejestracyjny varchar(10) not null,\r\n" + 
	    		"marka nvarchar(30),\r\n" + 
	    		"model nvarchar(30),\r\n" + 
	    		"typ_paliwa nvarchar(30),\r\n" + 
	    		"cena_wyp decimal,\r\n" + 
	    		"primary key (nr_rejestracyjny)\r\n" + 
	    		")";
	  private final String INSERT = "INSERT INTO auta VALUES (?,?,?,?,?)";
	  private final String SELECT= "SELECT * FROM Auta";
	  private final String UPDATE = "UPDATE AUTA SET marka=?, model=?, typ_paliwa=?, cena_wyp=? WHERE nr_rejestracyjny=?";
	  private final String DELETE = "DELETE FROM AUTA WHERE nr_rejestracyjny =?";
		
		

	  private Connection connection;

	  private PreparedStatement insertPST;
	  private PreparedStatement selectPST;
	  private PreparedStatement updatePST;
	  private PreparedStatement deletePST;
	  
		
	  public AutoServiceJDBC() {
		  try{
			  
		  connection = DriverManager.getConnection(DB_URL);
		  ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
	      connection.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
	      boolean tableExists = false;

	      while (rs.next()) {
	        if ("Auta".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
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
	  
	public void updateAuto(  String marka, String model, String typ_paliwa, double cena_wyp, String nr_stary) {
			try {
				updatePST.setString(1, marka);
				updatePST.setString(2, model);
				updatePST.setString(3, typ_paliwa);
				updatePST.setDouble(4, cena_wyp);
				updatePST.setString(5, nr_stary);
				updatePST.executeUpdate();

			} catch (SQLException e) {
			      e.printStackTrace();
			      }	
	}
	
	public void deleteAuto(String nr_rejestracyjny) {
		try {
			deletePST.setString(1, nr_rejestracyjny);
			deletePST.executeUpdate();
			
		}catch (SQLException e) {
		      e.printStackTrace();
		      }
	}
	  
	  public void addAuto(Auto auto) {
		  try {
			  insertPST.setString(1, auto.getNr_rejestracyjny());
			  insertPST.setString(2, auto.getMarka());
			  insertPST.setString(3, auto.getModel());
			  insertPST.setString(4, auto.getTyp_paliwa());
			  insertPST.setDouble(5, auto.getCena());
			  
			  insertPST.executeUpdate();
			  
		  }catch (SQLException e) {
		      e.printStackTrace();
		      }
	  }
	  
	  public List<Auto> getAuta() {

		    List<Auto> result = new ArrayList<Auto>();

		    try {
		      ResultSet rs = selectPST.executeQuery();

		      while (rs.next()) {
		    	  String nr = rs.getString("nr_rejestracyjny");
			    	String marka = rs.getString("marka");
			    	String model = rs.getString("model");
			    	String paliwo = rs.getString("typ_paliwa");
			    	double cena = rs.getDouble("cena_wyp");

			    Auto auto =  new Auto(nr, marka, model, paliwo, cena);

		        result.add(auto);
		      }


		    } catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return result;
		  }
	  
		public void dropAuta() {
			boolean tableExists = false;
			try{
				ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			
		      while (rs.next()) {
		        if ("Auta".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
		          tableExists = true;
		          break;
		        }
		      }
		      
		      if(tableExists) {
		        PreparedStatement dropTablePST = connection.prepareStatement("DROP TABLE AUTA CASCADE");
		        dropTablePST.executeUpdate();
		      }
			} catch (SQLException e) {
		        e.printStackTrace();
		        }
		}
	  

	  

}
