package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;

    private LocalDate dateBirth;
    private LocalDate dateDeath;

    @OneToMany(mappedBy = "author")
    private Set<BookAuthor> books = new HashSet();


    public Author() {
    }
    public Author(String firstName, String lastName, String dateBirth, String dateDeath) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(!dateBirth.equals(""))
            this.dateBirth = LocalDate.parse(dateBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        if(!dateDeath.equals(""))
            this.dateDeath = LocalDate.parse(dateDeath, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    public Author(String firstName, String lastName, LocalDate dateBirth, LocalDate dateDeath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.dateDeath = dateDeath;
    }

    public long getId() {
        return id;
    }

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

    public LocalDate getDateBirth() {
        return dateBirth;
    }
    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public LocalDate getDateDeath() {
        return dateDeath;
    }
    public void setDateDeath(LocalDate dateDeath) {
        this.dateDeath = dateDeath;
    }

    public Set<BookAuthor> getBooks() {
        return books;
    }

    @Transient
    public String getFullName(){
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
