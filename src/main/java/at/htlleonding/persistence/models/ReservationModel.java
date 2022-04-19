package at.htlleonding.persistence.models;

import java.time.LocalDateTime;

public class ReservationModel extends IdentityModel{
    private LocalDateTime datetime;
    private CustomerModel customer;
    private EmployeeModel employee;
    private int renewalCounter;

    //------------------------------------

    public LocalDateTime getDatetime() {
        return datetime;
    }
    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public CustomerModel getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }
    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public int getRenewalCounter() {
        return renewalCounter;
    }
    public void setRenewalCounter(int renewalCounter) {
        this.renewalCounter = renewalCounter;
    }
}
