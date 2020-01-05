package pl.ug.edu.wypozyczalniaspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.ug.edu.wypozyczalniaspring.model.Klient;
import pl.ug.edu.wypozyczalniaspring.repository.KlientRepository;

@SpringBootApplication
public class WypozyczalniaSpringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WypozyczalniaSpringApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(KlientRepository repository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(new Klient("Jacek", "Braun"));
	      repository.save(new Klient("Chloe", "O'Brian"));
	      repository.save(new Klient("Kim", "Bauer"));
	      repository.save(new Klient("David", "Palmer"));
	      repository.save(new Klient("Michelle", "Dessler"));

	      // fetch all customers
	      System.out.print("Customers found with findAll():");
	      System.out.print("-------------------------------");
	      for (Klient klient : repository.findAll()) {
	        System.out.print(klient.toString());
	      }
	    };
	  }

	}
