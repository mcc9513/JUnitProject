package org.example;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        bookList.add(book);
    }

    public List<Book> searchBook(String keyword) {
        List<Book> result = new ArrayList<>();
        if (keyword == null || keyword.isEmpty()) {
            return result;
        }

        for (Book book : bookList) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword) || book.getGenre().contains(keyword)) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean purchaseBook(User user, Book book) {
        if (user == null || !bookList.contains(book)) {
            return false;
        }

        user.getPurchasedBooks().add(book);
        return true;
    }

    public boolean addBookReview(User user, Book book, String review) {
        if (review == null || review.isEmpty()) {
            return false;
        }
        if (user.getPurchasedBooks().contains(book)) {
            book.addReview(review);
            return true;
        }
        return false;
    }

    public void clearBooks() {
        bookList.clear();
    }
}
