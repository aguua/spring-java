package pl.ug.edu.wypozyczalniaspring.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Klient {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id_klient;
	  private String imie;
	  private String nazwisko;

	  public Klient() {}
	  
	  public Klient(String imie, String nazwisko) {
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	public Long getId_klient() {
		return id_klient;
	}

	public void setId_klient(Long id_klient) {
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


	@Override
	public String toString() {
		return "Klient [id_klient=" + id_klient + ", imie=" + imie + ", nazwisko=" + nazwisko + "]";
	}
	  

}

