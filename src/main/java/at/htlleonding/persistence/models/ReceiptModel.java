package at.htlleonding.persistence.models;

import java.util.HashSet;
import java.util.Set;

public class ReceiptModel extends IdentityModel{
    private CustomerModel customer;
    private EmployeeModel employee;
    private final Set<SaleModel> sales = new HashSet<>();

    //------------------------------------

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

    public Set<SaleModel> getSales() {
        return sales;
    }
}
