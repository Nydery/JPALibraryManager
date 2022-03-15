package at.htlleonding.persistence.entities;

import javax.persistence.*;

@Entity
public class BookAuthor {
    @EmbeddedId
    private BookAuthorKey id = new BookAuthorKey();

    @ManyToOne
    @MapsId("authorId")
    //@JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @MapsId("bookId")
    //@JoinColumn(name = "book_id")
    private Book book;
    private boolean primaryAuthor;

    public BookAuthor() {
    }
    public BookAuthor(Book book, Author author, boolean primaryAuthor) {
        this.id.setBookId(book.getId());
        this.id.setAuthorId(author.getId());
        this.author = author;
        this.book = book;
        this.primaryAuthor = primaryAuthor;
    }

    public BookAuthorKey getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
        this.id.setAuthorId(author.getId());
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
        this.id.setBookId(book.getId());
    }

    public boolean isPrimaryAuthor() {
        return primaryAuthor;
    }
    public void setPrimaryAuthor(boolean primaryAuthor) {
        this.primaryAuthor = primaryAuthor;
    }
}
