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
		  //Connection con = WypozyczalniaJDBC.GetConnection();
		  //WypozyczalniaJDBC.PrepareDB(con);
		  
		AutoService as =  new AutoServiceJDBC();
		Auto ford = new Auto("XXDSA441", "Ford", "Focus", "diesel", 40.00);
		as.addAuto(ford);
		as.updateAuto("nowy",  "marka",  "model",  873, "GD909I12");
		as.deleteAuto("nowy");
		List<Auto> result = as.getAuta();
	    for (Auto a : result) {
	      System.out.println(a);
	    }
	    
	    
	    
		KlientService ks = new KlientServiceJDBC();
		Klient klient = new Klient(1,"Adam", "Maniek", "98765477643");
        ks.addKlient(klient);
		ks.updateKlient("imie", "nazwisko", "pesel", 0);
		ks.deleteKlient(0);
		List<Klient> k_res = ks.getKlienci();
	    
		for (Klient k : k_res) {
			System.out.println(k);
		}
			
		WypozyczenieService ws = new WypozyczenieServiceJDBC();

	    Wypozyczenie wyp = new Wypozyczenie(1, "2019-11-21", "2019-12-23", klient, ford);
	    ws.addWypozyczenie(wyp);
		List<Wypozyczenie> w_res = ws.getWypozyczenia();
		for (Wypozyczenie w : w_res) {
			System.out.println(w);
		}


	}
}