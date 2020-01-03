package pl.edu.ug.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.edu.ug.springdemo.domain.Library;

@SpringBootTest
class SpringdemoApplicationTests {

    @Autowired   //insted of  private Library library = new Library();
	private Library library;



}
