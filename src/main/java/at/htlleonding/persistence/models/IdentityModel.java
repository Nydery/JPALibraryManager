package at.htlleonding.persistence.models;

import at.htlleonding.persistence.IEntity;

public class IdentityModel implements IEntity {
    protected long id;

    public long getId() {
        return id;
    }
}
