package by.epam.buber.entity.participant;

import by.epam.buber.entity.Order;

public abstract class TaxiParticipant {
    protected int id;
    protected String name;
    protected String password;
    protected Role role;
    protected String email;
    protected String phoneNumber;
    protected Order currentOrder;

    public TaxiParticipant() {
    }

    public TaxiParticipant(String name, String password, String email, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public TaxiParticipant(int id, String name, String password, Role role, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public TaxiParticipant(int id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public TaxiParticipant(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Order getCurrentOrder() {
        return new Order(currentOrder);
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = new Order(currentOrder);
    }
}
