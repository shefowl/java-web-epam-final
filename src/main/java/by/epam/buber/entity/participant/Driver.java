package by.epam.buber.entity.participant;

import by.epam.buber.entity.Car;
import by.epam.buber.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Driver extends TaxiParticipant {
    private Car car;
    private String coordinates;
    private boolean active;
    private boolean busy;
    private List<Order> orders;

    public Driver() {
        role = Role.DRIVER;
    }

    public Driver(Driver driver) {
        super(driver);
        this.car = new Car(driver.car);
        this.coordinates = driver.coordinates;
        this.active = driver.active;
        this.busy = driver.busy;
        this.orders = new ArrayList<>(orders);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Driver driver = (Driver) o;
        return isActive() == driver.isActive() &&
                isBusy() == driver.isBusy() &&
                Objects.equals(getCar(), driver.getCar()) &&
                Objects.equals(getCoordinates(), driver.getCoordinates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCar(), getCoordinates(), isActive(), isBusy());
    }

    @Override
    public String toString() {
        return "Driver{" +
                "car=" + car +
                ", coordinates='" + coordinates + '\'' +
                ", active=" + active +
                ", busy=" + busy +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", currentOrder=" + currentOrder +
                '}';
    }

    public Car getCar() {
        return new Car(car);
    }

    public void setCar(Car car) {
        this.car = new Car(car);
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void setOrders(List<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }
}
