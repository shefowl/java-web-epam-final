package by.epam.buber.entity.participant;

import by.epam.buber.entity.Order;

import java.util.Objects;

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

    public TaxiParticipant(TaxiParticipant participant) {
        this.id = participant.id;
        this.name = participant.name;
        this.password = participant.password;
        this.role = participant.role;
        this.email = participant.email;
        this.phoneNumber = participant.phoneNumber;
        this.currentOrder = participant.currentOrder;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiParticipant that = (TaxiParticipant) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                getRole() == that.getRole() &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equals(getCurrentOrder(), that.getCurrentOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword(), getRole(), getEmail(), getPhoneNumber(), getCurrentOrder());
    }

    @Override
    public String toString() {
        return "TaxiParticipant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", currentOrder=" + currentOrder +
                '}';
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
