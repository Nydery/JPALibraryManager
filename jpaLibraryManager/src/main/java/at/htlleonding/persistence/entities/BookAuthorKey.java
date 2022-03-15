package at.htlleonding.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorKey implements Serializable {
    @Column
    private long bookId;
    @Column
    private long authorId;

    public long getBookId() {
        return bookId;
    }
    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getAuthorId() {
        return authorId;
    }
    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public BookAuthorKey() {
    }
    public BookAuthorKey(long bookId, long authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, authorId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookAuthorKey key1 = (BookAuthorKey) obj;
        if (bookId != key1.bookId) return false;
        return authorId == key1.authorId;
    }
}
