package at.htlleonding.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends Person {
    @Column(nullable = false)
    private boolean isEmployee;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;

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
