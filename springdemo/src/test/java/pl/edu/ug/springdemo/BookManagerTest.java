package pl.edu.ug.springdemo;

import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.edu.ug.springdemo.service.BookManager;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
//@ComponentScan(basePackages = "pl.edu.ug.springdemo.repository")  // ?
public class BookManagerTest {

    @PersistenceContext
    private BookManager bookManager;
    
	@Autowired
	//private BookRepository bookRepository;
    
    @Test
	public void checkAdding(){
		/*Book book = new Book();
		book.setTitle("Pan Tadeusz");
		book.setAuthor("Adam Mickiewicz");
		book.setYear(1788);
		bookManager.addNewBook(book);
		
		Book b2 = new Book();
		b2.setTitle("Pan Tadeusz");
		b2.setAuthor("King");
		b2.setYear(2002);
		bookManager.addNewBook(b2);
		
		List<Book> books = bookManager.getBooks("Pan Tadeusz");
		assertTrue(books.size() >=2);*/
    }


}
