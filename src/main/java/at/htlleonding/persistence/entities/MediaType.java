package at.htlleonding.persistence.entities;

import at.htlleonding.misc.BusinessKey;
import at.htlleonding.persistence.enums.MediaTypes;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MediaType extends IdentityEntity{
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private MediaTypes type;

    @Column(nullable = false)
    @BusinessKey
    private double price;

    @OneToMany(mappedBy = "mediaType")
    private Set<MediaExemplar> mediaExemplars = new HashSet<>();

    // ----------------------

    public MediaTypes getType() {
        return type;
    }
    public void setType(MediaTypes type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Set<MediaExemplar> getMediaExemplars() {
        return mediaExemplars;
    }
}
