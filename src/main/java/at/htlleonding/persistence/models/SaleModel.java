package at.htlleonding.persistence.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SaleModel extends IdentityModel{
    private LocalDate datetime;
    private ReceiptModel receipt;
    private MediaExemplarModel mediaExemplar;

    //------------------------------------

    public LocalDate getDatetime() {
        return datetime;
    }
    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    public ReceiptModel getReceipt() {
        return receipt;
    }
    public void setReceipt(ReceiptModel receipt) {
        this.receipt = receipt;
    }

    public MediaExemplarModel getMediaExemplar() {
        return mediaExemplar;
    }
    public void setMediaExemplar(MediaExemplarModel mediaExemplar) {
        this.mediaExemplar = mediaExemplar;
    }
}
