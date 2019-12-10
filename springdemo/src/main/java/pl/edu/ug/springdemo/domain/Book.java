package pl.edu.ug.springdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class Book implements Library {
	
	@Autowired
	private List<String> library = new ArrayList<String>();  

	@Override
	public void addBook(String book_title) {
		library.add(book_title);
	}
	@Override
	public void removeBook(String book_title) {
		library.remove(book_title);
	}
	@Override
	public void showBooks() {
		System.out.println("Dostępne książki: ");
		for (String book : library) {
			System.out.println(book);
		}
	}
	@Override
	 public List<String> getBooks() {
		 return library;
	}


}
