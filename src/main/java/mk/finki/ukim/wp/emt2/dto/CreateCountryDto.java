package mk.finki.ukim.wp.emt2.dto;

import mk.finki.ukim.wp.emt2.model.domain.Country;

public record CreateCountryDto(String name, String continent) {

    public Country toCountry() {
        return new Country(name, continent);
    }
}
