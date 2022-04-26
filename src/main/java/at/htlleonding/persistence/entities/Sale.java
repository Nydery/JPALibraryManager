package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Sale extends IdentityEntity{
    @Column
    private LocalDate datetime;

    @ManyToOne
    private Receipt receipt;

    @ManyToOne
    private MediaExemplar mediaExemplar;

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

    public Receipt getReceipt() {
        return receipt;
    }
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public MediaExemplar getMediaExemplar() {
        return mediaExemplar;
    }
    public void setMediaExemplar(MediaExemplar mediaExemplar) {
        this.mediaExemplar = mediaExemplar;
    }
}
