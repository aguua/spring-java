package pl.edu.ug.springdemo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.edu.ug.springdemo.domain.Book;

@Repository
public interface BookRepository extends ExtendedBookRepository,
										JpaRepository<Book, Long> {  //  JPA  extends : CrudRepository<T,ID>, PagingAndSortingRepository<T,ID>, QueryByExampleExecutor<T>, Repository<T,ID>

		//List<Book> findByTitle(String title);
		
		@Query(value = "{ 'title' : ?0, 'author' : ?1 }" )
		List<Book> findByTitleAndAuthor(String title, String author);
		
		@Query("SELECT b.title FROM book b where b.id = :id") 
		String findTitleById(@Param("id") Long id);
		

	}

