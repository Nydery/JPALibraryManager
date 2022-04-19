package at.htlleonding.persistence.models;

import java.util.HashSet;
import java.util.Set;

public class EmployeeModel extends PersonModel {
    private double salary;
    private final Set<ReceiptModel> receipts = new HashSet<ReceiptModel>();

    //------------------------------------

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Set<ReceiptModel> getReceipts() {
        return receipts;
    }
}
