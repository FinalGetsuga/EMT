package mk.finki.ukim.wp.emt2.dto;

import mk.finki.ukim.wp.emt2.model.domain.Author;
import mk.finki.ukim.wp.emt2.model.domain.Book;
import mk.finki.ukim.wp.emt2.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(String Title, Category category, Long AuthorId, Integer AvailableCopies) {

    public static CreateBookDto from(Book book) {
        return new CreateBookDto(
                book.getTitle(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<CreateBookDto> from(List<Book> books) {
        return books.stream().map(CreateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(Title, category, author, AvailableCopies);
    }
}
