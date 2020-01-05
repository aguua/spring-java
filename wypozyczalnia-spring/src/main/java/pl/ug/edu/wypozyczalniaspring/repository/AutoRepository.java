package pl.ug.edu.wypozyczalniaspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.ug.edu.wypozyczalniaspring.model.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long>{

	Auto findByNrRejestracyjny(String nrRejestracyjny);
	List<Auto> findByMarka(String marka);

}
