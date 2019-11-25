package pl.edu.ug.domain;

public class Wypozyczenie {
	
	private int id_wypozyczenie;
	private Klient klient;
	private Auto auto;
	private String data_wypozyczenia;
	private String data_zwrotu;
	public Wypozyczenie(int id_wypozyczenie, String data_wypozyczenia, String data_zwrotu, Klient klient, Auto auto) {
		super();
		this.id_wypozyczenie = id_wypozyczenie;
		this.klient = klient;
		this.auto = auto;
		this.data_wypozyczenia = data_wypozyczenia;
		this.data_zwrotu = data_zwrotu;
	}
	public int getid_wypozyczenie() {
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
	public Auto getAuto() {
		return auto;
	}
	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	public String getData_wypozyczenia() {
		return data_wypozyczenia;
	}
	public void setData_wypozyczenia(String data_wypozyczenia) {
		this.data_wypozyczenia = data_wypozyczenia;
	}
	public String getData_zwrotu() {
		return data_zwrotu;
	}
	public void setData_zwrotu(String data_zwrotu) {
		this.data_zwrotu = data_zwrotu;
	}
	@Override
	public String toString() {
		return "Wypozyczenie [id=" + id_wypozyczenie + ", data_wypozyczenia=" + data_wypozyczenia + ", data_zwrotu=" + data_zwrotu +
				", klient=" + klient + ", auto=" + auto + "]";
	}
	
	
}
