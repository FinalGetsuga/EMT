package mk.finki.ukim.wp.emt2.service.application;

import mk.finki.ukim.wp.emt2.dto.CreateAuthorDto;
import mk.finki.ukim.wp.emt2.dto.DisplayAuthorDto;
import mk.finki.ukim.wp.emt2.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    List<DisplayAuthorDto> findAll();

    Optional<DisplayAuthorDto> findById(Long id);

    Optional<DisplayAuthorDto> save(CreateAuthorDto authorDto);

    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto authorDto);

    void deleteById(Long id);
}
