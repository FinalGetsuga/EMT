package mk.finki.ukim.wp.emt2.repository;

import mk.finki.ukim.wp.emt2.model.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
