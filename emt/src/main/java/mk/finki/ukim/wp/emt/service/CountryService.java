package mk.finki.ukim.wp.emt.service;

import mk.finki.ukim.wp.emt.model.Country;
import mk.finki.ukim.wp.emt.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Country save(CountryDto country);

    Country findById(Long id);

    Country update(Long id, CountryDto country);

    void deleteById(Long id);
}
