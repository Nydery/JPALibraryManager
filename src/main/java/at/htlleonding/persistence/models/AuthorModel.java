package at.htlleonding.persistence.models;

import java.util.HashSet;
import java.util.Set;

public class AuthorModel extends IdentityModel {
    private String firstName;
    private String lastName;
    private final Set<MediaItemModel> mediaItems = new HashSet<>();

    //------------------------------------


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<MediaItemModel> getMediaItems() {
        return mediaItems;
    }
}
