package bookstore;

import bookstore.model.Book;
import bookstore.model.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(BookRepository bookRepository) {
    return (args) -> {
       
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
