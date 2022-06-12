package at.htlleonding.persistence.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class AuthorModel extends IdentityModel {
    private String firstName;
    private String lastName;
    private final Set<MediaItemModel> mediaItems = new HashSet<>();
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
}
