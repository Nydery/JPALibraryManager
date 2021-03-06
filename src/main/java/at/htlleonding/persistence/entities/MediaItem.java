package at.htlleonding.persistence.entities;

import at.htlleonding.misc.BusinessKey;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MediaItem extends IdentityEntity{
    @Column(nullable = false)
    @BusinessKey
    private String title;

    @ManyToMany
    @JoinTable(
            name = "MediaItem_Topic",
            joinColumns = @JoinColumn(name = "mediaItem_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> topics = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "MediaItem_Author",
            joinColumns = @JoinColumn(name = "mediaItem_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Genre genre;

    // ----------------

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    //Add for testing purpose in logic (addMediaItem)
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
