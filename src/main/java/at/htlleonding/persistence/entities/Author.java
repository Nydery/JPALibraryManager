package at.htlleonding.persistence.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author extends Person{
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
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
