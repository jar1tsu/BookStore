package bookstore.web;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bookstore.domain.Book;
import bookstore.domain.BookRepository;
import bookstore.domain.Category;
import bookstore.domain.CategoryRepository;




@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        }
    
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/books/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }
    @PostMapping("/books/save")
    public String saveBook(@ModelAttribute Book book, @RequestParam("categoryId") Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Ei ole oikea Id:" + categoryId));
        book.setCategory(category);
        bookRepository.save(book); 
        return "redirect:/books";
    }
    @GetMapping("/books/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ei ole oikea Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }

    @PostMapping("books/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Book updatedBook, @RequestParam("categoryId") Long categoryId) {
        updatedBook.setId(id);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Ei ole oikea Id:" + categoryId));
        updatedBook.setCategory(category);
        bookRepository.save(updatedBook);
        return "redirect:/books";
    }
    
    @GetMapping("/books/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
    
    
}
