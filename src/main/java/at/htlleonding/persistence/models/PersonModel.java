package at.htlleonding.persistence.models;

public abstract class PersonModel extends IdentityModel{
    private String firstName;
    private String lastName;

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
}
