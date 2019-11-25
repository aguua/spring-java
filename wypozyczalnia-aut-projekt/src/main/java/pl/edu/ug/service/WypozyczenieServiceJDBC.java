package pl.edu.ug.service;

import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.edu.ug.domain.Auto;
import pl.edu.ug.domain.Klient;
import pl.edu.ug.domain.Wypozyczenie;

public class WypozyczenieServiceJDBC implements WypozyczenieService{

	private final String DB_URL = "jdbc:hsqldb:hsql://localhost/wypozyczalnia";
	 private final String CREATE_TABLE = "CREATE TABLE WYPOZYCZENIA(\r\n" + 
		    		"id_wypozyczenie int identity not null,\r\n" + 
		    		"data_wypozyczenia date not null,\r\n" + 
		    		"data_zwrotu date null,\r\n" + 
		    		"id_klient int not null,\r\n" + 
		    		"nr_rejestracyjny varchar(10) not null,\r\n" + 
		    		"primary key (id_wypozyczenie)\r\n" + 
		    		")";
	  private final String INSERT = "INSERT INTO wypozyczenia(id_wypozyczenie, data_wypozyczenia, data_zwrotu, id_klient, nr_rejestracyjny) VALUES (?,?,?,?,?)";
	  private final String SELECT= "select w.id_wypozyczenie, w.nr_rejestracyjny,a.model, a.marka,a.typ_paliwa, a.cena_wyp, k.id_klient, k.imie, k.nazwisko, k.pesel "+
				"			w.data_wypozyczenia, w.data_zwrotu from\r\n" + 
				"			wypozyczenia w, auta a, klienci k \r\n" + 
				"			where w.nr_rejestracyjny=a.nr_rejestracyjny and k.id_klient = w.id_klient";
	  private final String DELETE = "DELETE FROM WYPOZYCZENIA WHERE id_wypozyczenie=?";
	  private final String UPDATE = "UPDATE WYPOZYCZENIA set data_wypozyczenia=?, data_zwrotu=?, nr_rejestracyjny=?, id_klient=? where id_wypozyczenie=?";

	  private Connection connection;

	  private PreparedStatement insertPST;
	  private PreparedStatement selectPST;
	  private PreparedStatement updatePST;
	  private PreparedStatement deletePST;
	  
	  public WypozyczenieServiceJDBC() {
		  try{
			  
		  connection = DriverManager.getConnection(DB_URL);
		  ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
	      connection.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
	      boolean tableExists = false;

	      while (rs.next()) {
	        if ("Wypozyczenia".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
	          tableExists = true;
	          break;
	        }
	      }

	      if (!tableExists) {
	        PreparedStatement createTablePST = connection.prepareStatement(CREATE_TABLE);
	        Statement st_new = connection.createStatement();
	        createTablePST.executeUpdate();
			st_new.executeUpdate("ALTER TABLE wypozyczenia ADD FOREIGN KEY (id_klient) references KLIENCI(id_klient)");
			st_new.executeUpdate("ALTER TABLE wypozyczenia ADD FOREIGN KEY (nr_rejestracyjny) references AUTA(nr_rejestracyjny)");
	        
	      }

	      insertPST = connection.prepareStatement(INSERT);
	      selectPST = connection.prepareStatement(SELECT);
	      updatePST = connection.prepareStatement(UPDATE);
	      deletePST = connection.prepareStatement(DELETE);
	      
	      
	      
		  }catch (SQLException e) {
		        e.printStackTrace();
		        }
	}
	  
	  public void addWypozyczenie(Wypozyczenie wypozyczenie) {
		  try {
			  insertPST.setInt(1, wypozyczenie.getid_wypozyczenie());
			  insertPST.setString(2, wypozyczenie.getData_wypozyczenia());
			  insertPST.setString(3,wypozyczenie.getData_zwrotu());
			  insertPST.setLong(4, wypozyczenie.getKlient().getId_klient());
			  insertPST.setString(5,  wypozyczenie.getAuto().getNr_rejestracyjny());
			  
			  
			  insertPST.executeUpdate();
			  
		  }catch (SQLException e) {
		      e.printStackTrace();
		      }
	  }
	  
	  public void updateWypozyczenie( String data_w, String data_z, String nr_r, int id_klient, int id_wyp) {
			try {
				updatePST.setString(1, data_w);
				updatePST.setString(2,data_z);
				updatePST.setInt(3, id_klient);
				updatePST.setString(4, nr_r);
				updatePST.setInt(5,id_wyp);
				updatePST.executeUpdate();

			} catch (SQLException e) {
			      e.printStackTrace();
			      }	
	}
	  
		public void deleteWypozyczenie(int id) {
			try {
				deletePST.setInt(1, id);
				deletePST.executeUpdate();
				
			}catch (SQLException e) {
			      e.printStackTrace();
			      }
		}
		
	  public List<Wypozyczenie> getWypozyczenia() {

		    List<Wypozyczenie> result = new ArrayList<Wypozyczenie>();

		    try {
		      ResultSet rs = selectPST.executeQuery();

		      while (rs.next()) {
		    	    // daty
		    	    int id = rs.getInt("id_wypozyczenie");
		    	    String data_wypozyczenia = rs.getString("data_wypozyczenia");
		    	    String data_zwrotu = rs.getString("data_zwrotu");
		    	  	//klient
		    	    int id_klienta = rs.getInt("id_klient");
		    	    String imie = rs.getString("imie");
			    	String nazwisko = rs.getString("nazwisko");
			    	String pesel = rs.getString("pesel");
			    	//auto
			    	String nr_rej = rs.getString("nr_rejestracyjny");
				    String marka = rs.getString("marka");
				    String model = rs.getString("model");
				    String paliwo = rs.getString("typ_paliwa");
				    double cena = rs.getDouble("cena_wyp");

				    Auto auto =  new Auto(nr_rej, marka, model, paliwo, cena);
				    Klient klient = new Klient(id_klienta, imie, nazwisko, pesel);
				    Wypozyczenie wypozyczenie = new  Wypozyczenie(id, data_wypozyczenia, data_zwrotu, klient, auto);
				    		
		        result.add(wypozyczenie);
		      }


		    } catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return result;
		  }
	  
		public void dropWypozyczenia() {
			boolean tableExists = false;
			try{
				ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			
		      while (rs.next()) {
		        if ("Wypozyczenia".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
		          tableExists = true;
		          break;
		        }
		      }
		      
		      if(tableExists) {
		        PreparedStatement dropTablePST = connection.prepareStatement("DROP TABLE WYPOZYCZENIA CASCADE");
		        dropTablePST.executeUpdate();
		      }
			} catch (SQLException e) {
		        e.printStackTrace();
		        }
		}


	  
}
