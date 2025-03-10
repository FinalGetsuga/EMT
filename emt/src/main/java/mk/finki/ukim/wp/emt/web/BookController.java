package mk.finki.ukim.wp.emt.web;

import mk.finki.ukim.wp.emt.model.Book;
import mk.finki.ukim.wp.emt.model.dto.BookDto;
import mk.finki.ukim.wp.emt.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        Book book = this.bookService.findById(id);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(book);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save (@RequestBody BookDto book){
        Book newBook = this.bookService.save(book);

        if (newBook == null) {
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(newBook);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookDto book) {
        Book newBook = this.bookService.update(id, book);

        if (newBook == null) {
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(newBook);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        Book book = this.bookService.findById(id);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }else{
            bookService.deleteById(id);
            return ResponseEntity.ok(book);
        }
    }

}
