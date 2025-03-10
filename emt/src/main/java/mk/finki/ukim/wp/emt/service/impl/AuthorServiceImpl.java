package mk.finki.ukim.wp.emt.service.impl;

import mk.finki.ukim.wp.emt.model.Author;
import mk.finki.ukim.wp.emt.model.Country;
import mk.finki.ukim.wp.emt.model.dto.AuthorDto;
import mk.finki.ukim.wp.emt.repository.AuthorRepository;
import mk.finki.ukim.wp.emt.service.AuthorService;
import mk.finki.ukim.wp.emt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Author save(AuthorDto author) {
        Author a = new Author();
        return saveAuthor(author, a);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author update(Long id, AuthorDto author) {
        Author a = this.findById(id);

        if (a == null){
            return null;
        }

        return saveAuthor(author, a);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    private Author saveAuthor(AuthorDto author, Author a)
    {
        Country country = countryService.findById(author.getCountry());

        if (country == null){
            return null;
        }

        a.setName(author.getName());
        a.setSurname(author.getSurname());
        a.setCountry(country);

        return authorRepository.save(a);
    }
}
