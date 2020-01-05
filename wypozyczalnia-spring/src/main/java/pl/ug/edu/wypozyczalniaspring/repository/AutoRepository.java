package pl.ug.edu.wypozyczalniaspring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.ug.edu.wypozyczalniaspring.model.Auto;

public interface AutoRepository extends CrudRepository<Auto, Long>{
	
	Auto findByNrRejestracyjny(String nrRejestracyjny);
	List<Auto> findByMarka(String marka);

}
