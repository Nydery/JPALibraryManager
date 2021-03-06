package at.htlleonding.persistence.entities;

import at.htlleonding.misc.BusinessKey;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class MediaExemplar extends IdentityEntity {
    @BusinessKey
    private LocalDate buyDate;
    @BusinessKey
    private boolean forRent = false;
    @BusinessKey
    private boolean forSale = false;
    @BusinessKey
    private boolean isSold = false;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private MediaItem mediaItem;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Language language;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Publisher publisher;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private MediaType mediaType;

    /*
    @ManyToMany
    @JoinTable(
            name = "MediaExemplar_Sales",
            joinColumns = @JoinColumn(name = "mediaExemplar_id"),
            inverseJoinColumns = @JoinColumn(name = "sales_id")
    )
    private Set<Sale> sales = new HashSet<>();
     */
    // ---------------------

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

    public boolean isForRent() {
        return forRent;
    }
    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }

    public boolean isForSale() {
        return forSale;
    }
    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public boolean isSold() {
        return isSold;
    }
    public void setSold(boolean sold) {
        isSold = sold;
    }

    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public MediaType getMediaType() {
        return mediaType;
    }
    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
