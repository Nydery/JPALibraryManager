package at.htlleonding.persistence.entities;

import at.htlleonding.persistence.enums.MediaTypes;

import javax.persistence.*;

@Entity
public class MediaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private MediaTypes type;

    @Column(nullable = false)
    private double price;

    // ---------------

    public long getId() {
        return id;
    }

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
