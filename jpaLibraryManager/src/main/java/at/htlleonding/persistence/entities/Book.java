package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 256)
    private String title;

    @ManyToOne
    private Genre genre;

    @ManyToMany
    @JoinTable(
            name = "Book_Topic",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<BookAuthor> authors = new HashSet<>();

    public Set<BookAuthor> getAuthors() {
        return authors;
    }

    //-----------------

    public Book() {
    }
    public Book(String title) {
        this.title = title;
    }

    //-----------------

    public long getId() {
        return id;
    }
    /*public void setId(long id) {
        this.id = id;
    }*/

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Topic> getTopics() {
        return topics;
    }
}
