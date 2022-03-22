package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 256, nullable = false)
    private String keyword;

    @OneToMany(mappedBy = "language")
    private Set<MediaExemplar> mediaExemplars = new HashSet<>();

    //-----------------

    public Language() {
    }
    public Language(String keyword) {
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

    public Set<MediaExemplar> getMediaExemplars() {
        return mediaExemplars;
    }
}
