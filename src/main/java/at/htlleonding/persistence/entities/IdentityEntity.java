package at.htlleonding.persistence.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class IdentityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public long getId() {
        return id;
    }
}
