package mk.finki.ukim.wp.emt.web;

import mk.finki.ukim.wp.emt.model.Country;
import mk.finki.ukim.wp.emt.model.dto.CountryDto;
import mk.finki.ukim.wp.emt.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        Country country = countryService.findById(id);

        if (country == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(country);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto country) {
        Country newCountry = countryService.save(country);

        if (newCountry == null) {
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(newCountry);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody CountryDto country) {
        Country newCountry = countryService.update(id, country);

        if (newCountry == null) {
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(newCountry);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> deleteById(@PathVariable Long id) {
        Country country = countryService.findById(id);

        if (country == null) {
            return ResponseEntity.notFound().build();
        }else{
            countryService.deleteById(id);
            return ResponseEntity.ok(country);
        }
    }
}
