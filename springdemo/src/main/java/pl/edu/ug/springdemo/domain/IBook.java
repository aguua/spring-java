package pl.edu.ug.springdemo.domain;

import org.springframework.stereotype.Component;

@Component
public interface IBook {
	  public void addBook(String book_title); 
	  public void removeBook(String book_title);
	  public void showBooks();
}

