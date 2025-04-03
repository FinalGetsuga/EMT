package mk.finki.ukim.wp.emt2.config;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.emt2.model.domain.Author;
import mk.finki.ukim.wp.emt2.model.domain.Book;
import mk.finki.ukim.wp.emt2.model.domain.User;
import mk.finki.ukim.wp.emt2.model.domain.Country;
import mk.finki.ukim.wp.emt2.model.enumerations.Category;
import mk.finki.ukim.wp.emt2.model.enumerations.Role;
import mk.finki.ukim.wp.emt2.repository.AuthorRepository;
import mk.finki.ukim.wp.emt2.repository.BookRepository;
import mk.finki.ukim.wp.emt2.repository.CountryRepository;
import mk.finki.ukim.wp.emt2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            CountryRepository countryRepository,
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Country country = new Country("France","Europe");
        Author author = new Author("Toni","Vasquez",country);
        Book book = new Book("Red",Category.THRILER,author,10);

        countryRepository.save(country);
        authorRepository.save(author);
        bookRepository.save(book);

        userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "test",
                "test",
                Role.ROLE_LIBRARIAN
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));
    }
}

