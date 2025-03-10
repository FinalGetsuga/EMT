package mk.finki.ukim.wp.emt.service.impl;

import mk.finki.ukim.wp.emt.model.Author;
import mk.finki.ukim.wp.emt.model.Book;
import mk.finki.ukim.wp.emt.model.dto.BookDto;
import mk.finki.ukim.wp.emt.repository.BookRepository;
import mk.finki.ukim.wp.emt.service.AuthorService;
import mk.finki.ukim.wp.emt.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(BookDto book) {
        Book b = new Book();
        return saveBook(book,b);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book update(Long id, BookDto book) {
        Book b = this.findById(id);

        if (b == null) {
            return null;
        }

        return saveBook(book,b);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    private Book saveBook(BookDto book, Book b){
        Author author = authorService.findById(book.getAuthor());

        if (author == null){
            return null;
        }

        b.setTitle(book.getTitle().isEmpty() ? "Untitled" : book.getTitle());
        b.setCategory(book.getCategory());
        b.setAuthor(author);
        b.setAvailableCopies(book.getAvailableCopies() >= 0 ? book.getAvailableCopies() : 0);

        return bookRepository.save(b);
    }
}
