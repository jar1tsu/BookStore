package bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import bookstore.domain.Book;
import bookstore.domain.BookRepository;
import bookstore.domain.Category;
import bookstore.domain.CategoryRepository;
import bookstore.domain.User;
import bookstore.domain.UserRepository;

@SpringBootTest
public class RepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    // --- BookRepository ---

    @Test
    public void createNewBook() {
        Book book = new Book("Test Book", "Test Author", 2024, "1234567890", 9.99);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void findByTitleShouldReturnBook() {
        Book book = new Book("Unique Test Title", "Test Author", 2024, "1234567890", 9.99);
        bookRepository.save(book);
        List<Book> books = bookRepository.findByTitle("Unique Test Title");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Test Author");
    }

    @Test
    public void deleteBook() {
        Book book = new Book("Delete Me", "Author", 2024, "0000000000", 5.00);
        bookRepository.save(book);
        List<Book> books = bookRepository.findByTitle("Delete Me");
        bookRepository.delete(books.get(0));
        List<Book> deletedBooks = bookRepository.findByTitle("Delete Me");
        assertThat(deletedBooks).hasSize(0);
    }

    // --- CategoryRepository ---

    @Test
    public void createNewCategory() {
        Category category = new Category("Sci-Fi");
        categoryRepository.save(category);
        assertThat(category.getId()).isNotNull();
    }

    @Test
    public void findByNameShouldReturnCategory() {
        Category category = new Category("Unique Test Category");
        categoryRepository.save(category);
        List<Category> categories = categoryRepository.findByName("Unique Test Category");
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Unique Test Category");
    }

    @Test
    public void deleteCategory() {
        Category category = new Category("Delete Me");
        categoryRepository.save(category);
        List<Category> categories = categoryRepository.findByName("Delete Me");
        categoryRepository.delete(categories.get(0));
        List<Category> deletedCategories = categoryRepository.findByName("Delete Me");
        assertThat(deletedCategories).hasSize(0);
    }

    // --- UserRepository ---

    @Test
    public void createNewUser() {
        User user = new User("testuser", "password123", "test@example.com", "ROLE_USER");
        userRepository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void findByUsernameShouldReturnUser() {
        User user = new User("finduser", "password123", "find@example.com", "ROLE_USER");
        userRepository.save(user);
        User found = userRepository.findByUsername("finduser");
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo("find@example.com");
    }

    @Test
    public void deleteUser() {
        User user = new User("deleteuser", "password123", "delete@example.com", "ROLE_USER");
        userRepository.save(user);
        userRepository.delete(user);
        User deleted = userRepository.findByUsername("deleteuser");
        assertThat(deleted).isNull();
    }
}