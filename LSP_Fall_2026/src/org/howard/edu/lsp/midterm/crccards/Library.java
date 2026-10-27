
package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages books and coordinates borrowing and returning.
 * Enforces the library's borrowing limit and supports book searches.
 *
 * @author Your Name
 */
public class Library {

    private final Map<String, Book> books;
    private static final int BORROWING_LIMIT = 3;

    /**
     * Creates an empty library.
     */
    public Library() {
        books = new LinkedHashMap<String, Book>();
    }

    /**
     * Adds a book to the library.
     *
     * @param book the book to add
     * @throws IllegalArgumentException if the ISBN already exists
     */
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Duplicate ISBN.");
        }

        books.put(book.getIsbn(), book);
    }

    /**
     * Finds a book by its ISBN.
     *
     * @param isbn the ISBN to search for
     * @return the matching book, or null if not found
     */
    public Book findBook(String isbn) {
        return books.get(isbn);
    }

    /**
     * Borrows a book from the library.
     *
     * @param isbn the ISBN of the book to borrow
     * @throws IllegalArgumentException if the ISBN is not found
     * @throws IllegalStateException if the book is already borrowed
     * or the borrowing limit has been reached
     */
    public void borrowBook(String isbn) {
        Book book = findBook(isbn);

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is already borrowed.");
        }

        if (getBorrowedBookCount() >= BORROWING_LIMIT) {
            throw new IllegalStateException("Borrowing limit reached.");
        }

        book.borrowBook();
    }

    /**
     * Returns a borrowed book to the library.
     *
     * @param isbn the ISBN of the book to return
     * @throws IllegalArgumentException if the ISBN is not found
     * @throws IllegalStateException if the book is already available
     */
    public void returnBook(String isbn) {
        Book book = findBook(isbn);

        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }

        book.returnBook();
    }

    /**
     * Retrieves all currently available books.
     *
     * @return a list of available books
     */
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<Book>();

        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }

        return availableBooks;
    }

    /**
     * Retrieves books by an exact, case-sensitive author name.
     *
     * @param author the author to search for
     * @return a list of matching books, including borrowed books
     */
    public List<Book> getBooksByAuthor(String author) {
        List<Book> authorBooks = new ArrayList<Book>();

        for (Book book : books.values()) {
            if (book.getAuthor().equals(author)) {
                authorBooks.add(book);
            }
        }

        return authorBooks;
    }

    /**
     * Retrieves all books sorted by ISBN in ascending order.
     *
     * @return a new list of books sorted by ISBN
     */
    public List<Book> getBooksSortedByIsbn() {
        List<Book> sortedBooks = new ArrayList<Book>(books.values());

        Collections.sort(sortedBooks, new Comparator<Book>() {
            @Override
            public int compare(Book first, Book second) {
                return first.getIsbn().compareTo(second.getIsbn());
            }
        });

        return sortedBooks;
    }

    /**
     * Counts the books that are currently borrowed.
     *
     * @return the number of borrowed books
     */
    public int getBorrowedBookCount() {
        int count = 0;

        for (Book book : books.values()) {
            if (!book.isAvailable()) {
                count++;
            }
        }

        return count;
    }
}
