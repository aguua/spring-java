package pl.ug.edu.wypozyczalniaspring.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.ug.edu.wypozyczalniaspring.model.Auto;
import pl.ug.edu.wypozyczalniaspring.model.Paliwo;
import pl.ug.edu.wypozyczeniaspring.service.AutoManager;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)

public class AutoManagerTest {

	@Autowired
	AutoManager autoManger;
	
	@Test
	public void checkAdd() {
		Auto auto = new Auto("CCH367HG6", "Ford", "Focus", Paliwo.benzyna, 40);
		autoManger.addAuto(auto);
		
		List<Auto> auta = autoManger.getAll();
		assertTrue(auta.size() == 1);
		

				
	}
}
