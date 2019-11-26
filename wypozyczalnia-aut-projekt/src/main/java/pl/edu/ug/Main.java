package pl.edu.ug;

import java.sql.SQLException;
import java.util.List;

import pl.edu.ug.domain.Auto;
import pl.edu.ug.domain.Klient;
import pl.edu.ug.domain.Wypozyczenie;
import pl.edu.ug.service.AutoService;
import pl.edu.ug.service.AutoServiceJDBC;
import pl.edu.ug.service.KlientService;
import pl.edu.ug.service.KlientServiceJDBC;
import pl.edu.ug.service.WypozyczenieService;
import pl.edu.ug.service.WypozyczenieServiceJDBC;



public class Main{
	public static void main( String[] args ) throws SQLException
	{
		 // Connection con = WypozyczalniaJDBC.GetConnection();
		 // WypozyczalniaJDBC.DropAllTables(con);
		  
		AutoService as =  new AutoServiceJDBC();
		Auto ford = new Auto("XXDSA441", "Ford", "Focus", "diesel", 40.00);
		Auto corsa = new Auto("GDA298K32", "Opel", "Corsa", "benzyna", 20.00);
		Auto astra = new Auto("WA982R09", "Opel", "Astra", "benzyna", 30.00);
		Auto fabia = new Auto("GD909I12", "Skoda", "Fabia", "gaz", 30.00);
		as.addAuto(ford);
		as.addAuto(corsa);
		as.addAuto(astra);
		as.addAuto(fabia);
		//as.updateAuto("nowy",  "marka",  "model",  873, "GD909I12");
		//as.deleteAuto("nowy");
		List<Auto> result = as.getAuta();
	    for (Auto a : result) {
	      System.out.println(a);
	    }
	    
	    
	    
		KlientService ks = new KlientServiceJDBC();
		Klient k1 = new Klient(0,"Adam", "Maniek", "98765477643");
		Klient k2 = new Klient(1,"Anna", "Gruszka", "99112098123");
		Klient k3 = new Klient(2, "Marcin", "Agrest", "87543277182");
		Klient k4 = new Klient(3, "Agata", "Agrest", "76432763872");
        ks.addKlient(k1);
        ks.addKlient(k2);
        ks.addKlient(k3);
        ks.addKlient(k4);
	    // ks.updateKlient("imie", "nazwisko", "pesel", 1);
		//ks.deleteKlient(1);
		List<Klient> k_res = ks.getKlienci();
	    
		for (Klient k : k_res) {
			System.out.println(k);
		}
			
		WypozyczenieService ws = new WypozyczenieServiceJDBC();

	    Wypozyczenie wyp = new Wypozyczenie(1, "2019-11-21", "2019-12-23", k1, ford);
	    ws.addWypozyczenie(wyp);
		List<Wypozyczenie> w_res = ws.getWypozyczenia();
		for (Wypozyczenie w : w_res) {
			System.out.println(w);
		}

		//ws.dropWypozyczenia();
		//as.dropAuta();
		//ks.dropKlienci();

	}
}