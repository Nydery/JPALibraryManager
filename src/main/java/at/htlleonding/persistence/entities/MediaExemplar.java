package at.htlleonding.persistence.entities;

import javax.persistence.*;

@Entity
public class MediaExemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne
    private MediaItem mediaItem;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public MediaItem getMediaItem() {
        return mediaItem;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }
}
