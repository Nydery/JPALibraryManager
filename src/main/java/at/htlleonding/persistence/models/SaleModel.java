package at.htlleonding.persistence.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SaleModel extends IdentityModel{
    private LocalDate datetime;
    private ReceiptModel receipt;
    private final Set<MediaExemplarModel> mediaExemplars = new HashSet<>();

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

    public Set<MediaExemplarModel> getMediaExemplars() {
        return mediaExemplars;
    }
}
