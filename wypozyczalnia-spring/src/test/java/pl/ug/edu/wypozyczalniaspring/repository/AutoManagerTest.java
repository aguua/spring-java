package pl.ug.edu.wypozyczalniaspring.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

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
    public void clearDBinsertOneAuto() { 
		autoRepository.deleteAll();
		Auto auto = new Auto("CCH67TRT", "Ford", "Focus", Paliwo.benzyna, 40);
		autoManager.addAuto(auto);
		}
	 
	@Test
	public void testAddAuto() {
		List<Auto> auta = autoManager.getAll();
		assertEquals(1, auta.size());		
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void testAddNotValidAutoSameNumber() {
		Auto autoSameNr = new Auto("CCH67TRT", "Ford-inny", "Focus", Paliwo.benzyna, 40);
		autoManager.addAuto(autoSameNr);
	}
	
	@Test(expected = DataIntegrityViolationException.class) 
	public void testAddAutoNotValidMissingNumber(){
		Auto auto = new Auto();
		autoManager.addAuto(auto);
		
		List<Auto> auta = autoManager.getAll();
		assertEquals(1, auta.size());
	}
	 @Test
	    public void testFindByMarka(){
	        String marka = "Ford";	
	        assertTrue(!(autoManager.getByMarka(marka)).isEmpty());
	    }
	 @Test
	    public void testFindByNumer(){
	        String numer = "CCH67TRT";	
	        assertNotNull(autoManager.getByNrRejestracyjny(numer));
	    }
	
	@Test
	public void testDeleteAuto() {
		String numer = "CCH67TRT";
		Auto autoToDelete = autoManager.getByNrRejestracyjny(numer);
		Long id = autoToDelete.getId_auto();
		
		assertTrue(autoManager.getById(id).isPresent());
		autoManager.deleteAutoById(id);
		assertFalse(autoManager.getById(id).isPresent());
	}
	
	@Test
	public void testDeleteNonExistingAuto() {
		int sizeBefore = autoManager.getAll().size();
		assertFalse(autoManager.getById(10000L).isPresent());

		autoManager.deleteAutoById(10000L);
		
		assertFalse(autoManager.getById(10000L).isPresent());
		assertEquals(sizeBefore, autoManager.getAll().size());
	}

	 @Test
	 public void testupdateAuto() {
			String numer = "CCH67TRT";
			Auto autoToUpdate = autoManager.getByNrRejestracyjny(numer);
			Long id = autoToUpdate.getId_auto();
			autoToUpdate.setTyp_paliwa(Paliwo.gaz);
			autoManager.updateAuto(id, autoToUpdate);
			
			Optional<Auto> autoAfterUpdate = autoManager.getById(id);
			assertEquals("Optional["+autoToUpdate.toString()+"]", autoAfterUpdate.toString());

		 
	 }
 
	 
}
