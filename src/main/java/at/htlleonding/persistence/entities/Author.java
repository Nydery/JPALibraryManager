package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author extends Person{
    @ManyToMany(mappedBy = "authors")

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
