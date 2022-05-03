package at.htlleonding.persistence.models;

import java.util.HashSet;
import java.util.Set;

public class PublisherModel extends IdentityModel{
    private String name;
    private final Set<MediaExemplarModel> mediaExemplars = new HashSet<>();

    //------------------------------------

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<MediaExemplarModel> getMediaExemplars() {
        return mediaExemplars;
    }
}
