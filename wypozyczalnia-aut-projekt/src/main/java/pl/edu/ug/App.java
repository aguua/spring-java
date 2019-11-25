package pl.edu.ug;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
	public static void CreateTableAdresy(Connection connection)  throws SQLException{
		Statement st_new = connection.createStatement();
		if (!TableExists(connection, "adresy")) {
			st_new.executeUpdate("CREATE TABLE ADRESY(\r\n" + 
					"			id_adres int identity not null,\r\n" + 
					"			ulica nvarchar(50),\r\n" + 
					"			kod_pocztowy nvarchar(10),\r\n" + 
					"			miasto nvarchar(50),\r\n" + 
					"			primary key (id_adres)\r\n" + 
					"			)");
		}
	}

	public static void CreateTableKlienci(Connection connection)  throws SQLException{
		Statement st_new = connection.createStatement();
		if (!TableExists(connection, "klienci")) {
			st_new.executeUpdate("CREATE TABLE KLIENCI(\r\n" + 
	    		"id_klient int  identity not null,\r\n" + 
	    		"imie nvarchar(50),\r\n" + 
	    		"nazwisko nvarchar(50),\r\n" + 
	    		"pesel varchar(11),\r\n" + 
	    		"id_adres int,\r\n" +
	    		"primary key (id_klient)\r\n" + 
	    		")");
		} 
		st_new.executeUpdate("ALTER TABLE klienci ADD FOREIGN KEY  (id_adres) references ADRESY(id_adres)");
	}
	
	public static void CreateTablePracownicy(Connection connection)throws SQLException{
		Statement st_new = connection.createStatement();
		if (!TableExists(connection, "pracownicy")) {
			st_new.executeUpdate("CREATE TABLE PRACOWNICY(\r\n" + 
	    		"id_pracownik int identity not null,\r\n" + 
	    		"imie nvarchar(50),\r\n" + 
	    		"nazwisko nvarchar(50),\r\n" + 
	    		"pesel varchar(11),\r\n" + 
	    		"id_adres int,\r\n" +
	    		"primary key (id_pracownik)\r\n" + 
	    		")");
			}
		st_new.executeUpdate("ALTER TABLE pracownicy ADD FOREIGN KEY (id_adres) references ADRESY(id_adres)");
		
	}
	public static void CreateTableAuta(Connection connection)throws SQLException{
		Statement st_new = connection.createStatement();
		if (!TableExists(connection, "auta")) {
			st_new.executeUpdate("CREATE TABLE AUTA(\r\n" + 
	    		"nr_rejestracyjny varchar(10) not null,\r\n" + 
	    		"marka nvarchar(30),\r\n" + 
	    		"model nvarchar(30),\r\n" + 
	    		"typ_paliwa nvarchar(30),\r\n" + 
	    		"cena_wyp decimal,\r\n" + 
	    		"primary key (nr_rejestracyjny)\r\n" + 
	    		")");
		}
	}
	
	public static void CreateTableWypozyczenia(Connection connection)throws SQLException{
		Statement st_new = connection.createStatement();
		if (!TableExists(connection, "wypozyczenia")) {
			st_new.executeUpdate("CREATE TABLE WYPOZYCZENIA(\r\n" + 
	    		"id_wypozyczenie int identity not null,\r\n" + 
	    		"data_wypozyczenia date not null,\r\n" + 
	    		"data_zwrotu date null,\r\n" + 
	    		"id_pracownik int not null,\r\n" + 
	    		"id_klient int not null,\r\n" + 
	    		"nr_rejestracyjny varchar(10) not null,\r\n" + 
	    		"primary key (id_wypozyczenie)\r\n" + 
	    		")\r\n" + 
	    		"");
			st_new.executeUpdate("ALTER TABLE wypozyczenia ADD FOREIGN KEY (id_pracownik) references PRACOWNICY(id_pracownik)");
			st_new.executeUpdate("ALTER TABLE wypozyczenia ADD FOREIGN KEY (id_klient) references KLIENCI(id_klient)");
			st_new.executeUpdate("ALTER TABLE wypozyczenia ADD FOREIGN KEY (nr_rejestracyjny) references AUTA(nr_rejestracyjny)");

		}
	}
	
	public static void DropTable(Connection connection, String table_name) throws SQLException{
	    Statement st_drop = connection.createStatement();
	    if(TableExists(connection, table_name))
	    	st_drop.executeQuery("DROP TABLE " + table_name + " CASCADE");
	}
	
	public static void DropAllTables(Connection connection) throws SQLException{   
		DropTable(connection, "adresy");  
	    DropTable(connection, "wypozyczenia");
	    DropTable(connection, "klienci");
	    DropTable(connection, "pracownicy");
	    DropTable(connection, "auta");    
	    System.out.println( "All dropped." );
	}
	
	public static void InsertIntoAdresy(Connection connection, String ulica, String kodpocztowy, String miasto) throws SQLException{
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO adresy(ulica, kod_pocztowy, miasto) VALUES (?,?,?)");

		pstmt.setString(1, ulica);
		pstmt.setString(2, kodpocztowy);
		pstmt.setString(3, miasto);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public static void InsertIntoKlienci(Connection connection, String imie, String nazwisko, String pesel, int id_adres) throws SQLException{
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO klienci(imie, nazwisko, pesel, id_adres) VALUES (?,?,?,?)");
		pstmt.setString(1, imie);
		pstmt.setString(2, nazwisko);
		pstmt.setString(3, pesel);
		pstmt.setInt(4, id_adres);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public static void InsertIntoPracownicy(Connection connection, String imie, String nazwisko, String pesel, int id_adres) throws SQLException{
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO pracownicy(imie, nazwisko, pesel, id_adres) VALUES (?,?,?,?)");
		pstmt.setString(1, imie);
		pstmt.setString(2, nazwisko);
		pstmt.setString(3, pesel);
		pstmt.setInt(4, id_adres);
		pstmt.executeUpdate();
		pstmt.close();
	}
	public static void InsertIntoAuta(Connection connection, String nr , String marka, String model, String typ_paliwa, double cena) throws SQLException{
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO auta VALUES (?,?,?,?,?)");
		pstmt.setString(1, nr);
		pstmt.setString(2, marka);
		pstmt.setString(3, model);
		pstmt.setString(4, typ_paliwa);
		pstmt.setDouble(5, cena);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public static void InsertIntoWypozyczenia(Connection connection, int klient, int pracownik, String auto, String data_wyp, String data_zwrotu) throws SQLException{
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO wypozyczenia("
				+ "data_wypozyczenia, data_zwrotu, id_pracownik, id_klient, nr_rejestracyjny) VALUES (?,?,?,?,?)");
		pstmt.setString(1, data_wyp);
		pstmt.setString(2, data_zwrotu);
		pstmt.setInt(3, pracownik);
		pstmt.setInt(4, klient);
		pstmt.setString(5, auto);		
		pstmt.executeUpdate();
		pstmt.close();
	}
	public static void ReadKlienci(Connection con) throws SQLException{
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery("select * from klienci");
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnsNumber = rsmd.getColumnCount();
	    System.out.println("KLIENCI");
	    while (rs.next()) {
	    	int nr = rs.getInt("id_klient");
	    	String imie = rs.getString("imie");
	    	String nazwisko = rs.getString("nazwisko");
	    	String pesel = rs.getString("pesel");
	    	  System.out.println(nr + "  | " +imie+" | "+nazwisko+ " | "+ pesel+ "\n");
	    	}
	}
	
	public static void ReadPracownicy(Connection con) throws SQLException{
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery("select * from pracownicy");
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnsNumber = rsmd.getColumnCount();
	    System.out.println("PRACOWNICY");
	    while (rs.next()) {
	    	int nr = rs.getInt("id_pracownik");
	    	String imie = rs.getString("imie");
	    	String nazwisko = rs.getString("nazwisko");
	    	String pesel = rs.getString("pesel");
	    	  System.out.println(nr + "  | " +imie+" | "+nazwisko+ " | "+ pesel+ "\n");
	    	}
	}
	
	public static void ReadAdresy(Connection con) throws SQLException{
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery("select * from adresy");
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnsNumber = rsmd.getColumnCount();
	    System.out.println("ADRESY");
	    while (rs.next()) {
	    	int nr = rs.getInt("id_adres");
	    	String ulica = rs.getString("ulica");
	    	String kod = rs.getString("kod_pocztowy");
	    	String miasto = rs.getString("miasto");
	    	  System.out.println(nr + "  | " +ulica+" | "+kod+ " | "+ miasto+ "\n");
	    	}
	}
	public static void ReadAuta(Connection con) throws SQLException{
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery("select * from auta");
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnsNumber = rsmd.getColumnCount();
	    System.out.println("AUTA");
	    while (rs.next()) {
	    	String nr = rs.getString("nr_rejestracyjny");
	    	String marka = rs.getString("marka");
	    	String model = rs.getString("model");
	    	String paliwo = rs.getString("typ_paliwa");
	    	double cena = rs.getDouble("cena_wyp");
	    	  System.out.println(nr + "  | " +marka+" | "+model+ " | "+ paliwo+ " | "+ cena + "\n");
	    	}
	}

	public static void PrepareDB(Connection con) throws SQLException{
        
		CreateTableAdresy(con);
        CreateTableAuta(con);
        CreateTableKlienci(con);
        CreateTablePracownicy(con);
        CreateTableWypozyczenia(con);
		
        InsertIntoAdresy(con, "Krajewskiego 13", "84-112", "Wejherowo");
        InsertIntoAdresy(con, "Mickiewicza 11a", "84-112", "Wejherowo");
        InsertIntoAdresy(con, "Sienkiewicza 31/21", "85-100", "Rumia");
        InsertIntoAdresy(con, "Krasickiego 188c/2", "85-201", "Reda");
        InsertIntoAdresy(con, "Mickiewicza 29", "85-201", "Reda");
        InsertIntoAdresy(con, "Mickiewicza 27", "85-201", "Reda");
        
        InsertIntoKlienci(con, "Anna", "Gruszka", "99112098123", 0);
        InsertIntoKlienci(con, "Marcin", "Agrest", "87543277182", 1);
        InsertIntoKlienci(con, "Agata", "Agrest", "76432763872", 1);
        InsertIntoKlienci(con, "Piotr", "Jabłoński", "87332134221", 2);
        
        InsertIntoPracownicy(con, "Adrian",  "Młotek", "98776543210", 3);
        InsertIntoPracownicy(con, "Marian",  "Teracki", "10293847564", 4);
        
    	InsertIntoAuta(con, "GDA20D91", "Ford", "Focus", "diesel", 40.00);
    	InsertIntoAuta(con, "WA982R09", "Opel", "Astra", "benzyna", 30.00);
    	InsertIntoAuta(con, "GDA298K32", "Opel", "Corsa", "benzyna", 20.00);
    	InsertIntoAuta(con, "GD909I12", "Skoda", "Fabia", "gaz", 30.00);
       
    	InsertIntoWypozyczenia(con, 0, 0, "GDA20D91", "2019-11-20", "2019-11-22");
    	InsertIntoWypozyczenia(con, 1, 0, "WA982R09", "2019-10-02", "2019-10-12");
    	InsertIntoWypozyczenia(con, 2, 1, "GDA298K32", "2019-11-10", "2019-11-15");
	}
	
    public static void main( String[] args ) throws SQLException
    {
        Connection con = GetConnection();
        
       //DropAllTables(con);

        //PrepareDB(con);
        ReadKlienci(con);
        ReadPracownicy(con);
        ReadAdresy(con);
        ReadAuta(con);
        
    }
}
