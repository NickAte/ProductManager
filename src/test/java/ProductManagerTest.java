import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Book book1 = new Book(1, "The One", 200, "Ivan");
    Book book2 = new Book(22, "One of Two", 300, "Pavel");
    Book book3 = new Book(3, "Two Thrones", 100, "Oleg");

    @Test
    public void TwoBooks() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] actual = manager.searchBy("One");
        Product[] expected = {book1, book2};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void OneBooks() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] actual = manager.searchBy("The");
        Product[] expected = {book1};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void NoBooks() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] actual = manager.searchBy("Ring");
        Product[] expected = {};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void FindAll() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] actual = manager.repo.findAll();
        Product[] expected = {book1, book2, book3};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void RemoveById() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Product[] actual = manager.repo.removeById(1);
        Product[] expected = {book2, book3};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void RemoveByNonexistentId() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(44);
        });
    }
}
