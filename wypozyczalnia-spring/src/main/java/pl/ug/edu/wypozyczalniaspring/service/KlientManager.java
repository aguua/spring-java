package pl.ug.edu.wypozyczalniaspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ug.edu.wypozyczalniaspring.model.Klient;
import pl.ug.edu.wypozyczalniaspring.repository.KlientRepository;

@Service
public class KlientManager {
	
private KlientRepository klientRepository;
	
	@Autowired
	public KlientManager(KlientRepository klientRepository) {
		this.klientRepository = klientRepository;
	}
	
  //CRUD
	public  Optional<Klient> getById(long id) {
		return klientRepository.findById(id);
	}
	
	/*public Klient getById(long id) {
		return klientRepository.findById(id);
	}*/
	public List<Klient> getAll(){
		List<Klient> k = new ArrayList<>();
		klientRepository.findAll().forEach(k::add);
		return k;
	}
	public void addKlient(Klient klient) {
		klientRepository.save(klient);
	}
	public void addKlient( List<Klient> klienci) {
		for(Klient k : klienci){
			addKlient(k);
		}
	}
	public void updateKlient(Long id, Klient klient) {
		klientRepository.save(klient);
	}
	public void deleteKlientById(Long id) {
		if(klientRepository.findById(id).isPresent()){
			klientRepository.deleteById(id);
		}
	}
	public List<Klient> getByNazwisko(String nazwisko) {
		return klientRepository.findByNazwisko(nazwisko);
	}

}
