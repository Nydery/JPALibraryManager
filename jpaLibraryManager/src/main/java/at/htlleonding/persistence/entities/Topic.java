package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 256)
    private String keyword;

    @ManyToMany(mappedBy = "topics")
    private Set<Book> books = new HashSet<>();

    //-----------------

    public Topic() {
    }
    public Topic(String keyword) {
        this.keyword = keyword;
    }

    //-----------------

    public long getId() {
        return id;
    }
    /*public void setId(long id) {
        this.id = id;
    }*/

    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Set<Book> getBooks() {
        return books;
    }
}
