package mk.finki.ukim.wp.emt.web;

import mk.finki.ukim.wp.emt.model.Author;
import mk.finki.ukim.wp.emt.model.dto.AuthorDto;
import mk.finki.ukim.wp.emt.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll(){
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        Author author = authorService.findById(id);

        if (author == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(author);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto author){
        Author newAuthor = authorService.save(author);

        if (newAuthor == null) {
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(newAuthor);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody AuthorDto author){
        Author newAuthor = authorService.update(id, author);

        if (newAuthor == null) {
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(newAuthor);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteById(@PathVariable Long id){
       Author author = authorService.findById(id);

       if (author == null) {
           return ResponseEntity.notFound().build();
       }else{
           authorService.deleteById(id);
           return ResponseEntity.ok(author);
       }
    }

}
