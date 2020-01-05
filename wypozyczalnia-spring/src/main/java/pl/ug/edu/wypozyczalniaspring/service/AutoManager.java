package pl.ug.edu.wypozyczalniaspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ug.edu.wypozyczalniaspring.model.Auto;
import pl.ug.edu.wypozyczalniaspring.repository.AutoRepository;

@Service
public class AutoManager {

	
	private AutoRepository autoRepository;
	
	@Autowired
	public AutoManager(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}
	
	//CRUD
	public Auto getOne(long id) {
		return autoRepository.getOne(id);
	}
	public Optional<Auto> getById(long id) {
		return autoRepository.findById(id);
	}
	public List<Auto> getAll(){
		List<Auto> auta = new ArrayList<>();
		autoRepository.findAll().forEach(auta::add);
		return auta;
	}
	public void addAuto(Auto auto) {
		autoRepository.save(auto);
	}
	public void addAuta( List<Auto> auta) {
		for(Auto a : auta){
			addAuto(a);
		}
	}
	public void updateAuto(Long id, Auto auto) {
		autoRepository.save(auto);
	}
	public void deleteAutoById(Long id) {
		if(autoRepository.findById(id).isPresent()){
			autoRepository.deleteById(id);
		}
	}
	
	//Other operation
	public Auto getByNrRejestracyjny(String nrRejestracyjny) {
		return autoRepository.findByNrRejestracyjny(nrRejestracyjny);
	}
	public List<Auto> getByMarka(String marka){
		List<Auto> auta = new ArrayList<>();
		autoRepository.findByMarka(marka).forEach(auta::add);
		return auta;
	}
}
