package mk.finki.ukim.wp.emt2.service.domain.impl;

import mk.finki.ukim.wp.emt2.model.domain.Country;
import mk.finki.ukim.wp.emt2.repository.CountryRepository;
import mk.finki.ukim.wp.emt2.service.domain.CountryService;
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
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id).map(exCountry -> {
            if (country.getName() != null) {
                exCountry.setName(country.getName());
            }
            if (country.getContinent() != null) {
                exCountry.setContinent(country.getContinent());
            }
            return countryRepository.save(exCountry);
        });
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
