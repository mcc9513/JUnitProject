
package org.example;

import org.example.Book;
import org.example.BookService;
import org.example.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

public class BookServiceTest {

    private BookService bookService;
    private Book bookMock;
    private User userMock;

    @Before
    public void setUp() {
        bookService = new BookService();  // Reinitializing should reset its state
        bookMock = Mockito.mock(Book.class);
        userMock = Mockito.mock(User.class);
    }

    // Test for searchBook method
    @Test
    public void testSearchBook_Found() {
        Mockito.when(bookMock.getTitle()).thenReturn("Test Title");
        Mockito.when(bookMock.getAuthor()).thenReturn("Test Author");
        Mockito.when(bookMock.getGenre()).thenReturn("Test Genre");
        bookService.addBook(bookMock);

        List<Book> foundBooks = bookService.searchBook("Test");
        assertFalse(foundBooks.isEmpty());
        assertEquals(1, foundBooks.size());
    }

    @Test
    public void testSearchBook_NotFound() {
        List<Book> foundBooks = bookService.searchBook("NonExistentBook");
        assertTrue(foundBooks.isEmpty());
    }

    @Test
    public void testSearchBook_EmptyKeyword() {
        List<Book> foundBooks = bookService.searchBook("");
        assertTrue(foundBooks.isEmpty());
    }

    @Test
    public void testSearchBook_NullKeyword() {
        List<Book> foundBooks = bookService.searchBook(null);
        assertTrue(foundBooks.isEmpty());
    }

    // Test for purchaseBook method
    @Test
    public void testPurchaseBook_Success() {
        bookService.addBook(bookMock);
        Mockito.when(userMock.getPurchasedBooks()).thenReturn(new ArrayList<>());

        assertTrue(bookService.purchaseBook(userMock, bookMock));
    }

    @Test
    public void testPurchaseBook_BookNotInDatabase() {
        Mockito.when(userMock.getPurchasedBooks()).thenReturn(new ArrayList<>());

        assertFalse(bookService.purchaseBook(userMock, bookMock));
    }

    @Test
    public void testPurchaseBook_UserNotRegistered() {
        assertFalse(bookService.purchaseBook(null, bookMock));
    }

    // Test for addBookReview method
    @Test
    public void testAddBookReview_Success() {
        bookService.addBook(bookMock);
        Mockito.when(userMock.getPurchasedBooks()).thenReturn(List.of(bookMock));
        Mockito.when(bookMock.getTitle()).thenReturn("Test Title");

        assertTrue(bookService.addBookReview(userMock, bookMock, "Great book!"));
    }

    @Test
    public void testAddBookReview_NotPurchased() {
        bookService.addBook(bookMock);
        Mockito.when(userMock.getPurchasedBooks()).thenReturn(new ArrayList<>());

        assertFalse(bookService.addBookReview(userMock, bookMock, "Great book!"));
    }

    @Test
    public void testAddBookReview_NullReview() {
        bookService.addBook(bookMock);
        Mockito.when(userMock.getPurchasedBooks()).thenReturn(List.of(bookMock));

        assertFalse(bookService.addBookReview(userMock, bookMock, null));
    }
}
