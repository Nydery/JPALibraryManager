package at.htlleonding.persistence.entities;

import at.htlleonding.misc.BusinessKey;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Person extends IdentityEntity {
    @Column(nullable = false)
    @BusinessKey
    private String firstName;
    @Column(nullable = false)
    @BusinessKey
    private String lastName;

    @Column
    @BusinessKey
    private LocalDate dateOfBirth;

    @Column
    @BusinessKey
    private LocalDate dateOfDeath;

    //--------------------------------


    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    //--------------------------------


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
