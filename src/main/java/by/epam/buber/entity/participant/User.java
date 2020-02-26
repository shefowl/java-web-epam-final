package by.epam.buber.entity.participant;

import java.util.Objects;

public class User extends TaxiParticipant {
    private int discount;

    public User() {
        role = Role.USER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getDiscount(), user.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDiscount());
    }

    @Override
    public String toString() {
        return "User{" +
                "discount=" + discount +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", banned=" + banned +
                '}';
    }


    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
