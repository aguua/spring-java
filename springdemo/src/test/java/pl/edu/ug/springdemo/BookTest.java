package pl.edu.ug.springdemo;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.ug.springdemo.domain.Book;

public class BookTest {
	

	Book book;

	@Test
	public void testone() {		
		String ksiazka = "Nowa ksiazka";
		book.addBook(ksiazka);
		assertTrue(book.getBooks().contains(ksiazka));
		
	}
	
	

}
