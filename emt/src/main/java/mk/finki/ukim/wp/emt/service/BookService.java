package mk.finki.ukim.wp.emt.service;

import mk.finki.ukim.wp.emt.model.Book;
import mk.finki.ukim.wp.emt.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Book save(BookDto book);

    Book findById(long id);

    Book update(Long id, BookDto book);

    void deleteById(long id);

}
