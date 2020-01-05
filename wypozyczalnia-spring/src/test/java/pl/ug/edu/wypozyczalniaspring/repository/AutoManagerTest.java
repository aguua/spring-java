package pl.ug.edu.wypozyczalniaspring.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.ug.edu.wypozyczalniaspring.model.Auto;
import pl.ug.edu.wypozyczalniaspring.model.Paliwo;
import pl.ug.edu.wypozyczalniaspring.service.AutoManager;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class AutoManagerTest {

	@Autowired
	public AutoManager autoManager;
	
	@Autowired
	public AutoRepository autoRepository;
	
	@Before
    public void clearDB() { autoRepository.deleteAll();}
	 
	@Test
	public void testAddAuto() {
		Auto auto = new Auto("CCH67TRT", "Ford", "Focus", Paliwo.benzyna, 40);
		autoManager.addAuto(auto);	
		
		List<Auto> auta = autoManager.getAll();
		assertEquals(1, auta.size());		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void testAddNotValidAutoSameNumber() {
		Auto auto = new Auto("CH56T76A", "Ford", "Focus", Paliwo.benzyna, 40);
		Auto autoSameNr = new Auto("CH56T76A", "Ford-inny", "Focus", Paliwo.benzyna, 40);
		autoManager.addAuto(auto);
		autoManager.addAuto(autoSameNr);
	}
	
	@Test(expected = DataIntegrityViolationException.class) 
	public void testAddAutoNotValidMissingNumber(){
		Auto auto = new Auto();
		autoManager.addAuto(auto);
		
		List<Auto> auta = autoManager.getAll();
		assertEquals(0, auta.size());
	}
	
	@Test
	public void testDeleteAuto() {
		Auto auto = new Auto("CCH67TRT", "Ford", "Focus", Paliwo.benzyna, 40);
		autoManager.addAuto(auto);	
		autoManager.deleteAuto(auto.getId_auto());
		
		List<Auto> auta = autoManager.getAll();
		assertEquals(0, auta.size());
	}
}
