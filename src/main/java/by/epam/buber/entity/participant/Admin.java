package by.epam.buber.entity.participant;

public class Admin extends TaxiParticipant {
    public Admin() {
        role = Role.ADMIN;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", banned=" + banned +
                '}';
    }
}
