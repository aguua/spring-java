package pl.edu.ug.domain;

public class Klient {
	  private int id_klient;
	  private String imie;
	  private String nazwisko;
	  private String pesel;

	  public Klient(int id, String imie, String nazwisko, String pesel) {
		this.id_klient = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
	}

	public int getId_klient() {
		return id_klient;
	}

	public void setId_klient(int id_klient) {
		this.id_klient = id_klient;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}


	@Override
	public String toString() {
		return "Klient [id_klient=" + id_klient + ", imie=" + imie + ", nazwisko=" + nazwisko + ", pesel=" + pesel
				 + "]";
	}
	  

}
