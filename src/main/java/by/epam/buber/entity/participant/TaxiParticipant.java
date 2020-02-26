package by.epam.buber.entity.participant;

import java.util.Objects;

public abstract class TaxiParticipant {
    protected int id;
    protected String name;
    protected String password;
    protected Role role;
    protected String email;
    protected String phoneNumber;
    protected boolean banned;

    public TaxiParticipant() {
    }

    public TaxiParticipant(TaxiParticipant participant) {
        this.id = participant.id;
        this.name = participant.name;
        this.password = participant.password;
        this.role = participant.role;
        this.email = participant.email;
        this.phoneNumber = participant.phoneNumber;
        this.banned = participant.banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiParticipant that = (TaxiParticipant) o;
        return getId() == that.getId() &&
                banned == that.banned &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                getRole() == that.getRole() &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword(), getRole(), getEmail(), getPhoneNumber(), banned);
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
                ", banned=" + banned +
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

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
