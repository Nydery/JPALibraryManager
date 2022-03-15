package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MediaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @ManyToMany(mappedBy = "mediaItems")
    @JoinTable(
            name = "MediaItem_Topic",
            joinColumns = @JoinColumn(name = "mediaItem_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "mediaItem")
    private Set<MediaExemplar> mediaExemplars = new HashSet<>();

}