package pl.edu.ug.springdemo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.edu.ug.springdemo.domain.Book;
import pl.edu.ug.springdemo.repository.BookRepository;

//@SpringBootTest  // testy integracyjne  (cover the interaction between a business service and the persistence layer, for instance.)
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class BookRepoTest {

	@Autowired
	private BookRepository bookRepo;
	
	 @Test
		public void checkRepoAdding(){
		   StringBuilder response = new StringBuilder();
		   
			Book book = new Book();
			book.setTitle("Pan Tadeusz");
			book.setAuthor("Adam Mickiewicz");
			book.setYear(1788);
			bookRepo.save(book);
			
			Book b2 = new Book();
			b2.setTitle("Pan Tadeusz");
			b2.setAuthor("King");
			b2.setYear(2002);
			bookRepo.save(b2); //bookManager.addNewBook(b2);
			
			// print 
	        for(Book i: bookRepo.findAll()) {
	            response.append(i).append("<br>");
	        }
	        System.out.print(response.toString());
	        //
			List<Book> books = new ArrayList<>();
			bookRepo.findAll()
				.forEach(books::add);
			assertTrue(books.size() >=2);
	    }
	
	
}
