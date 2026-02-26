package bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.domain.Book;
import bookstore.domain.BookRepository;
import bookstore.domain.CategoryRepository;



@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
    return (args) -> {

        if (categoryRepository.count() == 0) {
            log.info("luodaan kategoria");
            categoryRepository.save(new bookstore.domain.Category("Fiction"));
            categoryRepository.save(new bookstore.domain.Category("Non-fiction"));
            categoryRepository.save(new bookstore.domain.Category("Science"));
        }
       
        log.info("muutama kirja");
        bookRepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "9780743273565", 10.99));
        bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780061120084", 7.99));
        bookRepository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 8.99));

        log.info("haetaan kaikki kirjat");
        for (Book book : bookRepository.findAll()) {
            log.info(book.toString());
        }
    };

    }
}
