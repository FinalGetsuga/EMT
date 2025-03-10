package mk.finki.ukim.wp.emt.repository;

import mk.finki.ukim.wp.emt.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
