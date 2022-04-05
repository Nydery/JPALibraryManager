package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sale extends IdentityEntity{

    @Column
    private LocalDate datetime;

    @ManyToOne
    private Receipt receipt;

    @ManyToMany(mappedBy = "sales")
    private Set<MediaExemplar> mediaExemplars = new HashSet<>();

    //-----------------

    public Sale(){}

    public Sale(LocalDate datetime) {
        this.datetime = datetime;
    }

    //-----------------

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    public Set<MediaExemplar> getMediaExemplars() {
        return mediaExemplars;
    }
}
