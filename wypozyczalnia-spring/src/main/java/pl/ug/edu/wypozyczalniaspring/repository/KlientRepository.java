package pl.ug.edu.wypozyczalniaspring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.ug.edu.wypozyczalniaspring.model.Klient;

public interface KlientRepository extends CrudRepository<Klient, Long> {

		  List<Klient> findByNazwisko(String nazwisko);

		  Klient findById(long id);
}

