package pl.ug.edu.wypozyczalniaspring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.ug.edu.wypozyczalniaspring.model.Klient;
import pl.ug.edu.wypozyczalniaspring.service.KlientManager;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class KlientManagerTest {
	
	@Autowired
	public KlientManager klientManager;
	
	@Autowired
	public KlientRepository klientRepository;
	
	@Before
    public void clearDBinsertOneK() { 
		klientRepository.deleteAll();
		Klient klient = new Klient("Adam", "Nowak");
		klientManager.addKlient(klient);
		}
	
	@Test
	public void testAdd() {
		assertEquals(1, klientManager.getAll().size());		
		Klient newKlient = new Klient("Anna", "Nowak");
		klientManager.addKlient(newKlient);
		assertEquals(2, klientManager.getAll().size());		
	}
	
	@Test
	public void testFindByNazwisko() {
		String nazwisko = "Nowak";
		assertNotNull(klientManager.getByNazwisko(nazwisko));
		
	}
	
	@Test
	public void testDeleteKlient() {
		Klient klient = new Klient("Anna", "Nowojorska");
		klientManager.addKlient(klient);
	    final Long id = klient.getId_klient();
	    assertTrue(klientManager.getById(id).isPresent());
	    klientRepository.deleteById(id);
	    assertFalse(klientManager.getById(id).isPresent());
		
	}


}
