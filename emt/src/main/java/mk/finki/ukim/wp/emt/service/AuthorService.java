package mk.finki.ukim.wp.emt.service;

import mk.finki.ukim.wp.emt.model.Author;
import mk.finki.ukim.wp.emt.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author save(AuthorDto author);

    Author findById(Long id);

    Author update(Long id, AuthorDto author);

    void deleteById(Long id);

}
