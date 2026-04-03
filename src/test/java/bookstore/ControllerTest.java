package bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bookstore.domain.BookRepository;
import bookstore.domain.CategoryRepository;
import bookstore.web.BookController;
import bookstore.web.RestBookController;
import bookstore.web.UserDetailService;

@SpringBootTest
public class ControllerTest {

    @Autowired
    private BookController bookController;

    @Autowired
    private RestBookController restBookController;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserDetailService userDetailService;

    @Test
    public void bookControllerLoads() throws Exception {
        assertThat(bookController).isNotNull();
    }

    @Test
    public void restBookControllerLoads() throws Exception {
        assertThat(restBookController).isNotNull();
    }

    @Test
    public void bookControllerCanFetchBooks() throws Exception {
        assertThat(bookRepository.findAll()).isNotNull();
    }

    @Test
    public void bookControllerCanFetchCategories() throws Exception {
        assertThat(categoryRepository.findAll()).isNotNull();
    }

    @Test
    public void userDetailServiceLoads() throws Exception {
        assertThat(userDetailService).isNotNull();
    }
}
