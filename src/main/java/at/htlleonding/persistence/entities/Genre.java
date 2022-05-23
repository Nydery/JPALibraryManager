package at.htlleonding.persistence.entities;

import at.htlleonding.misc.BusinessKey;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.print.attribute.standard.Media;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre extends IdentityEntity {
    @Column(length = 256, nullable = false)
    @BusinessKey
    private String keyword;

    @OneToMany(mappedBy = "genre")
    private final Set<MediaItem> mediaItems = new HashSet<>();

    //-----------------

    public Genre() {
    }
    public Genre(String keyword) {
        this.keyword = keyword;
    }

    //-----------------

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
