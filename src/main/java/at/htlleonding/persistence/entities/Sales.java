package at.htlleonding.persistence.entities;

import javax.persistence.*;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
}
