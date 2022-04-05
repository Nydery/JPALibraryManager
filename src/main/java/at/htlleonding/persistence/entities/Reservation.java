package at.htlleonding.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Reservation extends IdentityEntity{
    @Column
    private LocalDateTime datetime;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Employee employee;

    @Column(scale = 1, precision = 0)
    private int renewalCounter;

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getRenewalCounter() {
        return renewalCounter;
    }

    public void setRenewalCounter(int renewalCounter) {
        this.renewalCounter = renewalCounter;
    }
}
