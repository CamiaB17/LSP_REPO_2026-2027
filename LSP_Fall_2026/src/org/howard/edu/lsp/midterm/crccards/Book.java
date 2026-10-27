
package org.howard.edu.lsp.midterm.crccards;

/**
 * Represents a book in a library lending system.
 * Stores the book's ISBN, title, author, and availability.
 *
 * @author Your Name
 */
public class Book {

    private final String isbn;
    private final String title;
    private final String author;
    private boolean available;

    /**
     * Creates a new book that is initially available.
     *
     * @param isbn the book's ISBN
     * @param title the book's title
     * @param author the book's author
     */
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    /**
     * Returns the book's ISBN.
     *
     * @return the ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the book's title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the book's author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Checks whether the book is available.
     *
     * @return true if the book is available; false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Borrows the book if it is available.
     *
     * @throws IllegalStateException if the book is already borrowed
     */
    public void borrowBook() {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed.");
        }

        available = false;
    }

    /**
     * Returns the book if it is currently borrowed.
     *
     * @throws IllegalStateException if the book is already available
     */
    public void returnBook() {
        if (available) {
            throw new IllegalStateException("Book is already available.");
        }

        available = true;
    }

    /**
     * Returns a string describing the book and its current status.
     *
     * @return the book's details and availability
     */
    @Override
    public String toString() {
        if (available) {
            return isbn + " " + title + " by " + author + " [AVAILABLE]";
        } else {
            return isbn + " " + title + " by " + author + " [BORROWED]";
        }
    }
}

