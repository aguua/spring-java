package pl.edu.ug.springdemo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Library implements IBook {
	
	private List<String> library;
	private int num;
	@Value("${spring.mail.host}") String host;  //wczytana wartosc z application.properties
	
	//@Autowired   --domyslnie uzywany jest ten konstruktor 
	public Library()
	{
		 library = new ArrayList<String>();  
		 System.out.println("in constructor");
	}
	
	@Autowired
	public Library(@Value("8") int n ) {
		library = new ArrayList<String>();  
		this.num = n;
		System.out.println("with "+ n + " in constructor");
		System.out.println("constructor: here is mail host before bean creation "+ host);
	}
	public void addBook(@Value("Pan Tadeusz") String book_title) {
		library.add(book_title);
		System.out.println("in add,  n = " + num);
	}
	public String getHost() {
		System.out.println("getHost(): here is mail host after bean is created: "+ host);
		return host;
	}
	public void removeBook(@Value("Pan Tadeusz") String book_title) {
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

