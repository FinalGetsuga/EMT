package mk.finki.ukim.wp.emt.service.impl;

import mk.finki.ukim.wp.emt.model.Country;
import mk.finki.ukim.wp.emt.model.dto.CountryDto;
import mk.finki.ukim.wp.emt.repository.CountryRepository;
import mk.finki.ukim.wp.emt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country save(CountryDto country) {
        Country newCountry = new Country();

        newCountry.setName(country.getName());

        return countryRepository.save(newCountry);
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public Country update(Long id, CountryDto country) {
        Country newCountry = countryRepository.findById(id).orElse(null);

        if (newCountry == null) {
            return null;
        }

        newCountry.setName(country.getName());

        return countryRepository.save(newCountry);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
