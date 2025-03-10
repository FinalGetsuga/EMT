package mk.finki.ukim.wp.emt.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.emt.model.Author;
import mk.finki.ukim.wp.emt.model.Book;
import mk.finki.ukim.wp.emt.model.Country;
import mk.finki.ukim.wp.emt.model.enumerations.Category;
import mk.finki.ukim.wp.emt.repository.AuthorRepository;
import mk.finki.ukim.wp.emt.repository.BookRepository;
import mk.finki.ukim.wp.emt.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        if (countryRepository.count() != 0 || authorRepository.count() != 0 || bookRepository.count() != 0) {
            return;
        }

        for (int i = 1; i < 11; i++) {
            create(i);
        }
    }

    private void create(int i) {
        Country country = new Country();
        country.setName(String.format("Country %d", i));
        country.setContinent(String.format("Continent %d", i));
        countryRepository.save(country);

        Author author = new Author();
        author.setName(String.format("Name %d", i));
        author.setSurname(String.format("Surname %d", i));
        author.setCountry(country);
        authorRepository.save(author);

        Book book = new Book();
        book.setTitle(String.format("Title %d", i));
        book.setCategory(Category.values()[i % Category.values().length]);
        book.setAuthor(author);
        book.setAvailableCopies(i);
        bookRepository.save(book);
    }
}
