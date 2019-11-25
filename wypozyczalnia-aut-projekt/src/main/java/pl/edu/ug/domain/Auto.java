package pl.edu.ug.domain;

public class Auto {

	private String nr_rejestracyjny;
	private String marka;
	private String model;
	private String typ_paliwa;
	private double cena;
	
	public Auto(String nr_rejestracyjny, String marka, String model, String typ_paliwa, double cena) {
		super();
		this.nr_rejestracyjny = nr_rejestracyjny;
		this.marka = marka;
		this.model = model;
		this.typ_paliwa = typ_paliwa;
		this.cena = cena;
	}

	public String getNr_rejestracyjny() {
		return nr_rejestracyjny;
	}

	public void setNr_rejestracyjny(String nr_rejestracyjny) {
		this.nr_rejestracyjny = nr_rejestracyjny;
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

	public String getTyp_paliwa() {
		return typ_paliwa;
	}

	public void setTyp_paliwa(String typ_paliwa) {
		this.typ_paliwa = typ_paliwa;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Auto [nr_rejestracyjny=" + nr_rejestracyjny + ", marka=" + marka + ", model=" + model + ", typ_paliwa="
				+ typ_paliwa + ", cena=" + cena + "]";
	}
	

	
	
	
}
