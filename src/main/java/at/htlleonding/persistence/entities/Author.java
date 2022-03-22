package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String name;

    @ManyToMany(mappedBy = "authors")
    private Set<MediaItem> mediaItems = new HashSet<>();

    //-----------------

    public Author() {
    }

    public Author(String firstName, String lastName, String name) {
        super(firstName, lastName);
        this.name = name;
    }

    //-----------------


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MediaItem> getMediaItems() {
        return mediaItems;
    }
}
