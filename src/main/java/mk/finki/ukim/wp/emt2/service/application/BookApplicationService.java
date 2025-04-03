package mk.finki.ukim.wp.emt2.service.application;

import mk.finki.ukim.wp.emt2.dto.CreateBookDto;
import mk.finki.ukim.wp.emt2.dto.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {

    List<DisplayBookDto> findAll();

    Optional<DisplayBookDto> findById(Long id);

    Optional<DisplayBookDto> save(CreateBookDto bookDto);

    Optional<DisplayBookDto> update(Long id, CreateBookDto bookDto);

    void deleteById(Long id);

}
