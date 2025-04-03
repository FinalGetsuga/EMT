package mk.finki.ukim.wp.emt2.dto;

import mk.finki.ukim.wp.emt2.model.domain.Author;
import mk.finki.ukim.wp.emt2.model.domain.Book;
import mk.finki.ukim.wp.emt2.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(Long id, String Title, Category category, Long AuthorId, Integer AvailableCopies) {

    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getId(),
                book.getTitle(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(Title, category, author, AvailableCopies);
    }
}
