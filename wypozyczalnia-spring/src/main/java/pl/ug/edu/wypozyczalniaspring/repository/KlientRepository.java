package pl.ug.edu.wypozyczalniaspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.ug.edu.wypozyczalniaspring.model.Klient;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {

		  List<Klient> findByNazwisko(String nazwisko);
		 // Klient findById(long id);
}

