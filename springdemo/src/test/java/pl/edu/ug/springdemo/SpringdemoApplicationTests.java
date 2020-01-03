package pl.edu.ug.springdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.edu.ug.springdemo.domain.Library;

@SpringBootTest
class SpringdemoApplicationTests {

    @Autowired   //insted of  private Library library = new Library();
	private Library library;

	@Test
	void checkAdd() {
		library.addBook("Jonnas");
		assertTrue(library.getBooks().contains("Jonnas"));
	}
	
	@Test 
	void checkRemove() {
		library.addBook("Jonnas");
		assertTrue(library.getBooks().contains("Jonnas"));
		library.removeBook("Jonnas");
		assertFalse(library.getBooks().contains("Jonnas"));
	}
	@Test
	void chechGetHost() {
		assertEquals("smtp.example.com",library.getHost());
	}
	
	@Test
	void checkAddBook() {
		
	}


}
