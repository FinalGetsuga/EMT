package mk.finki.ukim.wp.emt2.dto;

import mk.finki.ukim.wp.emt2.model.domain.Author;
import mk.finki.ukim.wp.emt2.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAuthorDto(String Name, String Surname, Long CountryId) {

    public static CreateAuthorDto from(Author author) {
        return new CreateAuthorDto(
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public static List<CreateAuthorDto> from(List<Author> authors) {
        return authors.stream().map(CreateAuthorDto::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(Name,Surname,country);
    }

}
