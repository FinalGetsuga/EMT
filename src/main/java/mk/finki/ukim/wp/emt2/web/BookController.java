package mk.finki.ukim.wp.emt2.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.wp.emt2.dto.CreateBookDto;
import mk.finki.ukim.wp.emt2.dto.DisplayBookDto;
import mk.finki.ukim.wp.emt2.service.application.BookApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "Endpoints for managing books")
public class BookController {

    private final BookApplicationService bookApplicationService;

    public BookController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all available books.")
    @GetMapping
    public List<DisplayBookDto> findAll() {
        return bookApplicationService.findAll();
    }

    @Operation(summary = "Get book by ID", description = "Finds a book by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new book", description = "Creates a new book based on the given BookDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.save(createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing book", description = "Updates a book by ID using BookDto.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id, @RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.update(id, createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookApplicationService.findById(id).isPresent()) {
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
