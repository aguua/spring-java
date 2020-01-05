package pl.ug.edu.wypozyczalniaspring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Wypozyczenie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_wypozyczenie;
	
	@OneToOne
	private Klient klient;
	
	@OneToMany
	private List<Auto> auta = new ArrayList<Auto>();
	

	private String dataWypozyczenia;
	private String dataZwrotu;
	public Wypozyczenie(Long id_wypozyczenie, String data_wypozyczenia, String data_zwrotu, Klient klient, List<Auto> auta) {
		super();
		this.id_wypozyczenie = id_wypozyczenie;
		this.klient = klient;
		this.auta =auta;
		this.dataWypozyczenia = data_wypozyczenia;
		this.dataZwrotu = data_zwrotu;
	}
	public Long getid_wypozyczenie() {
		return id_wypozyczenie;
	}
	public void setId(long id) {
		this.id_wypozyczenie = id_wypozyczenie;
	}
	public Klient getKlient() {
		return klient;
	}
	public void setKlient(Klient klient) {
		this.klient = klient;
	}
	public List<Auto> getAuta() {
		return auta;
	}
	public void setAuta(List<Auto> auta) {
		this.auta = auta;
	}
	public String getData_wypozyczenia() {
		return dataWypozyczenia;
	}
	public void setData_wypozyczenia(String data_wypozyczenia) {
		this.dataWypozyczenia = data_wypozyczenia;
	}
	public String getData_zwrotu() {
		return dataZwrotu;
	}
	public void setData_zwrotu(String data_zwrotu) {
		this.dataZwrotu = data_zwrotu;
	}
	@Override
	public String toString() {
		return "Wypozyczenie [id=" + id_wypozyczenie + ", data_wypozyczenia=" + dataWypozyczenia + ", data_zwrotu=" + dataZwrotu +
				", klient=" + klient + ", auto=" + auta + "]";
	}
	
	
}
