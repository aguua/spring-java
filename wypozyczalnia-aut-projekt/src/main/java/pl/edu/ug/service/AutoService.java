package pl.edu.ug.service;

import java.util.List;

import pl.edu.ug.domain.Auto;

public interface AutoService {

	  public void addAuto(Auto auto);
	  
	  public void dropAuta();

	  public void updateAuto( String marka, String model, String typ_paliwa, double cena_wyp, String nr_stary);
	  
	  public void deleteAuto(String nr_rejestracyjny);
	  
	  public List<Auto> getAuta();

	}
