package at.htlleonding.persistence.models;

import java.util.HashSet;
import java.util.Set;

public class AuthorModel extends IdentityModel {
    private String name;
    private final Set<MediaItemModel> mediaItems = new HashSet<>();

    //------------------------------------

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<MediaItemModel> getMediaItems() {
        return mediaItems;
    }
}
