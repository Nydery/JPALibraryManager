package at.htlleonding.persistence.entities;

import at.htlleonding.misc.BusinessKey;
import org.hibernate.annotations.Cascade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee extends Person {
    @Column(nullable = false)
    @BusinessKey
    private double salary;

    @OneToMany(mappedBy = "employee")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private final Set<Receipt> receipts = new HashSet<Receipt>();

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }
}
