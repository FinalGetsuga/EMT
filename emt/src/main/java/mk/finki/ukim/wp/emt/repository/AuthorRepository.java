package mk.finki.ukim.wp.emt.repository;

import mk.finki.ukim.wp.emt.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
