package at.htlleonding.persistence.entities;

import at.htlleonding.persistence.IEntity;

import javax.persistence.*;

@MappedSuperclass
public abstract class IdentityEntity implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public long getId() {
        return id;
    }
}
