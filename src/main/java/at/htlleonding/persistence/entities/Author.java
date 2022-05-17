package at.htlleonding.persistence.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author extends Person{
    @ManyToMany(mappedBy = "authors")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private final Set<MediaItem> mediaItems = new HashSet<>();

    //-----------------

    public Author() {
    }

    public Author(String firstName, String lastName) {
        super(firstName, lastName);
    }

    //-----------------

    public Set<MediaItem> getMediaItems() {
        return mediaItems;
    }



}
