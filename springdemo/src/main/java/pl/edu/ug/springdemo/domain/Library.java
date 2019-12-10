package pl.edu.ug.springdemo.domain;

import java.util.List;


public interface Library {
	  public void addBook(String book_title); 
	  public void removeBook(String book_title);
	  public void showBooks();
	  public List<String> getBooks();
	}
