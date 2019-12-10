package pl.edu.ug.springdemo.domain;

import java.util.ArrayList;
import java.util.List;

public class Library implements Book {
	
	private List<String> library = new ArrayList<String>();  

	
	public void addBook(String book_title) {
		library.add(book_title);
	}
	
	public void removeBook(String book_title) {
		library.remove(book_title);
	}
	public void showBooks() {
		System.out.println("Dostępne książki: ");
		for (String book : library) {
			System.out.println(book);
		}
	}
	
	 public List<String> getBooks() {
		 return library;
	}


}
