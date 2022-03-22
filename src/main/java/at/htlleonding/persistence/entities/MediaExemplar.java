package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MediaExemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    
    private LocalDate buyDate;
    private boolean forSale = false;

    @ManyToOne
    private MediaItem mediaItem;

    @ManyToOne
    private Language language;

    @ManyToOne
    private Publisher publisher;

    /*
    @ManyToMany
    @JoinTable(
            name = "MediaExemplar_Sales",
            joinColumns = @JoinColumn(name = "mediaExemplar_id"),
            inverseJoinColumns = @JoinColumn(name = "sales_id")
    )
    private Set<Sales> sales = new HashSet<>();
    */

    // ---------------------

    public long getId() {
        return Id;
    }

    public MediaItem getMediaItem() {
        return mediaItem;
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
