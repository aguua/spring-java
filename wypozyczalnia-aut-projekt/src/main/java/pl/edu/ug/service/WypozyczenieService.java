package pl.edu.ug.service;

import java.util.List;

import pl.edu.ug.domain.Wypozyczenie;


public interface WypozyczenieService {
	
	public void addWypozyczenie(Wypozyczenie wypozyczenie);
	public void dropWypozyczenia();
	public List<Wypozyczenie> getWypozyczenia();
	public void deleteWypozyczenie(int id);
	public void updateWypozyczenie( String data_w, String data_z, String nr_r, int id_klient, int id_wyp);

}
