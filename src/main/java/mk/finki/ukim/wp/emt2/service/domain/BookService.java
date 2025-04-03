package mk.finki.ukim.wp.emt2.service.domain;
import mk.finki.ukim.wp.emt2.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(Book book);

    Optional<Book> update(Long id, Book book);

    void deleteById(Long id);

}
