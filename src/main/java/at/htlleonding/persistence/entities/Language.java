package at.htlleonding.persistence.entities;

import at.htlleonding.misc.BusinessKey;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Language extends IdentityEntity{
    @Column(length = 256, nullable = false)
    @BusinessKey
    private String keyword;

    @OneToMany(mappedBy = "language")
    private final Set<MediaExemplar> mediaExemplars = new HashSet<>();

    //-----------------

    public Language() {
    }
    public Language(String keyword) {
        this.keyword = keyword;
    }

    //-----------------

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
