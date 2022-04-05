package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Topic extends IdentityEntity {

    @Column(length = 256, nullable = false)
    private String keyword;

    @ManyToMany(mappedBy = "topics")
    private Set<MediaItem> mediaItems = new HashSet<>();

    //-----------------

    public Topic() {
    }
    public Topic(String keyword) {
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
