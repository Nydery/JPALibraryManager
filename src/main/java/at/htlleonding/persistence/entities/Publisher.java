package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher extends IdentityEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<MediaExemplar> mediaExemplars = new HashSet<>();

    // ---------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MediaExemplar> getMediaExemplars() {
        return mediaExemplars;
    }
}
