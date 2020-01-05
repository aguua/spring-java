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
	public Optional<Auto> findById(long id) {
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
	public void deleteAuto(Long id) {
		autoRepository.deleteById(id);
	}
	
	//Other operation
	public Auto findByNrRejestracyjny(String nrRejestracyjny) {
		return autoRepository.findByNrRejestracyjny(nrRejestracyjny);
	}
	public List<Auto> findByMarka(String marka){
		List<Auto> auta = new ArrayList<>();
		autoRepository.findByMarka(marka).forEach(auta::add);
		return auta;
	}
}
