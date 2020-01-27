package by.epam.buber.entity.participant;

import java.math.BigDecimal;

public class User extends TaxiParticipant {
    private BigDecimal bill;

    public User() {
        role = Role.USER;
    }

    public User(String name, String password, String email, String phoneNumber) {
        super(name, password, email, phoneNumber);
        role = Role.USER;
    }

    public User(String name, String password) {
        super(name, password);
    }

    public BigDecimal getBill() {
        return new BigDecimal(bill.doubleValue());
    }

    public void setBill(BigDecimal bill) {
        this.bill = new BigDecimal(bill.doubleValue());
    }
}
