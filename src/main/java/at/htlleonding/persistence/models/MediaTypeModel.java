package at.htlleonding.persistence.models;

import at.htlleonding.persistence.enums.MediaTypes;

public class MediaTypeModel extends IdentityModel{
    private MediaTypes type;
    private double price;

    //------------------------------------

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
}
