package mk.finki.ukim.wp.emt2.service.domain.impl;

import mk.finki.ukim.wp.emt2.model.domain.Book;
import mk.finki.ukim.wp.emt2.repository.BookRepository;
import mk.finki.ukim.wp.emt2.service.domain.AuthorService;
import mk.finki.ukim.wp.emt2.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(Book book) {
        if (book.getAuthor() != null && authorService.findById(book.getAuthor().getId()).isPresent()) {
            return Optional.of(
                    bookRepository.save(new Book(
                            book.getTitle(),
                            book.getCategory(),
                            authorService.findById(book.getAuthor().getId()).get(),
                            book.getAvailableCopies()
                    )));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id).map(exBook -> {
            if (book.getTitle() != null){
                exBook.setTitle(book.getTitle());
            }
            if (book.getCategory() != null){
                exBook.setCategory(book.getCategory());
            }
            if (book.getAuthor() != null && authorService.findById(book.getAuthor().getId()).isPresent()) {
                exBook.setAuthor(authorService.findById(book.getAuthor().getId()).get());
            }
            if (book.getAvailableCopies() != null){
                exBook.setAvailableCopies(book.getAvailableCopies());
            }
            return bookRepository.save(exBook);
        });
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
