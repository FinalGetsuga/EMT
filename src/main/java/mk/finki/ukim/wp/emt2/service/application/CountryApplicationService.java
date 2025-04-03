package mk.finki.ukim.wp.emt2.service.application;

import mk.finki.ukim.wp.emt2.dto.CreateCountryDto;
import mk.finki.ukim.wp.emt2.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;


public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);

    Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto);

    void deleteById(Long id);
}
