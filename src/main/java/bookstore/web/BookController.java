package bookstore.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import bookstore.model.Book;
import bookstore.model.BookRepository;




@Controller
public class BookController {

    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        }
    

    @GetMapping("/books")
    public String getBooks(Model model) {
        
        model.addAttribute("books", bookRepository.findAll());
        return "/books";
    }

    @GetMapping("/books/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }
    @PostMapping("/books/save")
    public String saveBook(@ModelAttribute Book book) {   
        bookRepository.save(book);     
        return "redirect:/books";
    }
    @GetMapping("/books/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ei ole oikea Id:" + id));
        model.addAttribute("book", book);
        return "editbook";
    }

    @PostMapping("books/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
        return "redirect:/books";
    }
    
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
    
    
}
