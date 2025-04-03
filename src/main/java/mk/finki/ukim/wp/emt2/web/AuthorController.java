package mk.finki.ukim.wp.emt2.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.finki.ukim.wp.emt2.dto.CreateAuthorDto;
import mk.finki.ukim.wp.emt2.dto.DisplayAuthorDto;
import mk.finki.ukim.wp.emt2.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @Operation(summary = "Get all authors", description = "Retrieves a list of all available authors.")
    @GetMapping
    public List<DisplayAuthorDto> findAll() {
        return authorApplicationService.findAll();
    }

    @Operation(summary = "Get author by ID", description = "Finds a author by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new author", description = "Creates a new author based on the given AuthorDto.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.save(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing author", description = "Updates a author by ID using AuthorDto.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAuthorDto> update(@PathVariable Long id, @RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.update(id, createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a author", description = "Deletes a author by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (authorApplicationService.findById(id).isPresent()) {
            authorApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
