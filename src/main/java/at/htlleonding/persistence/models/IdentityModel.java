package at.htlleonding.persistence.models;

import at.htlleonding.persistence.IEntity;

public abstract class IdentityModel implements IEntity {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
