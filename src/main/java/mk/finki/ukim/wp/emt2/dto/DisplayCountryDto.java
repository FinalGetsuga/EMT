package mk.finki.ukim.wp.emt2.dto;

import lombok.Data;
import mk.finki.ukim.wp.emt2.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

@Data
public record DisplayCountryDto(Long id, String name, String continent) {

    public static DisplayCountryDto from(Country country) {
        return new DisplayCountryDto(country.getId(), country.getName(), country.getContinent());
    }

    public static List<DisplayCountryDto> from(List<Country> countries) {
        return countries.stream().map(DisplayCountryDto::from).collect(Collectors.toList());
    }
}
