package pl.ug.edu.wypozyczalniaspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Auto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_auto;
	
	@Column(unique = true)
	private String nrRejestracyjny;
	private String marka;
	private String model;
	
    @Enumerated(EnumType.STRING)
	private Paliwo typPaliwa;
	private double cena;
	
	protected Auto() {}
	public Auto(String nr_rejestracyjny, String marka, String model, Paliwo typ_paliwa, double cena) {
		super();
		this.nrRejestracyjny = nr_rejestracyjny;
		this.marka = marka;
		this.model = model;
		
		this.typPaliwa = typ_paliwa;
		this.cena = cena;
	}

	public String getNr_rejestracyjny() {
		return nrRejestracyjny;
	}

	public void setNr_rejestracyjny(String nr_rejestracyjny) {
		this.nrRejestracyjny = nr_rejestracyjny;
	}

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Paliwo getTyp_paliwa() {
		return typPaliwa;
	}

	public void setTyp_paliwa(Paliwo typ_paliwa) {
		this.typPaliwa = typ_paliwa;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Auto [nr_rejestracyjny=" + nrRejestracyjny + ", marka=" + marka + ", model=" + model + ", typ_paliwa="
				+ typPaliwa + ", cena=" + cena + "]";
	}
	

	
	
	
}
