package mk.finki.ukim.wp.emt2.service.domain.impl;

import mk.finki.ukim.wp.emt2.model.domain.Author;
import mk.finki.ukim.wp.emt2.repository.AuthorRepository;
import mk.finki.ukim.wp.emt2.service.domain.AuthorService;
import mk.finki.ukim.wp.emt2.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(Author author) {
        if (author.getCountry() != null && countryService.findById(author.getCountry().getId()).isPresent()) {
            return Optional.of(
                    authorRepository.save(new Author(
                            author.getName(),
                            author.getSurname(),
                            countryService.findById(author.getCountry().getId()).get()
                    )));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        return authorRepository.findById(id).map(exAuthor -> {
            if (author.getName() != null) {
                exAuthor.setName(author.getName());
            }
            if (author.getSurname() != null) {
                exAuthor.setSurname(author.getSurname());
            }
            if (author.getCountry() != null && countryService.findById(author.getCountry().getId()).isPresent()) {
                exAuthor.setCountry(countryService.findById(author.getCountry().getId()).get());
            }

            return authorRepository.save(exAuthor);
        });
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
