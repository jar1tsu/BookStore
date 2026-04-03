package bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Value;

import bookstore.domain.Book;
import bookstore.domain.BookRepository;
import bookstore.domain.CategoryRepository;
import bookstore.domain.User;
import bookstore.domain.UserRepository;




@SpringBootApplication
public class BookstoreApplication {

    @Value("${app.user.password}")
    private String userPassword;

    @Value("${app.admin.password}")
    private String adminPassword;

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        return (args) -> {

            if (categoryRepository.count() == 0) {
                log.info("luodaan kategoria");
                categoryRepository.save(new bookstore.domain.Category("Fiction"));
                categoryRepository.save(new bookstore.domain.Category("Non-fiction"));
                categoryRepository.save(new bookstore.domain.Category("Science"));
            }

            if (bookRepository.count() == 0) {
                log.info("muutama kirja");
                bookRepository.save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, "9780743273565", 10.99));
                bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780061120084", 7.99));
                bookRepository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 8.99));
            }


            log.info("haetaan kaikki kirjat");
            for (Book book : bookRepository.findAll()) {
                log.info(book.toString());
            }

            if (userRepository.count()== 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                userRepository.save(new User("user", encoder.encode(userPassword), "user@example.com", "ROLE_USER"));
                userRepository.save(new User("admin", encoder.encode(adminPassword), "admin@example.com", "ROLE_ADMIN"));
                log.info("Käyttäjät luotu tietokantaan");
        }
    };

    }
}
