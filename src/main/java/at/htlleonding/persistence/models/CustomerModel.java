package at.htlleonding.persistence.models;

public class CustomerModel extends PersonModel {
    private boolean isEmployee;
    private String email;
    private String phoneNumber;

    //------------------------------------

    public boolean isEmployee() {
        return isEmployee;
    }
    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
