package mk.finki.ukim.wp.emt2.service.application.impl;

import mk.finki.ukim.wp.emt2.dto.CreateCountryDto;
import mk.finki.ukim.wp.emt2.dto.DisplayCountryDto;
import mk.finki.ukim.wp.emt2.service.application.CountryApplicationService;
import mk.finki.ukim.wp.emt2.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto country) {
        return countryService.update(id, country.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto createCountryDto) {
        return countryService.save(createCountryDto.toCountry()).map(DisplayCountryDto::from);

    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
