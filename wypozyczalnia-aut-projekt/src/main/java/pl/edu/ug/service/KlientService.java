package pl.edu.ug.service;
import java.util.List;

import pl.edu.ug.domain.Klient;

public interface KlientService {
	
	public void addKlient(Klient klient);
	public void dropKlienci();
	public List<Klient> getKlienci();
	public void deleteKlient(int id);
	public void updateKlient(String imie, String nazwisko, String pesel, int id_stary);

}
