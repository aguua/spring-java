package pl.ug.edu.wypozyczalniaspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ug.edu.wypozyczalniaspring.model.Klient;
import pl.ug.edu.wypozyczalniaspring.model.Wypozyczenie;
import pl.ug.edu.wypozyczalniaspring.repository.WypozyczenieRepository;

@Service 
public class WypozyczenieManager {

	private WypozyczenieRepository wypozyczenieRepository;
	
	@Autowired
	public WypozyczenieManager(WypozyczenieRepository wypozyczenieRepository) {
		this.wypozyczenieRepository = wypozyczenieRepository;
	}
	
	public Optional<Wypozyczenie> getById(long id){
		return wypozyczenieRepository.findById(id);
	}
	
	public List<Wypozyczenie> getByDataWypozyczenia(String dataWypozyczenia){
		return wypozyczenieRepository.findByDataWypozyczenia(dataWypozyczenia);
	}
	public List<Wypozyczenie> getByKlient(Klient klient){
		return wypozyczenieRepository.findByKlient(klient);
	}
	public List<Wypozyczenie> getByKlientId(Long idKlient){
		return wypozyczenieRepository.findByKlientId(idKlient);
	}
		
	public List<Wypozyczenie> getAll() {
		List<Wypozyczenie> wyp = new ArrayList<>();
		wypozyczenieRepository.findAll().forEach(wyp::add);
		return wyp;
	}
	
	public void addWypozyczenie(Wypozyczenie wypozyczenie) {
		wypozyczenieRepository.save(wypozyczenie);
	}
	public void deleteWypozyczenieById(Long id) {
		if(wypozyczenieRepository.findById(id).isPresent()) {
			wypozyczenieRepository.deleteById(id);
		}
	}
	
	public void updateWypozyczenie(Long id, Wypozyczenie wypozyczenie) {
		wypozyczenieRepository.save(wypozyczenie);
	}
	
}
