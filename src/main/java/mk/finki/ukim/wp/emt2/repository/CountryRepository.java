package mk.finki.ukim.wp.emt2.repository;

import mk.finki.ukim.wp.emt2.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
