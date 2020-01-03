package pl.edu.ug.springdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pl.edu.ug.springdemo.domain.Book;
import pl.edu.ug.springdemo.repository.BookRepository;

@Service
@Component
public class BookManager {
	
	@Autowired
	private BookRepository bookRepo;
	
	
	Optional<Book> findById(Long id){
		return bookRepo.findById(id);
	}

	public void addNewBook(Book book) {
		bookRepo.save(book);
	}
	
	public void updateBook(Long id, Book book) {
		bookRepo.save(book);
	}

	public List<Book> getAllBooks(){
		List<Book> books = new ArrayList<>();
		bookRepo.findAll()
			.forEach(books::add);   // dla kazdego znalezionego elementu przez findAll() dodaj go do listy books
									// method references
		return books;

	}
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}
	
}

