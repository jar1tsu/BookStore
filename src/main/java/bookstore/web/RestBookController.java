package bookstore.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookstore.domain.Book;
import bookstore.domain.BookRepository;


@RestController
@RequestMapping("/api")
public class RestBookController {

    private final BookRepository bookRepository;

    public RestBookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

@GetMapping("/books")
public Iterable<Book> findAllBooks() {
    return bookRepository.findAll();
}

@GetMapping("/books/{id}")
public Book getBookById(@PathVariable Long id) {
    return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ei ole oikea Id:" + id));
}

@PostMapping("/books")
public Book saveBook(@RequestBody Book book) {
    return bookRepository.save(book);
}

@PutMapping("books/{id}")
public Book saveEditedBook(@RequestBody Book editedBook, @PathVariable Long id) {
    editedBook.setId(id);
    return bookRepository.save(editedBook);
}

@DeleteMapping("/books/{id}")
public void deleteBook(@PathVariable Long id) {
    bookRepository.deleteById(id);    
}

}
