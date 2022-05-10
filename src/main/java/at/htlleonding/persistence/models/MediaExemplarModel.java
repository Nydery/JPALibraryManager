package at.htlleonding.persistence.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class MediaExemplarModel extends IdentityModel{
    private LocalDate buyDate;
    private boolean forSale = false;
    private boolean isForRent = false;
    private MediaItemModel mediaItem;
    private LanguageModel language;
    private PublisherModel publisher;
    private final Set<SaleModel> sales = new HashSet<>();

    //------------------------------------

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

    public boolean isForRent() {
        return isForRent;
    }
    public void setForRent(boolean forRent) {
        isForRent = forRent;
    }

    public MediaItemModel getMediaItem() {
        return mediaItem;
    }
    public void setMediaItem(MediaItemModel mediaItem) {
        this.mediaItem = mediaItem;
    }

    public LanguageModel getLanguage() {
        return language;
    }
    public void setLanguage(LanguageModel language) {
        this.language = language;
    }

    public PublisherModel getPublisher() {
        return publisher;
    }
    public void setPublisher(PublisherModel publisher) {
        this.publisher = publisher;
    }

    public Set<SaleModel> getSales() {
        return sales;
    }
}
