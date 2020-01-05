package pl.ug.edu.wypozyczalniaspring.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.ug.edu.wypozyczalniaspring.model.Auto;
import pl.ug.edu.wypozyczalniaspring.model.Klient;
import pl.ug.edu.wypozyczalniaspring.model.Paliwo;
import pl.ug.edu.wypozyczalniaspring.service.AutoManager;
import pl.ug.edu.wypozyczalniaspring.service.KlientManager;
import pl.ug.edu.wypozyczalniaspring.service.WypozyczenieManager;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WypozyczenieManagerTest {

	@Autowired
	public KlientManager klientManager;
	@Autowired
	public AutoManager autoManager;
	
	@Autowired 
	public WypozyczenieRepository wypozyczenieRepository;
	@Autowired
	public WypozyczenieManager wypozyczenieManager;
	
	@Before
	public void clearDBsetAutoAndKlient() {
		wypozyczenieRepository.deleteAll();
		Klient klient1 = new Klient("Adam", "Nowak");
		Klient klient2 = new Klient("Adrianna", "Malinowska");
		Klient klient3 = new Klient("Piotr", "Adamczyk");
		klientManager.addKlient(klient1);
		klientManager.addKlient(klient2);
		klientManager.addKlient(klient3);
		
		Auto auto1 = new Auto("CCH67TRT", "Ford", "Focus", Paliwo.benzyna, 40);
		Auto auto2 = new Auto("CCH1111T", "Opel", "Astra", Paliwo.benzyna, 30);
		Auto auto3 = new Auto("GDA5612D", "Opel", "Corsa", Paliwo.gaz, 20);
		autoManager.addAuto(auto1);
		autoManager.addAuto(auto2);
		autoManager.addAuto(auto3);
	}
	@After
	public void clearAfterDB() {
		klientManager.deleteAll();
		autoManager.deleteAll();
	}

}


