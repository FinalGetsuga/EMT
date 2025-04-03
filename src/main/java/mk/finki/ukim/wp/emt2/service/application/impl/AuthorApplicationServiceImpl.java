package mk.finki.ukim.wp.emt2.service.application.impl;

import mk.finki.ukim.wp.emt2.dto.CreateAuthorDto;
import mk.finki.ukim.wp.emt2.dto.DisplayAuthorDto;
import mk.finki.ukim.wp.emt2.model.domain.Country;
import mk.finki.ukim.wp.emt2.service.application.AuthorApplicationService;
import mk.finki.ukim.wp.emt2.service.domain.AuthorService;
import mk.finki.ukim.wp.emt2.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    @Override
    public List<DisplayAuthorDto> findAll() {
        return authorService.findAll().stream().map(DisplayAuthorDto::from).toList();
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto authorDto) {

        Optional<Country> country = countryService.findById(authorDto.CountryId());

        if (country.isPresent()) {
            return authorService.save(authorDto.toAuthor(country.get())).map(DisplayAuthorDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto authorDto) {

        Optional<Country> country = countryService.findById(authorDto.CountryId());

        return authorService.update(id,authorDto.toAuthor(country.orElse(null))).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }
}
