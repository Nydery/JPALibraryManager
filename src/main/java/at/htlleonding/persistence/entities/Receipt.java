package at.htlleonding.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Receipt extends IdentityEntity{
    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Employee employee;

    @OneToMany(mappedBy = "receipt")
    private final Set<Sale> sales = new HashSet<>();

    // -----------------------

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

    public Set<Sale> getSales() {
        return sales;
    }

    //custom methods
    public double getTotalPrice() {
        var result = 0.0;

        for(var sale : sales) {
            result += sale.getMediaExemplar().getMediaType().getPrice();
        }

        return result;
    }
}
