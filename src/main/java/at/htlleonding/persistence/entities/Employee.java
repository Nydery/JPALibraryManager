package at.htlleonding.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employee extends Person {
    @Column(nullable = false)
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
