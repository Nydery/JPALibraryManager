package at.htlleonding.persistence.entities;

import javax.persistence.*;
import javax.print.attribute.standard.Media;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 256, nullable = false)
    private String keyword;

    @OneToMany(mappedBy = "genre")
    private Set<MediaItem> mediaItems = new HashSet<>();

    //-----------------

    public Genre() {
    }
    public Genre(String keyword) {
        this.keyword = keyword;
    }

    //-----------------


    public long getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Set<MediaItem> getMediaItems() {
        return mediaItems;
    }
}
