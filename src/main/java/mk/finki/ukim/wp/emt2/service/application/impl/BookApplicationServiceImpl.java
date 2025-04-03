package mk.finki.ukim.wp.emt2.service.application.impl;

import mk.finki.ukim.wp.emt2.dto.CreateBookDto;
import mk.finki.ukim.wp.emt2.dto.DisplayBookDto;
import mk.finki.ukim.wp.emt2.model.domain.Author;
import mk.finki.ukim.wp.emt2.service.application.BookApplicationService;
import mk.finki.ukim.wp.emt2.service.domain.AuthorService;
import mk.finki.ukim.wp.emt2.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return bookService.findAll().stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto bookDto) {

        Optional<Author> author = authorService.findById(bookDto.AuthorId());

        if (author.isPresent()) {
            return bookService.save(bookDto.toBook(author.get())).map(DisplayBookDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto bookDto) {

        Optional<Author> author = authorService.findById(bookDto.AuthorId());

        return bookService.update(id,bookDto.toBook(author.orElse(null))).map(DisplayBookDto::from);
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }
}
