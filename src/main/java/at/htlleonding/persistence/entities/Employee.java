package at.htlleonding.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee extends Person {
    @Column(nullable = false)
    private double salary;

    @OneToMany(mappedBy = "employee")
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
