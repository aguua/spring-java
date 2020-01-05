package pl.ug.edu.wypozyczalniaspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.ug.edu.wypozyczalniaspring.model.Klient;
import pl.ug.edu.wypozyczalniaspring.model.Wypozyczenie;

@Repository
public interface WypozyczenieRepository extends JpaRepository<Wypozyczenie, Long>{

	List<Wypozyczenie> findByKlient(Klient klient);
	List<Wypozyczenie> findByKlientId(Long idKlient);
	List<Wypozyczenie> findByDataWypozyczenia(String dataWypozyczenia);
	
}
